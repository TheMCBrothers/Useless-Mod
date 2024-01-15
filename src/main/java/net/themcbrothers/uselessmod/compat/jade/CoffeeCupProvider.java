package net.themcbrothers.uselessmod.compat.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum CoffeeCupProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag serverData = accessor.getServerData();
        if (serverData.contains("CoffeeType")) {
            tooltip.append(Component.translatable(serverData.getString("CoffeeType")));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UselessMod.rl("coffee_cup");
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        CupBlockEntity blockEntity = (CupBlockEntity) blockAccessor.getBlockEntity();
        blockEntity.getCoffeeType().map(CoffeeType::getDescriptionId).ifPresent(s -> compoundTag.putString("CoffeeType", s));
    }
}
