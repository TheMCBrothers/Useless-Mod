package tk.themcbros.uselessmod.items;

import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.entity.GrenadeEntity;

public class GrenadeItem extends Item {

	public GrenadeItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerEntity, Hand handIn) {
		ItemStack stack = playerEntity.getHeldItem(handIn);
		if (!playerEntity.abilities.isCreativeMode) {
			stack.shrink(1);
		}

		worldIn.playSound((PlayerEntity)null, playerEntity.posX, playerEntity.posY, playerEntity.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerEntity.getCooldownTracker().setCooldown(this, 20);
		if (!worldIn.isRemote) {
			GrenadeEntity grenadeEntity = new GrenadeEntity(worldIn, playerEntity);
			grenadeEntity.func_213884_b(stack);
			grenadeEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(grenadeEntity);
		}

		playerEntity.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}

}
