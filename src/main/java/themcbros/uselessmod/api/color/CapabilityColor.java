package themcbros.uselessmod.api.color;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * @author TheMCBrothers
 */
public class CapabilityColor {

    @CapabilityInject(IColorHandler.class)
    public static Capability<IColorHandler> COLOR;

    public static void register() {
        CapabilityManager.INSTANCE.register(IColorHandler.class, new Capability.IStorage<IColorHandler>() {
            @Override
            public INBT writeNBT(Capability<IColorHandler> capability, IColorHandler instance, Direction side) {
                return IntNBT.valueOf(instance.getColor());
            }

            @Override
            public void readNBT(Capability<IColorHandler> capability, IColorHandler instance, Direction side, INBT nbt) {
                if (!(instance instanceof DefaultColorHandler))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                instance.setColor(((IntNBT) nbt).getInt());
            }
        }, () -> DefaultColorHandler.INSTANCE);
    }

}
