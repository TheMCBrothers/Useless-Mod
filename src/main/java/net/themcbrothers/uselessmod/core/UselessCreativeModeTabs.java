package net.themcbrothers.uselessmod.core;

import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.AirBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.util.WallClosetRecipeManager;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public final class UselessCreativeModeTabs {
    static void register() {
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = Registration.CREATIVE_MODE_TABS.register("a_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod"))
                    .icon(() -> new ItemStack(UselessItems.USELESS_INGOT.value()))
                    .withTabsAfter(UselessCreativeModeTabs.COFFEE_TAB.getKey(), UselessCreativeModeTabs.PAINT_TAB.getKey(), UselessCreativeModeTabs.WALL_CLOSET_TAB.getKey())
                    .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COFFEE_TAB = Registration.CREATIVE_MODE_TABS.register("coffee",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.coffee"))
                    .icon(() -> new ItemStack(UselessBlocks.COFFEE_MACHINE))
                    .withTabsBefore(UselessCreativeModeTabs.MAIN_TAB.getKey())
                    .withTabsAfter(UselessCreativeModeTabs.PAINT_TAB.getKey(), UselessCreativeModeTabs.WALL_CLOSET_TAB.getKey())
                    .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PAINT_TAB = Registration.CREATIVE_MODE_TABS.register("paint_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.paint"))
                    .icon(() -> new ItemStack(UselessItems.PAINT_BRUSH.value()))
                    .withTabsBefore(UselessCreativeModeTabs.MAIN_TAB.getKey(), UselessCreativeModeTabs.COFFEE_TAB.getKey())
                    .withTabsAfter(UselessCreativeModeTabs.WALL_CLOSET_TAB.getKey())
                    .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WALL_CLOSET_TAB = Registration.CREATIVE_MODE_TABS.register("wall_closet",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.wall_closet"))
                    .icon(() -> new ItemStack(UselessBlocks.WALL_CLOSET))
                    .withTabsBefore(UselessCreativeModeTabs.MAIN_TAB.getKey(), UselessCreativeModeTabs.COFFEE_TAB.getKey(), UselessCreativeModeTabs.PAINT_TAB.getKey())
                    .build());


    @SubscribeEvent
    static void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MAIN_TAB.get()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();

//            Registration.BLOCKS.getEntries().stream().map(Holder::value).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);
            Registration.ITEMS.getEntries().stream().map(Holder::value).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);

            // remove some items
            List<ItemLike> ignoredByMainTab = Stream.of(UselessBlocks.PAINTED_WOOL, UselessBlocks.PAINT_BUCKET, UselessItems.PAINT_BRUSH, UselessItems.BUCKET_PAINT,
                    UselessItems.COFFEE_BEANS, UselessItems.COFFEE_SEEDS, UselessBlocks.COFFEE_MACHINE, UselessBlocks.CUP, UselessBlocks.CUP_COFFEE,
                    UselessBlocks.WALL_CLOSET).map(ItemLike::asItem).collect(Collectors.toList());
            itemStacks.removeIf(stack -> ignoredByMainTab.contains(stack.getItem()));

            // add the list to the tab
            event.acceptAll(itemStacks);
        }

        // Paint stuff
        if (event.getTab() == PAINT_TAB.get()) {
            event.accept(UselessBlocks.PAINT_BUCKET);

            List<Triple<ItemStack, ItemStack, ItemStack>> coloredItems = new ArrayList<>();

            for (DyeColor color : DyeColor.values()) {
                // painted wool
                final ItemStack stackWool = new ItemStack(UselessBlocks.PAINTED_WOOL);
                applyColorComponentToStack(stackWool, color);

                // paint brush
                final ItemStack stackBrush = new ItemStack(UselessItems.PAINT_BRUSH.value());
                applyColorComponentToStack(stackBrush, color);
                stackBrush.setDamageValue(0);

                // bucket with paint
                final ItemStack stackBucket = new ItemStack(UselessItems.BUCKET_PAINT.value());
                applyColorComponentToStack(stackBucket, color);

                coloredItems.add(Triple.of(stackWool, stackBrush, stackBucket));
            }

            coloredItems.stream().map(Triple::getLeft).forEach(event::accept);
            coloredItems.stream().map(Triple::getMiddle).forEach(event::accept);
            coloredItems.stream().map(Triple::getRight).forEach(event::accept);
            coloredItems.clear();
        }

        // Coffee stuff
        if (event.getTab() == COFFEE_TAB.get()) {
            event.accept(UselessBlocks.COFFEE_MACHINE);
            event.accept(UselessItems.COFFEE_SEEDS);
            event.accept(UselessItems.COFFEE_BEANS);
            event.accept(UselessBlocks.CUP);
            event.acceptAll(UselessRegistries.COFFEE_REGISTRY.stream().map(CoffeeUtils::createCoffeeStack).collect(Collectors.toList()));
        }

        // Wall Closets
        if (event.getTab() == WALL_CLOSET_TAB.get()) {
            BuiltInRegistries.BLOCK.stream()
                    .filter(WallClosetRecipeManager::isValidMaterial)
                    .filter(resourceKeyBlockEntry -> !(WallClosetRecipeManager.getSlab(resourceKeyBlockEntry) instanceof AirBlock))
                    .map(BuiltInRegistries.BLOCK::getKey)
                    .map(blockRegistryName -> {
                        final CompoundTag tag = new CompoundTag();
                        tag.putString("Material", blockRegistryName.toString());
                        final ItemStack stack = new ItemStack(UselessBlocks.WALL_CLOSET);
                        BlockItem.setBlockEntityData(stack, UselessBlockEntityTypes.WALL_CLOSET.get(), tag);
                        return stack;
                    }).forEach(event::accept);
        }

        // Add Spawn Eggs
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(UselessItems.USELESS_SKELETON_SPAWN_EGG);
            event.accept(UselessItems.USELESS_CHICKEN_SPAWN_EGG);
            event.accept(UselessItems.USELESS_COW_SPAWN_EGG);
            event.accept(UselessItems.USELESS_PIG_SPAWN_EGG);
            event.accept(UselessItems.USELESS_SHEEP_SPAWN_EGG);
        }
    }

    private static void applyColorComponentToStack(ItemStack stack, DyeColor color) {
        float[] colors = color.getTextureDiffuseColors();
        int r = (int) (colors[0] * 255.0F);
        int g = (int) (colors[1] * 255.0F);
        int b = (int) (colors[2] * 255.0F);

        stack.set(UselessDataComponents.COLOR.get(), (r << 16) + (g << 8) + b);
    }

    /**
     * Converts an item to a list of item stacks for creative tabs
     *
     * @param itemLike Item like
     * @return List of item or empty list
     */
    private static NonNullList<ItemStack> convert(ItemLike itemLike) {
        NonNullList<ItemStack> itemStacks = NonNullList.create();

        if (itemLike.asItem() != Items.AIR) {
            itemStacks.add(new ItemStack(itemLike));
        }

        return itemStacks;
    }
}
