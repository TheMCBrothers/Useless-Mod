package tk.themcbros.uselessmod.compat.farmingforblockheads;

import mcp.MethodsReturnNonnullByDefault;
import net.blay09.mods.farmingforblockheads.api.FarmingForBlockheadsAPI;
import net.blay09.mods.farmingforblockheads.api.IMarketRegistryDefaultHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;

import javax.annotation.ParametersAreNonnullByDefault;

public class FarmingForBlockheadsCompat {

    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public static void init() {

        FarmingForBlockheadsAPI.registerMarketDefaultHandler("UselessMod Flowers", new IMarketRegistryDefaultHandler() {
            @Override
            public void register(ItemStack defaultPayment, int defaultAmount) {
                final Block[] FLOWERS = new Block[]{
                        ModBlocks.RED_ROSE,
                        ModBlocks.BLUE_ROSE
                };

                for (Block flower : FLOWERS) {
                    FarmingForBlockheadsAPI.registerMarketEntry(new ItemStack(flower, defaultAmount), defaultPayment, FarmingForBlockheadsAPI.getMarketCategoryFlowers());
                }
            }

            @Override
            public boolean isEnabledByDefault() {
                return true;
            }

            @Override
            public ItemStack getDefaultPayment() {
                return new ItemStack(Items.EMERALD);
            }
        });

        FarmingForBlockheadsAPI.registerMarketDefaultHandler("UselessMod Seeds", new IMarketRegistryDefaultHandler() {
            @Override
            public void register(ItemStack defaultPayment, int defaultAmount) {
                final Item[] SEEDS = new Item[]{
                        ModItems.USELESS_WHEAT_SEEDS,
                        ModItems.COFFEE_SEEDS
                };

                for (Item seed : SEEDS) {
                    FarmingForBlockheadsAPI.registerMarketEntry(new ItemStack(seed, defaultAmount), defaultPayment, FarmingForBlockheadsAPI.getMarketCategorySeeds());
                }
            }

            @Override
            public boolean isEnabledByDefault() {
                return true;
            }

            @Override
            public ItemStack getDefaultPayment() {
                return new ItemStack(Items.EMERALD);
            }
        });

    }

}
