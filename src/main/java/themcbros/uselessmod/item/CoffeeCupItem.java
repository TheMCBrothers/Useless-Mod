package themcbros.uselessmod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class CoffeeCupItem extends BlockItem {

    public CoffeeCupItem() {
        super(BlockInit.COFFEE_CUP.get(), new Properties().group(UselessMod.GROUP).maxStackSize(1));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            for (CoffeeType coffeeType : UselessRegistries.COFFEE_TYPES) {
                items.add(this.getStack(coffeeType));
            }
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        CoffeeType coffeeType = this.getCoffeeType(stack);
        if (coffeeType != null && coffeeType.doesCurePotionEffects() && !worldIn.isRemote)
            entityLiving.curePotionEffects(stack);

        if (entityLiving instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }

        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
        }

        return stack.isEmpty() ? new ItemStack(ItemInit.CUP.get()) : stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setActiveHand(handIn);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ActionResultType actionresulttype = context.getHand() == Hand.MAIN_HAND ? this.tryPlace(new BlockItemUseContext(context)) : ActionResultType.FAIL;
        return actionresulttype != ActionResultType.SUCCESS && context.getPlayer() != null
                ? this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType()
                : actionresulttype;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemInit.CUP.get());
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        CoffeeType type = this.getCoffeeType(stack);
        if (type != null)
            return Util.makeTranslationKey("coffee", type.getRegistryName());
        return super.getTranslationKey(stack);
    }

    @Nullable
    public CoffeeType getCoffeeType(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            String name = itemStack.getOrCreateTag().getString("CoffeeType");
            return CoffeeType.byId(name);
        }
        return null;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        CoffeeType type = this.getCoffeeType(stack);
        return super.hasEffect(stack) || (type != null && type.hasGlint());
    }

    public ItemStack getStack(@Nonnull CoffeeType coffeeType) {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().putString("CoffeeType", Objects.requireNonNull(coffeeType.getRegistryName()).toString());
        return stack;
    }

    @Override
    public String getTranslationKey() {
        return this.getDefaultTranslationKey();
    }

}
