package net.themcbrothers.uselessmod.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.init.ModBiomes;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessAdvancementProvider extends AdvancementProvider {
    public UselessAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement root = Advancement.Builder.advancement().display(info(ModItems.USELESS_INGOT, "root", rl("textures/gui/advancements/backgrounds/uselessmod.png"), FrameType.TASK, false, false, false)).addCriterion("what", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT)).save(consumer, rl("root"), fileHelper);
        Advancement.Builder.advancement().display(info(ModBlocks.USELESS_ORE, "mine_ore", null, FrameType.TASK, true, true, false)).parent(root).addCriterion("has_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT)).save(consumer, rl("mine_ore"), fileHelper);
        Advancement biome = Advancement.Builder.advancement().display(info(ModBlocks.USELESS_OAK_SAPLING, "visit_useless_forest", null, FrameType.TASK, true, true, false)).parent(root).addCriterion("useless_forest", LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(ModBiomes.USELESS_FOREST.getKey()))).save(consumer, rl("visit_useless_forest"), fileHelper);
        Advancement.Builder.advancement().display(info(ModBlocks.USELESS_ROSE, "collect_roses", null, FrameType.GOAL, true, true, false)).parent(biome).addCriterion("has_roses", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RED_ROSE, ModBlocks.BLUE_ROSE, ModBlocks.USELESS_ROSE)).save(consumer, rl("collect_roses"), fileHelper);
    }

    private DisplayInfo info(ItemLike icon, String name, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceChat, boolean hidden) {
        return new DisplayInfo(new ItemStack(icon.asItem()), new TranslatableComponent("advancement.uselessmod." + name + ".title"),
                new TranslatableComponent("advancement.uselessmod." + name + ".description"), background, frame, showToast, announceChat, hidden);
    }
}
