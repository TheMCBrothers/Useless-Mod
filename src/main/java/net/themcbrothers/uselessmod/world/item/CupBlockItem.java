package net.themcbrothers.uselessmod.world.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import org.jetbrains.annotations.Nullable;

public class CupBlockItem extends BlockItem {
    private static final int DRINK_DURATION = 32;

    private final boolean drinkable;

    public CupBlockItem(Block block, Properties properties, boolean drinkable) {
        super(block, properties);
        this.drinkable = drinkable;
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        if (this.drinkable && this.allowdedIn(tab)) {
            UselessRegistries.coffeeRegistry.get().getValues().stream()
                    .map(CoffeeUtils::createCoffeeStack).forEach(items::add);
        } else {
            super.fillItemCategory(tab, items);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!this.drinkable) {
            return stack;
        }

        Player player = entity instanceof Player ? (Player) entity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if (!level.isClientSide) {
            for (MobEffectInstance mobeffectinstance : CoffeeUtils.getMobEffects(stack)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, entity, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    entity.addEffect(new MobEffectInstance(mobeffectinstance));
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(ModBlocks.CUP);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(ModBlocks.CUP));
            }
        }

        level.gameEvent(entity, GameEvent.DRINKING_FINISH, entity.eyeBlockPosition());
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.drinkable ? DRINK_DURATION : 0;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return this.drinkable ? UseAnim.DRINK : UseAnim.NONE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.drinkable ? ItemUtils.startUsingInstantly(level, player, hand) : super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionResult = this.place(new BlockPlaceContext(context));
        if (!interactionResult.consumesAction() && context.getPlayer() != null) {
            InteractionResult result = this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
            return result == InteractionResult.CONSUME ? InteractionResult.CONSUME_PARTIAL : result;
        } else {
            return interactionResult;
        }
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
    public String getDescriptionId(ItemStack stack) {
        return CoffeeUtils.getCoffeeType(stack)
                .map(CoffeeType::getDescriptionId)
                .orElse(super.getDescriptionId(stack));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return this.drinkable;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.drinkable ? new ItemStack(ModBlocks.CUP) : ItemStack.EMPTY;
    }

    @Nullable
    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return CoffeeUtils.getCoffeeType(itemStack)
                .map(ForgeRegistryEntry::getRegistryName)
                .map(ResourceLocation::getNamespace)
                .orElse(super.getCreatorModId(itemStack));
    }
}
