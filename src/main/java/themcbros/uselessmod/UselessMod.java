package themcbros.uselessmod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.registration.RegistrationHelper;
import slimeknights.mantle.util.SupplierItemGroup;
import themcbros.uselessmod.api.UselessAPI;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.proxy.ClientProxy;
import themcbros.uselessmod.proxy.CommonProxy;
import themcbros.uselessmod.proxy.ServerProxy;

/**
 * @author TheMCBrothers
 */
@Mod(UselessMod.MOD_ID)
public class UselessMod {

    public static final String MOD_ID = UselessAPI.MOD_ID;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static UselessMod instance;
    public final CommonProxy proxy;

    public static final ItemGroup GROUP = new SupplierItemGroup(MOD_ID, MOD_ID, () -> new ItemStack(ItemInit.USELESS_INGOT));

    public UselessMod() {
        instance = this;

        // Enable Milk Fluid
        ForgeMod.enableMilkFluid();

        UselessAPI.init(new UselessModAPI());

        MinecraftForge.EVENT_BUS.register(this);

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    }

    public static UselessMod getInstance() {
        return instance;
    }

    public static ResourceLocation rl(String s) {
        return new ResourceLocation(MOD_ID, s);
    }

    @SubscribeEvent
    public void missingBlockMappings(final RegistryEvent.MissingMappings<Block> event) {
        RegistrationHelper.handleMissingMappings(event, MOD_ID, block -> {
            switch (block) {
                case "useless_sapling":
                    return BlockInit.USELESS_OAK_SAPLING.get();
                case "useless_leaves":
                    return BlockInit.USELESS_OAK_LEAVES.get();
                case "potted_useless_sapling":
                    return BlockInit.POTTED_USELESS_OAK_SAPLING.get();
                case "useless_log":
                    return BlockInit.USELESS_OAK_LOG.get();
                case "useless_wood":
                    return BlockInit.USELESS_OAK_WOOD.get();
                case "stripped_useless_log":
                    return BlockInit.STRIPPED_USELESS_OAK_LOG.get();
                case "stripped_useless_wood":
                    return BlockInit.STRIPPED_USELESS_OAK_WOOD.get();
                case "useless_planks":
                    return BlockInit.USELESS_OAK_PLANKS.get();
                case "useless_stairs":
                    return BlockInit.USELESS_OAK_STAIRS.get();
                case "useless_slab":
                    return BlockInit.USELESS_OAK_SLAB.get();
                case "useless_fence":
                    return BlockInit.USELESS_OAK_FENCE.get();
                case "useless_fence_gate":
                    return BlockInit.USELESS_OAK_FENCE_GATE.get();
                case "wooden_useless_door":
                    return BlockInit.USELESS_OAK_DOOR.get();
                case "wooden_useless_trapdoor":
                    return BlockInit.USELESS_OAK_TRAPDOOR.get();
                case "useless_pressure_plate":
                    return BlockInit.USELESS_OAK_PRESSURE_PLATE.get();
                case "useless_button":
                    return BlockInit.USELESS_OAK_BUTTON.get();
                case "useless_sign":
                    return BlockInit.USELESS_OAK_SIGN.get();
                case "useless_wall_sign":
                    return BlockInit.USELESS_OAK_WALL_SIGN.get();
                case "useless_chest":
                    return BlockInit.USELESS_OAK_CHEST.get();
                default:
                    return null;
            }
        });
    }

        @SubscribeEvent
        public void missingItemMappings(final RegistryEvent.MissingMappings<Item> event) {
            RegistrationHelper.handleMissingMappings(event, MOD_ID, item -> {
                switch (item) {
                    case "useless_sapling":
                        return BlockInit.USELESS_OAK_SAPLING.asItem();
                    case "useless_leaves":
                        return BlockInit.USELESS_OAK_LEAVES.asItem();
                    case "useless_log":
                        return BlockInit.USELESS_OAK_LOG.asItem();
                    case "useless_wood":
                        return BlockInit.USELESS_OAK_WOOD.asItem();
                    case "stripped_useless_log":
                        return BlockInit.STRIPPED_USELESS_OAK_LOG.asItem();
                    case "stripped_useless_wood":
                        return BlockInit.STRIPPED_USELESS_OAK_WOOD.asItem();
                    case "useless_planks":
                        return BlockInit.USELESS_OAK_PLANKS.asItem();
                    case "useless_stairs":
                        return BlockInit.USELESS_OAK_STAIRS.asItem();
                    case "useless_slab":
                        return BlockInit.USELESS_OAK_SLAB.asItem();
                    case "useless_fence":
                        return BlockInit.USELESS_OAK_FENCE.asItem();
                    case "useless_fence_gate":
                        return BlockInit.USELESS_OAK_FENCE_GATE.asItem();
                    case "wooden_useless_door":
                        return BlockInit.USELESS_OAK_DOOR.asItem();
                    case "wooden_useless_trapdoor":
                        return BlockInit.USELESS_OAK_TRAPDOOR.asItem();
                    case "useless_pressure_plate":
                        return BlockInit.USELESS_OAK_PRESSURE_PLATE.asItem();
                    case "useless_button":
                        return BlockInit.USELESS_OAK_BUTTON.asItem();
                    case "useless_sign":
                        return ItemInit.USELESS_OAK_SIGN.asItem();
                    case "useless_boat":
                        return ItemInit.USELESS_OAK_BOAT.asItem();
                    case "useless_chest":
                        return BlockInit.USELESS_OAK_CHEST.asItem();
                    default:
                        return null;
                }
            });
        }

}
