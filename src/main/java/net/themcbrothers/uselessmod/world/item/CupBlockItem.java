package net.themcbrothers.uselessmod.world.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import org.jspecify.annotations.Nullable;

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

    @Override
    public @Nullable String getCreatorModId(HolderLookup.Provider registries, ItemStack itemStack) {
        return CoffeeUtils.getCoffeeType(itemStack)
                .map(UselessRegistries.COFFEE_REGISTRY::getKey)
                .map(Identifier::getNamespace)
                .orElse(super.getCreatorModId(registries, itemStack));
    }
}
