package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.model.data.ModelData;
import net.neoforged.neoforge.model.data.ModelProperty;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;

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
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (this.mimic != null) {
            output.store("Mimic", CompoundTag.CODEC, NbtUtils.writeBlockState(this.mimic));
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.read("Mimic", CompoundTag.CODEC)
                .ifPresent(compoundTag -> this.mimic = NbtUtils.readBlockState(this.level.holderLookup(Registries.BLOCK), compoundTag));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveCustomOnly(lookupProvider);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ValueInput valueInput) {
        BlockState oldMimic = this.mimic;
        super.onDataPacket(net, valueInput);
        if (!Objects.equals(oldMimic, this.mimic)) {
            this.requestModelDataUpdate();
            if (this.level != null) {
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
            }
        }
    }

    @Override
    public ModelData getModelData() {
        return ModelData.builder().with(MIMIC_PROPERTY, this.mimic).build();
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        this.setMimic(componentGetter.get(UselessDataComponents.MIMIC.get()));
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        builder.set(UselessDataComponents.MIMIC.get(), this.getMimic());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("Mimic");
    }
}
