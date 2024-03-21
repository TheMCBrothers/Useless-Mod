package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import net.themcbrothers.uselessmod.init.UselessFluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaintBucketBlockEntity extends BlockEntity {
    public final FluidTank colorTank = new FluidTank(FluidType.BUCKET_VOLUME, fluidStack -> (fluidStack.getFluid().is(FluidTags.WATER) || fluidStack.getFluid().is(UselessTags.Fluids.PAINT)) && fluidStack.getFluid().isSource(fluidStack.getFluid().defaultFluidState()));
    public final ItemStackHandler stackHandler = new ItemStackHandler() {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            PaintBucketBlockEntity.this.setChanged();
        }
    };

    public PaintBucketBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINT_BUCKET.get(), pos, state);
    }

    public boolean hasColor() {
        return !this.colorTank.isEmpty() && this.colorTank.getFluid().getFluid().is(UselessTags.Fluids.PAINT) && this.colorTank.getFluid().has(UselessDataComponents.COLOR.get());
    }

    public int getColor() {
        return this.colorTank.getFluid().getOrDefault(UselessDataComponents.COLOR.get(), -1);
    }

    public void setColor(float[] colorValues) {
        int r = (int) (colorValues[0] * 255.0F) << 16;
        int g = (int) (colorValues[1] * 255.0F) << 8;
        int b = (int) (colorValues[2] * 255.0F);

        this.setColor(r + g + b);
    }

    /**
     * @param color Color
     */
    public void setColor(int color) {
        this.colorTank.setFluid(new FluidStack(UselessFluids.PAINT.get(), FluidType.BUCKET_VOLUME));
        this.colorTank.getFluid().set(UselessDataComponents.COLOR.get(), color);
        this.setChanged();
    }

    private void saveColorAndItem(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        if (!this.colorTank.isEmpty()) {
            tag.put("Tank", this.colorTank.writeToNBT(lookupProvider, new CompoundTag()));
        }

        tag.put("Slots", this.stackHandler.serializeNBT(lookupProvider));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        this.saveColorAndItem(tag, lookupProvider);
        return tag;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        this.saveColorAndItem(tag, lookupProvider);
    }

    @Override
    public void load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.load(tag, lookupProvider);
        this.colorTank.readFromNBT(lookupProvider, tag.getCompound("Tank"));
        this.stackHandler.deserializeNBT(lookupProvider, tag.getCompound("Slots"));
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        builder.set(NeoForgeMod.FLUID_STACK_COMPONENT.get(), this.colorTank.getFluid().immutable());
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        this.colorTank.setFluid(FluidStack.of(components.getOrDefault(NeoForgeMod.FLUID_STACK_COMPONENT.get(), FluidStack.EMPTY.immutable())));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("Tank");
    }
}
