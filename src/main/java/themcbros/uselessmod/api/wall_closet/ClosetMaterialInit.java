package themcbros.uselessmod.api.wall_closet;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.api.UselessAPI;
import themcbros.uselessmod.api.UselessRegistries;

import java.util.Objects;

/**
 * @author TheMCBrothers
 */
@Mod.EventBusSubscriber(modid = UselessAPI.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClosetMaterialInit {

    public static final DeferredRegister<ClosetMaterial> REGISTER = DeferredRegister.create(UselessRegistries.CLOSET_MATERIALS, "minecraft");

    public static final RegistryObject<ClosetMaterial> OAK_PLANKS = REGISTER.register("oak_planks",
            () -> new ClosetMaterial(() -> Blocks.OAK_PLANKS));
    public static final RegistryObject<ClosetMaterial> SPRUCE_PLANKS = REGISTER.register("spruce_planks",
            () -> new ClosetMaterial(() -> Blocks.SPRUCE_PLANKS));
    public static final RegistryObject<ClosetMaterial> BIRCH_PLANKS = REGISTER.register("birch_planks",
            () -> new ClosetMaterial(() -> Blocks.BIRCH_PLANKS));
    public static final RegistryObject<ClosetMaterial> JUNGLE_PLANKS = REGISTER.register("jungle_planks",
            () -> new ClosetMaterial(() -> Blocks.JUNGLE_PLANKS));
    public static final RegistryObject<ClosetMaterial> ACACIA_PLANKS = REGISTER.register("acacia_planks",
            () -> new ClosetMaterial(() -> Blocks.ACACIA_PLANKS));
    public static final RegistryObject<ClosetMaterial> DARK_OAK_PLANKS = REGISTER.register("dark_oak_planks",
            () -> new ClosetMaterial(() -> Blocks.DARK_OAK_PLANKS));
    public static final RegistryObject<ClosetMaterial> CRIMSON_PLANKS = REGISTER.register("crimson_planks",
            () -> new ClosetMaterial(() -> Blocks.CRIMSON_PLANKS));
    public static final RegistryObject<ClosetMaterial> WARPED_PLANKS = REGISTER.register("warped_planks",
            () -> new ClosetMaterial(() -> Blocks.WARPED_PLANKS));

    @SubscribeEvent
    public static void registerModPlanks(final RegistryEvent.Register<ClosetMaterial> event) {
        for (Block block : ForgeRegistries.BLOCKS) {
            ResourceLocation registryName = block.getRegistryName();
            assert registryName != null;
            if (registryName.getNamespace().equalsIgnoreCase("minecraft")
                    || !(registryName.getPath().endsWith("_planks"))) continue;

            if (registryName.getNamespace().equalsIgnoreCase("quark") && registryName.getPath().contains("vertical_"))
                continue;

            event.getRegistry().register(new ClosetMaterial(() -> block, null).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
        }
    }

}
