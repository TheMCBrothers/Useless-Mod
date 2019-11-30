package tk.themcbros.uselessmod.compat.top;

import mcjty.theoneprobe.api.ITheOneProbe;
import tk.themcbros.uselessmod.UselessMod;

import java.util.function.Function;

public class TheOneProbeSupport implements Function<ITheOneProbe, Void> {

    @Override
    public Void apply(ITheOneProbe theOneProbe) {
        UselessMod.LOGGER.info("Enabled support for TheOneProbe");
        theOneProbe.registerProvider(MachineProbeProvider.INSTANCE);
        return null;
    }

}
