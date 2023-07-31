package net.themcbrothers.uselessmod.init;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.util.CreativeTabFiller;

import java.util.List;
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
    public static final RegistryObject<CreativeModeTab> WALL_CLOSET_TAB = Registration.CREATIVE_MODE_TABS.register("wall_closet",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.uselessmod.wall_closet"))
                    .icon(() -> new ItemStack(ModBlocks.WALL_CLOSET))
                    .build());


    @SubscribeEvent
    static void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MAIN_TAB.get()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();

            Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).map(UselessCreativeModeTabs::toList).forEach(itemStacks::addAll);
            Registration.ITEMS.getEntries().stream().map(RegistryObject::get).map(UselessCreativeModeTabs::toList).forEach(itemStacks::addAll);

            // remove some items
            List<ItemLike> ignoredByMainTab = Stream.of(ModItems.COFFEE_BEANS, ModItems.COFFEE_SEEDS, ModBlocks.COFFEE_MACHINE, ModBlocks.CUP, ModBlocks.CUP_COFFEE, ModBlocks.WALL_CLOSET).map(ItemLike::asItem).collect(Collectors.toList());
            itemStacks.removeIf(stack -> ignoredByMainTab.contains(stack.getItem()));

            // add the list to the tab
            event.acceptAll(itemStacks);
        }

        if (event.getTab() == COFFEE_TAB.get()) {
            event.accept(() -> ModBlocks.COFFEE_MACHINE);
            event.accept(() -> ModItems.COFFEE_SEEDS);
            event.accept(() -> ModItems.COFFEE_BEANS);
            event.accept(() -> ModBlocks.CUP);
            event.acceptAll(toList(ModBlocks.CUP_COFFEE.asItem()));
        }

        if (event.getTab() == WALL_CLOSET_TAB.get()) {
            event.acceptAll(toList(ModBlocks.WALL_CLOSET.get()));
        }
    }

    private static NonNullList<ItemStack> toList(ItemLike itemLike) {
        NonNullList<ItemStack> itemStacks = NonNullList.create();

        if (itemLike instanceof CreativeTabFiller filler) {
            filler.fillCreativeTab(itemStacks);
        } else if (itemLike.asItem() != Items.AIR) {
            itemStacks.add(new ItemStack(itemLike));
        }

        return itemStacks;
    }
}
