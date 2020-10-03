package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.init.TileEntityInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class PaintBucketTileEntity extends TileEntity implements IWrenchableTileEntity {

    public ColorTank color;
    public final ItemStackHandler stackHandler = new ItemStackHandler() {
        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            PaintBucketTileEntity.this.markDirty();
        }
    };

    public PaintBucketTileEntity() {
        super(TileEntityInit.PAINT_BUCKET.get());
        this.color = new ColorTank(FluidAttributes.BUCKET_VOLUME);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (!this.color.isEmpty())
            compound.put("Color", this.color.writeToNBT(new CompoundNBT()));
        compound.put("ItemHandler", this.stackHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.color.readFromNBT(compound.getCompound("Color"));
        this.stackHandler.deserializeNBT(compound.getCompound("ItemHandler"));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        assert this.world != null;
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    private final LazyOptional<ColorTank> colorHolder = LazyOptional.of(() -> this.color);
    private final LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> this.stackHandler);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || cap == CapabilityColor.COLOR)
            return colorHolder.cast();
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return itemHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        this.colorHolder.invalidate();
        this.itemHandler.invalidate();
    }

    public static class ColorTank extends FluidTank implements IColorHandler {

        public ColorTank(int capacity) {
            super(capacity);
        }

        @Override
        public int getColor() {
            if (this.getFluid().getFluid() == ColorModule.PAINT.get()) {
                CompoundNBT tag = this.getFluid().getTag();
                if (tag != null && tag.contains("Color", Constants.NBT.TAG_ANY_NUMERIC))
                    return tag.getInt("Color");
            }
            return -1;
        }

        @Override
        public void setColor(int color) {
            if (!this.hasColor()) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putInt("Color", color);
                FluidStack newStack = new FluidStack(ColorModule.PAINT.get(), FluidAttributes.BUCKET_VOLUME, nbt);
                this.setFluid(newStack);
            } else {
                this.getFluid().getOrCreateTag().putInt("Color", color);
            }
        }

        @Override
        public ActionResultType onClick(ItemStack stack) {
            if (this.hasColor()) {
                FluidStack fluidStack = this.getFluid();
                int color = this.getColor();
                return stack.getCapability(CapabilityColor.COLOR).map(colorHolder -> {
                    if (colorHolder.getColor() != color) {
                        colorHolder.setColor(color);
                        fluidStack.shrink(100);
                        if (fluidStack.isEmpty())
                            fluidStack.setTag(null);
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }).orElse(ActionResultType.FAIL);
            }
            return ActionResultType.FAIL;
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return (stack.getFluid().isIn(FluidTags.WATER)) && stack.getFluid().isSource(fluid.getFluid().getDefaultState())
                    || stack.getFluid() == ColorModule.PAINT.get();
        }
    }

}
