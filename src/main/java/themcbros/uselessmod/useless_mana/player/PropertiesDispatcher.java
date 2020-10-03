package themcbros.uselessmod.useless_mana.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class PropertiesDispatcher implements ICapabilitySerializable<CompoundNBT> {

    private final PlayerMana playerMana = new PlayerMana();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == PlayerProperties.PLAYER_MANA) {
            return LazyOptional.of(() -> this.playerMana).cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        this.playerMana.write(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.playerMana.read(nbt);
    }
}
