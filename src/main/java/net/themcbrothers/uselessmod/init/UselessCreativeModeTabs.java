package net.themcbrothers.uselessmod.init;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.AirBlock;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.util.WallClosetRecipeManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public final class UselessCreativeModeTabs {
    static void register() {
    }

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = Registration.CREATIVE_MODE_TABS.register("a_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod"))
                    .icon(() -> new ItemStack(ModItems.USELESS_INGOT))
                    .build());
    public static final RegistryObject<CreativeModeTab> COFFEE_TAB = Registration.CREATIVE_MODE_TABS.register("coffee",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.coffee"))
                    .icon(() -> new ItemStack(ModBlocks.COFFEE_MACHINE))
                    .build());
    public static final RegistryObject<CreativeModeTab> PAINT_TAB = Registration.CREATIVE_MODE_TABS.register("paint_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.paint"))
                    .icon(() -> new ItemStack(ModItems.PAINT_BRUSH))
                    .build());
    public static final RegistryObject<CreativeModeTab> WALL_CLOSET_TAB = Registration.CREATIVE_MODE_TABS.register("wall_closet",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.wall_closet"))
                    .icon(() -> new ItemStack(ModBlocks.WALL_CLOSET))
                    .build());


    @SubscribeEvent
    static void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MAIN_TAB.get()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();

            Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);
            Registration.ITEMS.getEntries().stream().map(RegistryObject::get).map(UselessCreativeModeTabs::convert).forEach(itemStacks::addAll);

            // remove some items
            List<ItemLike> ignoredByMainTab = Stream.of(ModBlocks.PAINTED_WOOL, ModBlocks.PAINT_BUCKET, ModItems.PAINT_BRUSH,
                    ModItems.COFFEE_BEANS, ModItems.COFFEE_SEEDS, ModBlocks.COFFEE_MACHINE, ModBlocks.CUP, ModBlocks.CUP_COFFEE,
                    ModBlocks.WALL_CLOSET).map(ItemLike::asItem).collect(Collectors.toList());
            itemStacks.removeIf(stack -> ignoredByMainTab.contains(stack.getItem()));

            // add the list to the tab
            event.acceptAll(itemStacks);
        }

        // Paint stuff
        if (event.getTab() == PAINT_TAB.get()) {
            event.accept(() -> ModBlocks.PAINT_BUCKET);

            // painted wool
            for (DyeColor color : DyeColor.values()) {
                final ItemStack stack = new ItemStack(ModBlocks.PAINTED_WOOL);
                CompoundTag tag = getTagWithColorFromDyeColor(color);
                BlockItem.setBlockEntityData(stack, ModBlockEntityTypes.PAINTED_WOOL.get(), tag);
                event.accept(stack);
            }

            // paint brush
            for (DyeColor color : DyeColor.values()) {
                final ItemStack stack = new ItemStack(ModItems.PAINT_BRUSH);
                stack.setTag(getTagWithColorFromDyeColor(color));
                stack.setDamageValue(0);
                event.accept(stack);
            }
        }

        // Coffee stuff
        if (event.getTab() == COFFEE_TAB.get()) {
            event.accept(() -> ModBlocks.COFFEE_MACHINE);
            event.accept(() -> ModItems.COFFEE_SEEDS);
            event.accept(() -> ModItems.COFFEE_BEANS);
            event.accept(() -> ModBlocks.CUP);
            event.acceptAll(UselessRegistries.coffeeRegistry.get().getValues().stream().map(CoffeeUtils::createCoffeeStack).collect(Collectors.toList()));
        }

        // Wall Closets
        if (event.getTab() == WALL_CLOSET_TAB.get()) {
            ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(WallClosetRecipeManager::isValidMaterial)
                    .filter(resourceKeyBlockEntry -> !(WallClosetRecipeManager.getSlab(resourceKeyBlockEntry.getValue()) instanceof AirBlock))
                    .map(Map.Entry::getKey)
                    .map(ResourceKey::location)
                    .map(blockRegistryName -> {
                        final CompoundTag tag = new CompoundTag();
                        tag.putString("Material", blockRegistryName.toString());
                        final ItemStack stack = new ItemStack(ModBlocks.WALL_CLOSET);
                        BlockItem.setBlockEntityData(stack, ModBlockEntityTypes.WALL_CLOSET.get(), tag);
                        return stack;
                    }).forEach(event::accept);
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
