package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class CupBlockEntity extends BlockEntity {
    private static final String TAG_COFFEE = "Coffee";

    @Nullable
    private CoffeeType type;

    public CupBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CUP.get(), pos, state);
    }

    public void setType(@Nullable CoffeeType type) {
        this.type = type;
        this.setChanged();

        if (this.level != null) {
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
        }
    }

    @Nullable
    public CoffeeType getCoffeeType() {
        return this.type;
    }

    private void writeCoffeeNbt(CompoundTag tag) {
        if (this.type != null) {
            tag.putString("Coffee", String.valueOf(this.type.getRegistryName()));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(TAG_COFFEE, Tag.TAG_STRING)) {
            this.type = UselessRegistries.coffeeRegistry.get().getValue(ResourceLocation.tryParse(tag.getString(TAG_COFFEE)));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        this.writeCoffeeNbt(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        this.writeCoffeeNbt(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
