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
import tk.themcbros.uselessmod.lists.ModBlocks;

public class GlowstoneGeneratorRecipe implements IRecipe<IInventory> {

	private final ResourceLocation id;
	private final String group;
	private final Ingredient ingredient;
	private final int energyProduced;
	private final int cookTime;
	
	public GlowstoneGeneratorRecipe(ResourceLocation id, String group, Ingredient ingredient, int energyProduced, int cookTime) {
		this.id = id;
		this.group = group;
		this.ingredient = ingredient;
		this.energyProduced = energyProduced;
		this.cookTime = cookTime;
	}
	
	@Override
	public String getGroup() {
		return group;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.ingredient);
		return nonnulllist;
	}
	
	public int getCookTime() {
		return cookTime;
	}
	
	public int getEnergyProduced() {
		return energyProduced;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeSerializers.GENERATING;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.GLOWSTONE_GENERATOR);
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypes.GLOWSTONE_GENERATING;
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<GlowstoneGeneratorRecipe> {

		public GlowstoneGeneratorRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient")
					? JSONUtils.getJsonArray(json, "ingredient")
					: JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
			int i = JSONUtils.getInt(json, "result", 200);
			int j = JSONUtils.getInt(json, "cooktime", 200);
			return new GlowstoneGeneratorRecipe(recipeId, s, ingredient, i, j);
		}
		
		public GlowstoneGeneratorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient = Ingredient.read(buffer);
			int i = buffer.readVarInt();
			int j = buffer.readVarInt();
			return new GlowstoneGeneratorRecipe(recipeId, s, ingredient, i, j);
		}

		public void write(PacketBuffer buffer, GlowstoneGeneratorRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient.write(buffer);
			buffer.writeVarInt(recipe.energyProduced);
			buffer.writeVarInt(recipe.cookTime);
		}
	}
	
	@Override
	public boolean canFit(int w, int h) {
		return true;
	}

}
