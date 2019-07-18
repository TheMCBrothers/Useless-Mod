package tk.themcbros.uselessmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ItemUselessSoup extends Item {
	
	public ItemUselessSoup(Properties builder) {
		super(builder);
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		super.onItemUseFinish(stack, worldIn, entityLiving);
		return new ItemStack(Items.BOWL);
	}

}
