package tk.themcbros.uselessmod.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.CoffeeType;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeCupItem extends Item {

	public CoffeeCupItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stack = new ItemStack(this);
		stack.setTag(new CompoundNBT());
		PotionUtils.addPotionToItemStack(stack, CoffeeType.BLACK.getPotion());
		stack.getTag().putString("CoffeeType", CoffeeType.BLACK.getName());
		return stack;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity) entityLiving : null;
		if (playerentity == null || !playerentity.abilities.isCreativeMode) {
			stack.shrink(1);
		}

		if (playerentity instanceof ServerPlayerEntity) {
			CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) playerentity, stack);
		}

		if (!worldIn.isRemote) {
			for (EffectInstance effectinstance : PotionUtils.getEffectsFromStack(stack)) {
				if (effectinstance.getPotion().isInstant()) {
					effectinstance.getPotion().affectEntity(playerentity, playerentity, entityLiving,
							effectinstance.getAmplifier(), 1.0D);
				} else {
					entityLiving.addPotionEffect(new EffectInstance(effectinstance));
				}
			}
		}

		if (playerentity != null) {
			playerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (playerentity == null || !playerentity.abilities.isCreativeMode) {
			if (stack.isEmpty()) {
				return new ItemStack(ModItems.CUP);
			}

			if (playerentity != null) {
				playerentity.inventory.addItemStackToInventory(new ItemStack(ModItems.CUP));
			}
		}
		return stack;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return stack.hasTag() && stack.getTag().contains("CoffeeType") 
				? this.getTranslationKey() + "." + stack.getTag().getString("CoffeeType")
				: this.getTranslationKey();
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return super.hasEffect(stack) || 
				(stack.hasTag() && stack.getTag().contains("CoffeeType") && stack.getTag().getString("CoffeeType") == CoffeeType.SUGAR.getName());
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(this.isInGroup(group)) {
			for(CoffeeType type : CoffeeType.values()) {
				ItemStack stack = new ItemStack(this);
				stack.setTag(new CompoundNBT());
				PotionUtils.addPotionToItemStack(stack, type.getPotion());
				stack.getTag().putString("CoffeeType", type.getName());
				items.add(stack);
			}
		}
	}
	
}
