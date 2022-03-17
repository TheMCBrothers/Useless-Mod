package net.themcbrothers.uselessmod.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessAdvancementProvider extends AdvancementProvider {
    public UselessAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement root = Advancement.Builder.advancement().display(info(ModItems.USELESS_INGOT.get(), "root", rl("textures/gui/advancements/backgrounds/uselessmod.png"), FrameType.TASK, false, false, false)).addCriterion("what", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT.get())).save(consumer, rl("root"), fileHelper);
        Advancement.Builder.advancement().display(info(ModBlocks.USELESS_ORE.get(), "mine_ore", null, FrameType.TASK, true, true, false)).parent(root).addCriterion("has_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.USELESS_INGOT.get())).save(consumer, rl("mine_ore"), fileHelper);
    }

    private DisplayInfo info(ItemLike icon, String name, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceChat, boolean hidden) {
        return new DisplayInfo(new ItemStack(icon.asItem()), new TranslatableComponent("advancement.uselessmod." + name + ".title"),
                new TranslatableComponent("advancement.uselessmod." + name + ".description"), background, frame, showToast, announceChat, hidden);
    }
}
