package tk.themcbros.uselessmod.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IShapedRecipe;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;

public class ClosetRecipeMaker {

	public static List<IShapedRecipe<? extends IInventory>> createClosetRecipes() {
        List<IShapedRecipe<? extends IInventory>> recipes = new ArrayList<>();
        String group = "uselessmod.closet";
        for(IClosetMaterial beddingId : ClosetRegistry.BEDDINGS.getKeys()) {
            for(IClosetMaterial casingId : ClosetRegistry.CASINGS.getKeys()) {
                
                Ingredient beddingIngredient = beddingId.getIngredient();
                Ingredient casingIngredient = casingId.getIngredient();
                NonNullList<Ingredient> inputs = NonNullList.from(Ingredient.EMPTY,
                    casingIngredient, casingIngredient, casingIngredient,
                    casingIngredient, beddingIngredient, casingIngredient,
                    casingIngredient, casingIngredient, casingIngredient
                );
                ItemStack output = ClosetRegistry.createItemStack(casingId, beddingId);
                
                ResourceLocation id = new ResourceLocation(UselessMod.MOD_ID, output.getTranslationKey());
                ShapedRecipe recipe = new ShapedRecipe(id, group, 3, 3, inputs, output);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
	
}
