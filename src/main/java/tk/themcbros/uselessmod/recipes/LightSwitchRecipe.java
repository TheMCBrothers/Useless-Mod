package tk.themcbros.uselessmod.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.lists.ModItems;

public class LightSwitchRecipe extends SpecialRecipe {

	public LightSwitchRecipe(ResourceLocation location) {
		super(location);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		boolean matches = false;
		
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(!stack.isEmpty() && stack.getItem() == ModItems.LIGHT_SWITCH || stack.getItem() == ModItems.LIGHT_SWITCH_BLOCK)
				matches = true;
		}
		
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		ItemStack recipeOutput = ItemStack.EMPTY;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.getItem() == ModItems.LIGHT_SWITCH) 
				recipeOutput = new ItemStack(ModItems.LIGHT_SWITCH_BLOCK);
			else if(stack.getItem() == ModItems.LIGHT_SWITCH_BLOCK) 
				recipeOutput = new ItemStack(ModItems.LIGHT_SWITCH);
			
			if(stack.hasTag()) recipeOutput.setTag(stack.getTag());
		}
		return recipeOutput;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height > 1;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeSerializers.LIGHT_SWITCH;
	}

}
