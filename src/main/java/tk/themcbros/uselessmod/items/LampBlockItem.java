package tk.themcbros.uselessmod.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class LampBlockItem extends BlockItem {
	
	private DyeColor color;

	public LampBlockItem(Properties builder, DyeColor color) {
		super(ModBlocks.LAMP, builder);
		this.color = color;
		this.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, color.getName() + "_lamp"));
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(this.isInGroup(group)) {
			items.add(new ItemStack(this));
		}
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return super.getTranslationKey(stack) + "." + this.color.getName();
	}
	
	public DyeColor getColor() {
		return color;
	}

}
