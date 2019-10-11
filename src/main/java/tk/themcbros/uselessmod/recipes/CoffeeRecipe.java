package tk.themcbros.uselessmod.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

public class CoffeeRecipe implements IRecipe<IInventory> {

	private final ResourceLocation id;
	private final String group;
	private final Ingredient beansIngredient;
	private final Ingredient cupIngredient;
	private final Ingredient ingredient;
	private final ItemStack result;
	private final int cookTime;
	
	public CoffeeRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int cookTime) {
		this.id = id;
		this.group = group;
		this.beansIngredient = Ingredient.fromStacks(new ItemStack(ModItems.COFFEE_BEANS, CoffeeMachineTileEntity.COFFEE_BEANS_PER_COFFEE));
		this.cupIngredient = Ingredient.fromItems(ModItems.CUP);
		this.ingredient = ingredient;
		this.result = result;
		this.cookTime = cookTime;
	}
	
	@Override
	public String getGroup() {
		return group;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.beansIngredient);
		nonnulllist.add(this.cupIngredient);
		nonnulllist.add(this.ingredient);
		return nonnulllist;
	}
	
	public int getCookTime() {
		return cookTime;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(3)) && this.cupIngredient.test(inv.getStackInSlot(2)) && this.beansIngredient.test(inv.getStackInSlot(1));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return this.result.copy();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeSerializers.COFFEE;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.COFFEE_MACHINE);
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypes.COFFEE;
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<CoffeeRecipe> {

		public CoffeeRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient")
					? JSONUtils.getJsonArray(json, "ingredient")
					: JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
			ItemStack result = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), true);
			int i = JSONUtils.getInt(json, "cooktime", 200);
			return new CoffeeRecipe(recipeId, s, ingredient, result, i);
		}
		
		public CoffeeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient = Ingredient.read(buffer);
			ItemStack result = buffer.readItemStack();
			int i = buffer.readVarInt();
			return new CoffeeRecipe(recipeId, s, ingredient, result, i);
		}

		public void write(PacketBuffer buffer, CoffeeRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient.write(buffer);
			buffer.writeItemStack(recipe.result);
			buffer.writeVarInt(recipe.cookTime);
		}

	}

	@Override
	public boolean canFit(int w, int h) {
		return true;
	}


}
