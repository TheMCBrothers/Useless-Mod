package net.themcbrothers.uselessmod.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.world.level.biome.UselessBiomes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement().display(info(ModItems.USELESS_INGOT, "root", rl("textures/gui/advancements/backgrounds/uselessmod.png"), FrameType.TASK, false, false, false)).addCriterion("what", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT)).save(saver, rl("root"), existingFileHelper);
        Advancement.Builder.advancement().display(info(ModBlocks.USELESS_ORE, "mine_ore", null, FrameType.TASK, true, true, false)).parent(root).addCriterion("has_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT)).save(saver, rl("mine_ore"), existingFileHelper);
        Advancement biome = Advancement.Builder.advancement().display(info(ModBlocks.USELESS_OAK_SAPLING, "visit_useless_forest", null, FrameType.TASK, true, true, false)).parent(root).addCriterion("useless_forest", PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(UselessBiomes.USELESS_FOREST))).save(saver, rl("visit_useless_forest"), existingFileHelper);
        Advancement.Builder.advancement().display(info(ModBlocks.USELESS_ROSE, "collect_roses", null, FrameType.GOAL, true, true, false)).parent(biome).addCriterion("has_roses", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RED_ROSE, ModBlocks.BLUE_ROSE, ModBlocks.USELESS_ROSE)).save(saver, rl("collect_roses"), existingFileHelper);
    }

    private DisplayInfo info(ItemLike icon, String name, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceChat, boolean hidden) {
        return new DisplayInfo(new ItemStack(icon.asItem()), UselessMod.translate("advancement", name + ".title"),
                UselessMod.translate("advancement", name + ".description"), background, frame, showToast, announceChat, hidden);
    }
}
