package themcbros.uselessmod.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.wires.WireApi;
import blusunrize.immersiveengineering.api.wires.WireType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import themcbros.uselessmod.UselessMod;

public class ImmersiveCompat {

    static final WireType USELESS_WIRE_TYPE = new UselessWireType();
    static final Item USELESS_WIRE_COIL = new WireCoilItem(USELESS_WIRE_TYPE, new Item.Properties().group(UselessMod.GROUP));
    static final Item USELESS_WIRE = new Item(new Item.Properties().group(UselessMod.GROUP)).setRegistryName(UselessMod.rl("useless_wire"));
    static final Item SUPER_USELESS_WIRE = new Item(new Item.Properties().group(UselessMod.GROUP)).setRegistryName(UselessMod.rl("super_useless_wire"));

    public static void register(IEventBus bus) {
        bus.register(ImmersiveCompat.class);
        WireApi.registerWireType(USELESS_WIRE_TYPE);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(USELESS_WIRE_COIL, USELESS_WIRE, SUPER_USELESS_WIRE);
    }
}
