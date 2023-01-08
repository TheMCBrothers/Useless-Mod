package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
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
    }

    @Nullable
    public CoffeeType getCoffeeType() {
        return this.type;
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
        if (this.type != null) {
            tag.putString("Coffee", String.valueOf(this.type.getRegistryName()));
        }
        super.saveAdditional(tag);
    }
}
