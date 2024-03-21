package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class MachineSupplierBlockEntity extends BlockEntity {
    public static final ModelProperty<BlockState> MIMIC_PROPERTY = new ModelProperty<>();

    @Nullable
    private BlockState mimic;

    public MachineSupplierBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.MACHINE_SUPPLIER.get(), pos, state);
    }

    @Nullable
    public BlockState getMimic() {
        return this.mimic;
    }

    public void setMimic(@Nullable BlockState mimic) {
        this.mimic = mimic;
        this.setChanged();
        this.requestModelDataUpdate();

        if (this.level != null) {
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        this.writeMimic(tag);
    }

    private void writeMimic(CompoundTag tag) {
        if (this.mimic != null) {
            tag.put("Mimic", NbtUtils.writeBlockState(this.mimic));
        }
    }

    @Override
    public void load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.load(tag, lookupProvider);
        if (tag.contains("Mimic", Tag.TAG_COMPOUND) && this.level != null) {
            this.mimic = NbtUtils.readBlockState(this.level.holderLookup(Registries.BLOCK), tag.getCompound("Mimic"));
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = new CompoundTag();
        this.writeMimic(tag);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        BlockState oldMimic = this.mimic;
        super.onDataPacket(net, pkt, lookupProvider);
        if (!Objects.equals(oldMimic, this.mimic)) {
            this.requestModelDataUpdate();
            if (this.level != null) {
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
            }
        }
    }

    @NotNull
    @Override
    public ModelData getModelData() {
        return ModelData.builder().with(MIMIC_PROPERTY, this.mimic).build();
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        this.setMimic(components.get(UselessDataComponents.MIMIC.get()));
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        builder.set(UselessDataComponents.MIMIC.get(), this.getMimic());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("Mimic");
    }
}
