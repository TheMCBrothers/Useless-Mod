package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.item.PaintBrushItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class CanvasTileEntity extends TileEntity implements IColorHandler {

    private int color;

    public CanvasTileEntity() {
        super(TileEntityInit.CANVAS.get());
        this.color = -1;
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

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("Color", this.color);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.color = compound.getInt("Color");
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
        this.markDirty();
        this.requestModelDataUpdate();
        if (this.world != null)
            this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.DEFAULT_AND_RERENDER);
    }

    @Override
    public ActionResultType onClick(ItemStack stack) {
        return stack.getCapability(CapabilityColor.COLOR).map(colorHandler -> {
            if (colorHandler.hasColor() && colorHandler.getColor() != this.getColor()) {
                this.setColor(colorHandler.getColor());
                Item item = stack.getItem();
                if (item instanceof PaintBrushItem) {
                    PaintBrushItem paintBrushItem = (PaintBrushItem) item;
                    paintBrushItem.consumeColor(stack);
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }).orElse(ActionResultType.FAIL);
    }

    private final LazyOptional<IColorHandler> holder = LazyOptional.of(() -> this);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityColor.COLOR)
            return holder.cast();
        return super.getCapability(cap, side);
    }
}
