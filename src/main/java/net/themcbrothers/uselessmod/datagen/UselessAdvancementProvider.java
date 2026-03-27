package net.themcbrothers.uselessmod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.world.level.biome.UselessBiomes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.id;

public class UselessAdvancementProvider implements AdvancementSubProvider {
    private DisplayInfo info(ItemLike icon, String name, @Nullable Identifier background, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden) {
        return new DisplayInfo(new ItemStack(icon.asItem()), UselessMod.translate("advancement", name + ".title"),
                UselessMod.translate("advancement", name + ".description"), Optional.ofNullable(background).map(ClientAsset.ResourceTexture::new), type, showToast, announceChat, hidden);
    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> writer) {
        HolderGetter<Biome> holderGetter = registries.lookupOrThrow(Registries.BIOME);

        AdvancementHolder root = Advancement.Builder.advancement().display(info(UselessItems.USELESS_INGOT, "root", id("gui/advancements/backgrounds/uselessmod"), AdvancementType.TASK, false, false, false)).addCriterion("what", InventoryChangeTrigger.TriggerInstance.hasItems(UselessItems.USELESS_INGOT)).save(writer, id("root"));
        Advancement.Builder.advancement().display(info(UselessBlocks.USELESS_ORE, "mine_ore", null, AdvancementType.TASK, true, true, false)).parent(root).addCriterion("has_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UselessItems.USELESS_INGOT)).save(writer, id("mine_ore"));
        AdvancementHolder biome = Advancement.Builder.advancement().display(info(UselessBlocks.USELESS_OAK_SAPLING, "visit_useless_forest", null, AdvancementType.TASK, true, true, false)).parent(root).addCriterion("useless_forest", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(holderGetter.getOrThrow(UselessBiomes.USELESS_FOREST)))).save(writer, id("visit_useless_forest"));
        Advancement.Builder.advancement().display(info(UselessBlocks.USELESS_ROSE, "collect_roses", null, AdvancementType.GOAL, true, true, false)).parent(biome).addCriterion("has_roses", InventoryChangeTrigger.TriggerInstance.hasItems(UselessBlocks.RED_ROSE, UselessBlocks.BLUE_ROSE, UselessBlocks.USELESS_ROSE)).save(writer, id("collect_roses"));
    }
}
