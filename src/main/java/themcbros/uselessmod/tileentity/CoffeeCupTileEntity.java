package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.init.TileEntityInit;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author TheMCBrothers
 */
public class CoffeeCupTileEntity extends TileEntity implements IWrenchableTileEntity {

    @Nullable
    private CoffeeType coffeeType = null;

    public CoffeeCupTileEntity() {
        super(TileEntityInit.COFFEE_CUP.get());
    }

    @Nullable
    public CoffeeType getCoffeeType() {
        return this.coffeeType;
    }

    public void setCoffeeType(@Nullable CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
        this.markDirty();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (this.coffeeType != null)
            compound.putString("CoffeeType", Objects.requireNonNull(this.coffeeType.getRegistryName()).toString());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CoffeeType", Constants.NBT.TAG_STRING))
            this.coffeeType = CoffeeType.byId(compound.getString("CoffeeType"));
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        if (net.getDirection() == PacketDirection.CLIENTBOUND) {
            assert this.world != null;
            this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
        }
    }

}
