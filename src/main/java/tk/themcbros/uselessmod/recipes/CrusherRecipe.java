package tk.themcbros.uselessmod.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class CrusherRecipe implements IRecipe<IInventory> {

	private final ResourceLocation id;
	private final String group;
	private final Ingredient ingredient;
	private final ItemStack result;
	private final ItemStack secondResult;
	private final float secondChance;
	private final float experience;
	private final int crushTime;
	
	public CrusherRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, ItemStack secondResult, float secondChance, float experience, int crushTime) {
		this.id = id;
		this.group = group;
		this.ingredient = ingredient;
		this.result = result;
		this.secondResult = secondResult;
		this.secondChance = secondChance;
		this.experience = experience;
		this.crushTime = crushTime;
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
	
	public int getCrushTime() {
		return crushTime;
	}
	
	public ItemStack getSecondRecipeOutput() {
		return secondResult;
	}
	
	public float getSecondChance() {
		return secondChance;
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
		return RecipeSerializers.CRUSHING;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.CRUSHER);
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypes.CRUSHING;
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<CrusherRecipe> {

		public static ItemStack deserializeItem(JsonObject p_199798_0_) {
			String s = JSONUtils.getString(p_199798_0_, "item");
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(s));
			if (item == null) {
				throw new JsonSyntaxException("Unknown item '" + s + "'");
			} else if (p_199798_0_.has("data")) {
				throw new JsonParseException("Disallowed data tag found");
			} else {
				int i = JSONUtils.getInt(p_199798_0_, "count", 1);
				return new ItemStack(item, i);
			}
		}

		public CrusherRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient")
					? JSONUtils.getJsonArray(json, "ingredient")
					: JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
			ItemStack result = deserializeItem(JSONUtils.getJsonObject(json, "result"));
			ItemStack secondResult = ItemStack.EMPTY; 
			float secondChance = 0.0f;
			if(JSONUtils.hasField(json, "secondresult")) {
				secondResult = deserializeItem(JSONUtils.getJsonObject(json, "secondresult"));
				secondChance = JSONUtils.getInt(json, "secondchance") / 100.0f;
			}
			float f = JSONUtils.getFloat(json, "experience", 0.0F);
			int i = JSONUtils.getInt(json, "crushtime", 200);
			return new CrusherRecipe(recipeId, s, ingredient, result, secondResult, secondChance, f, i);
		}
		
		public CrusherRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient = Ingredient.read(buffer);
			ItemStack result = buffer.readItemStack();
			ItemStack secondResult = buffer.readItemStack();
			float secondChance = buffer.readFloat();
			float f = buffer.readFloat();
			int i = buffer.readVarInt();
			return new CrusherRecipe(recipeId, s, ingredient, result, secondResult, secondChance, f, i);
		}

		public void write(PacketBuffer buffer, CrusherRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient.write(buffer);
			buffer.writeItemStack(recipe.result);
			buffer.writeItemStack(recipe.secondResult);
			buffer.writeFloat(recipe.secondChance);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.crushTime);
		}

	}
	
	@Override
	public boolean canFit(int w, int h) {
		return true;
	}


}
