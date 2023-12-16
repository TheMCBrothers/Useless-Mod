package net.themcbrothers.uselessmod.init;

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
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.util.WallClosetRecipeManager;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public final class UselessCreativeModeTabs {
    static void register() {
    }

    public static final Supplier<CreativeModeTab> MAIN_TAB = Registration.CREATIVE_MODE_TABS.register("a_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod"))
                    .icon(() -> new ItemStack(ModItems.USELESS_INGOT.value()))
                    .build());
    public static final Supplier<CreativeModeTab> COFFEE_TAB = Registration.CREATIVE_MODE_TABS.register("coffee",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.coffee"))
                    .icon(() -> new ItemStack(ModBlocks.COFFEE_MACHINE))
                    .build());
    public static final Supplier<CreativeModeTab> PAINT_TAB = Registration.CREATIVE_MODE_TABS.register("paint_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.paint"))
                    .icon(() -> new ItemStack(ModItems.PAINT_BRUSH.value()))
                    .build());
    public static final Supplier<CreativeModeTab> WALL_CLOSET_TAB = Registration.CREATIVE_MODE_TABS.register("wall_closet",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.wall_closet"))
                    .icon(() -> new ItemStack(ModBlocks.WALL_CLOSET))
                    .build());


    @SubscribeEvent
    static void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MAIN_TAB.get()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();

            Registration.BLOCKS.getEntries().stream().map(Holder::value).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);
            Registration.ITEMS.getEntries().stream().map(Holder::value).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);

            // remove some items
            List<ItemLike> ignoredByMainTab = Stream.of(ModBlocks.PAINTED_WOOL, ModBlocks.PAINT_BUCKET, ModItems.PAINT_BRUSH, ModItems.BUCKET_PAINT,
                    ModItems.COFFEE_BEANS, ModItems.COFFEE_SEEDS, ModBlocks.COFFEE_MACHINE, ModBlocks.CUP, ModBlocks.CUP_COFFEE,
                    ModBlocks.WALL_CLOSET).map(ItemLike::asItem).collect(Collectors.toList());
            itemStacks.removeIf(stack -> ignoredByMainTab.contains(stack.getItem()));

            // add the list to the tab
            event.acceptAll(itemStacks);
        }

        // Paint stuff
        if (event.getTab() == PAINT_TAB.get()) {
            event.accept(ModBlocks.PAINT_BUCKET);

            List<Triple<ItemStack, ItemStack, ItemStack>> coloredItems = new ArrayList<>();

            for (DyeColor color : DyeColor.values()) {
                // painted wool
                final ItemStack stackWool = new ItemStack(ModBlocks.PAINTED_WOOL);
                CompoundTag tag = getTagWithColorFromDyeColor(color);
                BlockItem.setBlockEntityData(stackWool, ModBlockEntityTypes.PAINTED_WOOL.get(), tag);

                // paint brush
                final ItemStack stackBrush = new ItemStack(ModItems.PAINT_BRUSH.value());
                stackBrush.setTag(getTagWithColorFromDyeColor(color));
                stackBrush.setDamageValue(0);

                // bucket with paint
                final ItemStack stackBucket = new ItemStack(ModItems.BUCKET_PAINT.value());
                stackBucket.setTag(getTagWithColorFromDyeColor(color));

                coloredItems.add(Triple.of(stackWool, stackBrush, stackBucket));
            }

            coloredItems.stream().map(Triple::getLeft).forEach(event::accept);
            coloredItems.stream().map(Triple::getMiddle).forEach(event::accept);
            coloredItems.stream().map(Triple::getRight).forEach(event::accept);
            coloredItems.clear();
        }

        // Coffee stuff
        if (event.getTab() == COFFEE_TAB.get()) {
            event.accept(ModBlocks.COFFEE_MACHINE);
            event.accept(ModItems.COFFEE_SEEDS);
            event.accept(ModItems.COFFEE_BEANS);
            event.accept(ModBlocks.CUP);
            event.acceptAll(UselessRegistries.coffeeRegistry.stream().map(CoffeeUtils::createCoffeeStack).collect(Collectors.toList()));
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
                        final ItemStack stack = new ItemStack(ModBlocks.WALL_CLOSET);
                        BlockItem.setBlockEntityData(stack, ModBlockEntityTypes.WALL_CLOSET.get(), tag);
                        return stack;
                    }).forEach(event::accept);
        }

        // Add Spawn Eggs
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.USELESS_SKELETON_SPAWN_EGG);
            event.accept(ModItems.USELESS_CHICKEN_SPAWN_EGG);
            event.accept(ModItems.USELESS_COW_SPAWN_EGG);
            event.accept(ModItems.USELESS_PIG_SPAWN_EGG);
            event.accept(ModItems.USELESS_SHEEP_SPAWN_EGG);
        }
    }

    private static CompoundTag getTagWithColorFromDyeColor(DyeColor color) {
        float[] colors = color.getTextureDiffuseColors();
        int r = (int) (colors[0] * 255.0F);
        int g = (int) (colors[1] * 255.0F);
        int b = (int) (colors[2] * 255.0F);

        CompoundTag tag = new CompoundTag();
        tag.putInt("Color", (r << 16) + (g << 8) + b);

        return tag;
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
