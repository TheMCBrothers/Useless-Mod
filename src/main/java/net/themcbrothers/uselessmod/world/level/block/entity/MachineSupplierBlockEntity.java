package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class MachineSupplierBlockEntity extends BlockEntity {
    public static final ModelProperty<BlockState> MIMIC_PROPERTY = new ModelProperty<>();

    @Nullable
    private BlockState mimic;

    public MachineSupplierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.MACHINE_SUPPLIER.get(), pos, state);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (this.level != null) {
            BlockState state = this.level.getBlockState(this.getBlockPos().above());

            if (state.hasBlockEntity()) {
                BlockEntity blockEntity = this.level.getBlockEntity(this.getBlockPos().above());
                if (blockEntity != null) {
                    return blockEntity.getCapability(cap, side);
                }
            }
        }

        return super.getCapability(cap, side);
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
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        this.writeMimic(tag);
    }

    private void writeMimic(CompoundTag tag) {
        if (this.mimic != null) {
            tag.put("Mimic", NbtUtils.writeBlockState(this.mimic));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Mimic", Tag.TAG_COMPOUND) && this.level != null) {
            this.mimic = NbtUtils.readBlockState(this.level.holderLookup(Registries.BLOCK), tag.getCompound("Mimic"));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.writeMimic(tag);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        BlockState oldMimic = this.mimic;
        super.onDataPacket(net, pkt);
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
}
