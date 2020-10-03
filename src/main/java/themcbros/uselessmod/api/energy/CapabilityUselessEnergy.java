package themcbros.uselessmod.api.energy;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import themcbros.uselessmod.energy.UselessEnergyStorage;

/**
 * @author TheMCBrothers
 */
public class CapabilityUselessEnergy {

    @CapabilityInject(IUselessEnergyStorage.class)
    public static Capability<IUselessEnergyStorage> USELESS_ENERGY;

    public static void register() {
        CapabilityManager.INSTANCE.register(IUselessEnergyStorage.class, new Capability.IStorage<IUselessEnergyStorage>() {
            @Override
            public INBT writeNBT(Capability<IUselessEnergyStorage> capability, IUselessEnergyStorage instance, Direction side) {
                return IntNBT.valueOf(instance.getEnergyStored());
            }

            @Override
            public void readNBT(Capability<IUselessEnergyStorage> capability, IUselessEnergyStorage instance, Direction side, INBT nbt) {
                if (!(instance instanceof UselessEnergyStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                ((UselessEnergyStorage) instance).setEnergyStored(((IntNBT) nbt).getInt());
            }
        }, () -> new UselessEnergyStorage(1000, 1000, 1000));
    }

}
