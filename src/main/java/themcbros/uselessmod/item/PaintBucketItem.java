package themcbros.uselessmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.helpers.ColorUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class PaintBucketItem extends BucketItem {
    public PaintBucketItem(Properties builder) {
        super(ColorModule.PAINT, builder);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            for (DyeColor col : DyeColor.values()) {
                ItemStack stack = new ItemStack(this);
                stack.getOrCreateTag().putInt("Color", col.getColorValue());
                items.add(stack);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag() && stack.getOrCreateTag().contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
            tooltip.add(ColorUtils.getHexAsText(stack.getOrCreateTag().getInt("Color")));
        }
    }

    public ItemStack fillBucket(FluidStack fluidStack) {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().putInt("Color", fluidStack.getFluid().getAttributes().getColor(fluidStack));
        return stack;
    }

    private FluidStack getFluid(ItemStack itemStack) {
        FluidStack fluidStack = new FluidStack(this.getFluid(), FluidAttributes.BUCKET_VOLUME);
        if (itemStack.hasTag())
            fluidStack.getOrCreateTag().putInt("Color", itemStack.getOrCreateTag().getInt("Color"));
        return fluidStack;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (this.getClass() == PaintBucketItem.class)
            return new PaintBucketWrapper(stack);
        else
            return super.initCapabilities(stack, nbt);
    }

    private class PaintBucketWrapper implements IFluidHandlerItem, ICapabilityProvider {
        private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

        @Nonnull
        protected ItemStack container;

        public PaintBucketWrapper(@Nonnull ItemStack container) {
            this.container = container;
        }

        @Nonnull
        @Override
        public ItemStack getContainer() {
            return container;
        }

        public boolean canFillFluidType(FluidStack fluid) {
            return fluid.getFluid() == ColorModule.PAINT.get();
        }

        @Nonnull
        public FluidStack getFluid() {
            Item item = container.getItem();
            if (item instanceof PaintBucketItem) {
                return PaintBucketItem.this.getFluid(container);
            } else {
                return FluidStack.EMPTY;
            }
        }

        protected void setFluid(@Nonnull FluidStack fluidStack) {
            if (fluidStack.isEmpty())
                container = new ItemStack(Items.BUCKET);
            else
                container = PaintBucketItem.this.fillBucket(fluidStack);
        }

        @Override
        public int getTanks() {
            return 1;
        }

        @Nonnull
        @Override
        public FluidStack getFluidInTank(int tank) {
            return getFluid();
        }

        @Override
        public int getTankCapacity(int tank) {
            return FluidAttributes.BUCKET_VOLUME;
        }

        @Override
        public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
            return stack.getFluid() == ColorModule.PAINT.get();
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            if (container.getCount() != 1 || resource.getAmount() < FluidAttributes.BUCKET_VOLUME || !getFluid().isEmpty() || !canFillFluidType(resource)) {
                return 0;
            }

            if (action.execute()) {
                setFluid(resource);
            }

            return FluidAttributes.BUCKET_VOLUME;
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            if (container.getCount() != 1 || resource.getAmount() < FluidAttributes.BUCKET_VOLUME) {
                return FluidStack.EMPTY;
            }

            FluidStack fluidStack = getFluid();
            if (!fluidStack.isEmpty() && fluidStack.isFluidEqual(resource)) {
                if (action.execute()) {
                    setFluid(FluidStack.EMPTY);
                }
                return fluidStack;
            }

            return FluidStack.EMPTY;
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            if (container.getCount() != 1 || maxDrain < FluidAttributes.BUCKET_VOLUME) {
                return FluidStack.EMPTY;
            }

            FluidStack fluidStack = getFluid();
            if (!fluidStack.isEmpty()) {
                if (action.execute()) {
                    setFluid(FluidStack.EMPTY);
                }
                return fluidStack;
            }

            return FluidStack.EMPTY;
        }

        @Override
        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
            return CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY.orEmpty(capability, holder);
        }
    }
}