package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
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
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.themcbrothers.lib.LibDataComponents;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.core.UselessFluids;
import org.jetbrains.annotations.Nullable;

public class PaintBucketBlockEntity extends BlockEntity {
    public final FluidStacksResourceHandler colorTank = new FluidStacksResourceHandler(1, FluidType.BUCKET_VOLUME) {
        @Override
        public boolean isValid(int index, FluidResource resource) {
            return (resource.is(FluidTags.WATER) || resource.is(UselessTags.Fluids.PAINT)) && resource.getFluid().isSource(resource.getFluid().defaultFluidState());
        }
    };
    public final ItemStacksResourceHandler stackHandler = new ItemStacksResourceHandler(1) {
        @Override
        protected int getCapacity(int index, ItemResource resource) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            PaintBucketBlockEntity.this.setChanged();
        }
    };

    public PaintBucketBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.PAINT_BUCKET.get(), pos, state);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (this.level != null) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemUtil.getStack(this.stackHandler, 0));
        }
    }

    public boolean hasColor() {
        return !this.colorTank.getResource(0).isEmpty() && this.colorTank.getResource(0).is(UselessTags.Fluids.PAINT) && this.colorTank.getResource(0).has(UselessDataComponents.COLOR.get());
    }

    public int getColor() {
        return this.colorTank.getResource(0).getOrDefault(UselessDataComponents.COLOR.get(), -1);
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
        this.colorTank.set(0, FluidResource.of(UselessFluids.PAINT,
                        DataComponentPatch.builder()
                                .set(UselessDataComponents.COLOR.get(), color)
                                .build()),
                FluidType.BUCKET_VOLUME);
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

        if (!this.colorTank.getResource(0).isEmpty()) {
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
        builder.set(LibDataComponents.FLUID.get(), SimpleFluidContent.copyOf(FluidUtil.getStack(this.colorTank, 0)));
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        FluidStack stack = componentGetter.getOrDefault(LibDataComponents.FLUID.get(), SimpleFluidContent.EMPTY).copy();
        this.colorTank.set(0, FluidResource.of(stack), stack.getAmount());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("Tank");
    }
}
