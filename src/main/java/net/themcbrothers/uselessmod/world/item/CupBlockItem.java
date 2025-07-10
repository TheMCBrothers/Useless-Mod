package net.themcbrothers.uselessmod.world.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import org.jetbrains.annotations.Nullable;

public class CupBlockItem extends BlockItem {
    private final boolean drinkable;

    public CupBlockItem(Block block, Properties properties, boolean drinkable) {
        super(block, properties);
        this.drinkable = drinkable;
    }

    @Nullable
    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        if (this.drinkable) {
            final Player player = context.getPlayer();
            return player == null || player.isSecondaryUseActive() ? context : null;
        }

        return super.updatePlacementContext(context);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) ||
                CoffeeUtils.getCoffeeType(stack)
                        .map(CoffeeType::isFoil)
                        .orElse(false);
    }

    @Override
    public Component getName(ItemStack stack) {
        return CoffeeUtils.getCoffeeType(stack)
                .map(CoffeeType::getDescriptionId)
                .map(Component::translatable)
                .orElse(Component.translatable(this.descriptionId));
    }

    @Nullable
    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return CoffeeUtils.getCoffeeType(itemStack)
                .map(UselessRegistries.COFFEE_REGISTRY::getKey)
                .map(ResourceLocation::getNamespace)
                .orElse(super.getCreatorModId(itemStack));
    }
}
