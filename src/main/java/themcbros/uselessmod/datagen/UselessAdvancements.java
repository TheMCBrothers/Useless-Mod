package themcbros.uselessmod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.init.BiomeInit;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class UselessAdvancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement root = Advancement.Builder.builder().withDisplay(BlockInit.USELESS_ORE.get(), title("root"), description("root"), UselessMod.rl("textures/gui/advancements/background.png"), FrameType.TASK, true, false, false).withRequirementsStrategy(IRequirementsStrategy.OR).withCriterion("useless_biome", uselessBiome()).withCriterion("useless_ore", itemTrigger(UselessTags.Items.ORES_USELESS)).register(advancementConsumer, id("root"));

        Advancement useless_ingot = Advancement.Builder.builder().withParent(root).withDisplay(ItemInit.USELESS_INGOT.get(), title("useless_ingot"), description("useless_ingot"), null, FrameType.TASK, true, true, false).withCriterion("useless_ingot", itemTrigger(UselessTags.Items.INGOTS_USELESS)).register(advancementConsumer, id("useless_ingot"));
        Advancement.Builder.builder().withParent(useless_ingot).withDisplay(ItemInit.USELESS_CHESTPLATE.get(), title("useless_armor"), description("useless_armor"), null, FrameType.TASK, true, true, false).withRequirementsStrategy(IRequirementsStrategy.OR).withCriterion("useless_helmet", itemTrigger(ItemInit.USELESS_HELMET)).withCriterion("useless_chestplate", itemTrigger(ItemInit.USELESS_CHESTPLATE)).withCriterion("useless_leggings", itemTrigger(ItemInit.USELESS_LEGGINGS)).withCriterion("useless_boots", itemTrigger(ItemInit.USELESS_BOOTS)).register(advancementConsumer, id("useless_armor"));
        Advancement super_useless_ingot = Advancement.Builder.builder().withParent(root).withDisplay(ItemInit.SUPER_USELESS_INGOT.get(), title("super_useless_ingot"), description("super_useless_ingot"), null, FrameType.TASK, true, true, false).withCriterion("super_useless_ingot", itemTrigger(UselessTags.Items.INGOTS_SUPER_USELESS)).register(advancementConsumer, id("super_useless_ingot"));
        Advancement.Builder.builder().withParent(super_useless_ingot).withDisplay(ItemInit.SUPER_USELESS_CHESTPLATE.get(), title("super_useless_armor"), description("super_useless_armor"), null, FrameType.TASK, true, true, false).withRequirementsStrategy(IRequirementsStrategy.OR).withCriterion("super_useless_helmet", itemTrigger(ItemInit.SUPER_USELESS_HELMET)).withCriterion("super_useless_chestplate", itemTrigger(ItemInit.SUPER_USELESS_CHESTPLATE)).withCriterion("super_useless_leggings", itemTrigger(ItemInit.SUPER_USELESS_LEGGINGS)).withCriterion("super_useless_boots", itemTrigger(ItemInit.SUPER_USELESS_BOOTS)).register(advancementConsumer, id("super_useless_armor"));

        Advancement useless_biome = Advancement.Builder.builder().withParent(root).withDisplay(BlockInit.USELESS_OAK_SAPLING.get(), title("useless_biome"), description("useless_biome"), null, FrameType.TASK, true, true, false).withCriterion("useless_biome", uselessBiome()).register(advancementConsumer, id("useless_biome"));
        Advancement.Builder.builder().withParent(useless_biome).withDisplay(BlockInit.USELESS_ROSE.get(), title("useless_roses"), description("useless_roses"), null, FrameType.GOAL, true, true, false).withCriterion("red_rose", itemTrigger(BlockInit.RED_ROSE)).withCriterion("blue_rose", itemTrigger(BlockInit.BLUE_ROSE)).withCriterion("useless_rose", itemTrigger(BlockInit.USELESS_ROSE)).register(advancementConsumer, id("useless_roses"));
    }

    @Nonnull
    private CriterionInstance uselessBiome() {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, BiomeInit.USELESS_FOREST.getId());
        return PositionTrigger.Instance.forLocation(LocationPredicate.forBiome(key));
    }

    @Nonnull
    private CriterionInstance itemTrigger(@Nonnull ITag<Item> tag) {
        return InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(tag).build());
    }

    @Nonnull
    private CriterionInstance itemTrigger(@Nonnull Supplier<? extends IItemProvider> item) {
        return InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().item(item.get()).build());
    }

    private String id(String name) {
        return UselessMod.MOD_ID + ":" + UselessMod.MOD_ID + "/" + name;
    }

    private ITextComponent title(String name) {
        return new TranslationTextComponent("advancements." + UselessMod.MOD_ID + "." + name + ".title");
    }

    private ITextComponent description(String name) {
        return new TranslationTextComponent("advancements." + UselessMod.MOD_ID + "." + name + ".description");
    }
}
