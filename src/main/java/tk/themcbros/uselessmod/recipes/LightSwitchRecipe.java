package tk.themcbros.uselessmod.recipes;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
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
			else if(!stack.isEmpty())
				matches = false;
		}
		
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		ItemStack recipeOutput = ItemStack.EMPTY;
		List<ItemStack> inputs = Lists.newArrayList();
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.getItem() == ModItems.LIGHT_SWITCH) {
				inputs.add(stack);
				recipeOutput = new ItemStack(ModItems.LIGHT_SWITCH_BLOCK);
			}
			else if(stack.getItem() == ModItems.LIGHT_SWITCH_BLOCK) {
				inputs.add(stack);
				recipeOutput = new ItemStack(ModItems.LIGHT_SWITCH);
			}
			
			if(stack.hasTag()) recipeOutput.setTag(stack.getTag());
		}
		
		CompoundNBT compoundNBT = new CompoundNBT();
		List<Long> lights = Lists.newArrayList();
		for(ItemStack inputStack : inputs) {
			if(inputStack.hasTag() && inputStack.getTag().contains("BlockEntityTag", Constants.NBT.TAG_COMPOUND)) {
				if(inputStack.getChildTag("BlockEntityTag").contains("Lights", Constants.NBT.TAG_LONG_ARRAY)) {
					for(Long l : inputStack.getChildTag("BlockEntityTag").getLongArray("Lights")) {
						if(!lights.contains(l)) lights.add(l);
					}
				}
			}
		}
		compoundNBT.putLongArray("Lights", lights);
		recipeOutput.setTag(new CompoundNBT());
		recipeOutput.getTag().put("BlockEntityTag", compoundNBT);
		
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
