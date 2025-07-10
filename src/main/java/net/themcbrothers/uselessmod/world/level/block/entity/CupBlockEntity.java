package net.themcbrothers.uselessmod.world.level.block.entity;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class CupBlockEntity extends BlockEntity {
    private static final String TAG_COFFEE = "Coffee";
    private static final String TAG_CONSUMABLE = "Consumable";

    @Nullable
    private Holder<CoffeeType> type;
    @Nullable
    private Consumable consumable;

    public CupBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.CUP.get(), pos, state);
    }

    public void setType(@Nullable CoffeeType type) {
        this.type = type != null ? UselessRegistries.COFFEE_REGISTRY.wrapAsHolder(type) : null;
        this.consumable = type != null ? type.getConsumable() : null;

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
            this.type.unwrapKey().ifPresent(key -> tag.putString(TAG_COFFEE, key.location().toString()));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.loadAdditional(tag, lookupProvider);
        if (tag.contains(TAG_COFFEE, Tag.TAG_STRING)) {
            var key = ResourceKey.create(UselessRegistries.COFFEE_KEY, Objects.requireNonNull(ResourceLocation.tryParse(tag.getString(TAG_COFFEE))));
            this.type = UselessRegistries.COFFEE_REGISTRY.get(key).orElse(null);
        }

        if (tag.contains(TAG_CONSUMABLE, Tag.TAG_COMPOUND)) {
            DataResult<Consumable> dataResult = Consumable.CODEC.parse(NbtOps.INSTANCE, tag.getCompound(TAG_CONSUMABLE));

            if (dataResult.isSuccess()) {
                this.consumable = dataResult.getOrThrow();
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        this.writeCoffeeNbt(tag);

        if (this.consumable != null) {
            tag.put(TAG_CONSUMABLE, Consumable.CODEC.encode(this.consumable, registries.createSerializationContext(NbtOps.INSTANCE), new CompoundTag()).getOrThrow());
        }
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
    protected void applyImplicitComponents(DataComponentInput components) {
        CoffeeType type = components.get(UselessDataComponents.COFFEE_TYPE.get());

        if (type != null) {
            this.type = UselessRegistries.COFFEE_REGISTRY.wrapAsHolder(type);
        }

        this.consumable = components.get(DataComponents.CONSUMABLE);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        if (this.type != null) {
            builder.set(UselessDataComponents.COFFEE_TYPE.get(), this.type.value());
        }

        if (this.consumable != null) {
            builder.set(DataComponents.CONSUMABLE, this.consumable);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove(TAG_COFFEE);
        tag.remove(TAG_CONSUMABLE);
    }
}
