package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.themcbrothers.lib.LibDataComponents;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.core.UselessFluids;
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
        super(UselessBlockEntityTypes.PAINT_BUCKET.get(), pos, state);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (this.level != null) {
            Containers.dropContents(level, pos, NonNullList.of(this.stackHandler.getStackInSlot(0)));
        }
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

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveCustomOnly(lookupProvider);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        if (!this.colorTank.isEmpty()) {
            this.colorTank.serialize(output.child("Tank"));
        }

        this.stackHandler.serialize(output.child("Slots"));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.colorTank.deserialize(input.childOrEmpty("Tank"));
        this.stackHandler.deserialize(input.childOrEmpty("Slots"));
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        builder.set(LibDataComponents.FLUID.get(), SimpleFluidContent.copyOf(this.colorTank.getFluid()));
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        this.colorTank.setFluid(componentGetter.getOrDefault(LibDataComponents.FLUID.get(), SimpleFluidContent.EMPTY).copy());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("Tank");
    }
}
