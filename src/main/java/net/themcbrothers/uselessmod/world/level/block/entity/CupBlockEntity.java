package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class CupBlockEntity extends BlockEntity {
    private static final String TAG_COFFEE = "coffee";
    private static final String TAG_CONSUMABLE = "consumable";

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

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        input.read(TAG_COFFEE, UselessRegistries.COFFEE_REGISTRY.holderByNameCodec()).ifPresent(coffeeTypeHolder -> this.type = coffeeTypeHolder);
        input.read(TAG_CONSUMABLE, Consumable.CODEC).ifPresent(consumable -> this.consumable = consumable);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        if (this.type != null) {
            output.store(TAG_COFFEE, UselessRegistries.COFFEE_REGISTRY.holderByNameCodec(), this.type);
        }

        if (this.consumable != null) {
            output.store(TAG_CONSUMABLE, Consumable.CODEC, this.consumable);
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveCustomOnly(lookupProvider);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        CoffeeType type = componentGetter.get(UselessDataComponents.COFFEE_TYPE.get());

        if (type != null) {
            this.type = UselessRegistries.COFFEE_REGISTRY.wrapAsHolder(type);
        }

        this.consumable = componentGetter.get(DataComponents.CONSUMABLE);
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
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard(TAG_COFFEE);
        output.discard(TAG_CONSUMABLE);
    }
}
