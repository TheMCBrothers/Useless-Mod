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
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class CompressorRecipe implements IRecipe<IInventory> {

	private final ResourceLocation id;
	private final String group;
	private final Ingredient ingredient;
	private final ItemStack result;
	private final float experience;
	private final int compressTime;
	
	public CompressorRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int crushTime) {
		this.id = id;
		this.group = group;
		this.ingredient = ingredient;
		this.result = result;
		this.experience = experience;
		this.compressTime = crushTime;
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
	
	public float getExperience() {
		return experience;
	}
	
	public int getCompressTime() {
		return compressTime;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
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
		return RecipeSerializers.COMPRESSING;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.COMPRESSOR);
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypes.COMPRESSING;
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<CompressorRecipe> {

		public CompressorRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient")
					? JSONUtils.getJsonArray(json, "ingredient")
					: JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
			String s1 = JSONUtils.getString(json, "result");
			ResourceLocation resourcelocation = new ResourceLocation(s1);
			ItemStack result = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
				return new IllegalStateException("Item: " + s1 + " does not exist");
			}));
			float f = JSONUtils.getFloat(json, "experience", 0.0F);
			int i = JSONUtils.getInt(json, "compresstime", 200);
			return new CompressorRecipe(recipeId, s, ingredient, result, f, i);
		}
		
		public CompressorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient = Ingredient.read(buffer);
			ItemStack result = buffer.readItemStack();
			float f = buffer.readFloat();
			int i = buffer.readVarInt();
			return new CompressorRecipe(recipeId, s, ingredient, result, f, i);
		}

		public void write(PacketBuffer buffer, CompressorRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient.write(buffer);
			buffer.writeItemStack(recipe.result);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.compressTime);
		}

	}
	
	@Override
	public boolean canFit(int w, int h) {
		// TODO Auto-generated method stub
		return true;
	}


}
