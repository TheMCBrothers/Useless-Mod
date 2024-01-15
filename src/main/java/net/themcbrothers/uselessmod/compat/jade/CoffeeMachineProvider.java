package net.themcbrothers.uselessmod.compat.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec2;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum CoffeeMachineProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag serverData = accessor.getServerData();
        if (serverData.contains("CookTimeTotal") && serverData.contains("CookTimeTotal")) {
            IElementHelper elements = IElementHelper.get();
            IElement icon = elements.item(new ItemStack(Items.CLOCK), 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1));
            icon.message(null);
            tooltip.add(icon);
            tooltip.append(Component.translatable("mymod.fuel", serverData.getInt("CookTimeTotal")));

            int cookTime = serverData.getInt("CookTime");
            int cookTimeTotal = serverData.getInt("CookTimeTotal");

            if (cookTimeTotal > 0) {
                IElement progress = elements.progress((float) cookTime / (float) cookTimeTotal);
                tooltip.add(progress);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UselessMod.rl("coffee_machine");
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        CoffeeMachineBlockEntity blockEntity = (CoffeeMachineBlockEntity) blockAccessor.getBlockEntity();
        compoundTag.putInt("CookTime", blockEntity.getCookingProgress());
        compoundTag.putInt("CookTimeTotal", blockEntity.getCookingTotalTime());
    }
}
