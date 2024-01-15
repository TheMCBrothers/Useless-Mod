package net.themcbrothers.uselessmod.compat.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IBoxElement;
import snownee.jade.api.ui.IElementHelper;

public enum PaintedWoolProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig pluginConfig) {
        tooltip.add(Component.literal("Hello there!"));

        if (blockAccessor.getServerData().contains("Color", CompoundTag.TAG_ANY_NUMERIC)) {
//            IElementHelper elementHelper = IElementHelper.get();
//            BoxStyle.GradientBorder style = BoxStyle.getTransparent();
//            style.bgColor = blockAccessor.getServerData().getInt("Color");
//            IBoxElement box = elementHelper.box(tooltip, style);
//            tooltip.add(box);
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor accessor) {
        PaintedWoolBlockEntity blockEntity = (PaintedWoolBlockEntity) accessor.getBlockEntity();
        compoundTag.putInt("Color", blockEntity.getColor());
    }

    @Override
    public ResourceLocation getUid() {
        return UselessMod.rl("painted_wool");
    }
}
