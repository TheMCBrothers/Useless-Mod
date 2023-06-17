package net.themcbrothers.uselessmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.init.Registration;
import net.themcbrothers.uselessmod.setup.ClientSetup;
import net.themcbrothers.uselessmod.setup.CommonSetup;
import net.themcbrothers.uselessmod.setup.ServerSetup;

@Mod(UselessMod.MOD_ID)
public class UselessMod {
    public static final String MOD_ID = "uselessmod";

    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod"))
                    .icon(() -> new ItemStack(ModItems.USELESS_INGOT))
                    .build());

    public static CommonSetup setup;

    public UselessMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TABS.register(bus);
        bus.addListener(this::buildCreativeTab);
        setup = DistExecutor.unsafeRunForDist(() -> ClientSetup::new, () -> ServerSetup::new);
    }

    private void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TAB.get()) {
            event.acceptAll(Registration.ITEMS.getEntries().stream().map(RegistryObject::get).map(ItemStack::new).toList());
        }
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static MutableComponent translate(String prefix, String suffix, Object... args) {
        return Component.translatable(String.format("%s.%s.%s", prefix, MOD_ID, suffix), args);
    }
}
