package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class CupBlockEntity extends BlockEntity {
    private static final String TAG_COFFEE = "Coffee";

    @Nullable
    private Holder<CoffeeType> type;

    public CupBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CUP.get(), pos, state);
    }

    public void setType(@Nullable CoffeeType type) {
        this.type = type != null ? UselessRegistries.COFFEE_REGISTRY.wrapAsHolder(type) : null;
        this.setChanged();

        if (this.level != null) {
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
        }
    }

    public Optional<CoffeeType> getCoffeeType() {
        if (this.type == null) {
            return Optional.empty();
        }

        return Optional.of(this.type.value());
    }

    private void writeCoffeeNbt(CompoundTag tag) {
        if (this.type != null) {
            this.type.unwrapKey().ifPresent(key -> tag.putString("Coffee", key.location().toString()));
        }
    }

    @Override
    public void load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.load(tag, lookupProvider);
        if (tag.contains(TAG_COFFEE, Tag.TAG_STRING)) {
            var key = ResourceKey.create(UselessRegistries.COFFEE_KEY, Objects.requireNonNull(ResourceLocation.tryParse(tag.getString(TAG_COFFEE))));
            this.type = UselessRegistries.COFFEE_REGISTRY.getHolder(key).orElse(null);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        this.writeCoffeeNbt(tag);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        this.writeCoffeeNbt(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        CoffeeType type = components.get(UselessDataComponents.COFFEE_TYPE.get());

        if (type != null) {
            this.type = UselessRegistries.COFFEE_REGISTRY.wrapAsHolder(type);
        }
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        if (this.type != null) {
            builder.set(UselessDataComponents.COFFEE_TYPE.get(), this.type.value());
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("Coffee");
    }
}
