package tk.themcbros.uselessmod.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.fluid.Fluid;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class MagmaCrucibleRecipe implements IRecipe<IInventory> {

	private final ResourceLocation id;
	private final String group;
	private final Ingredient ingredient;
	private final FluidStack result;
	private final int cookTime;
	
	public MagmaCrucibleRecipe(ResourceLocation id, String group, Ingredient ingredient, FluidStack result, int cookTime) {
		this.id = id;
		this.group = group;
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
		nonnulllist.add(this.ingredient);
		return nonnulllist;
	}
	
	public int getCookTime() {
		return cookTime;
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
	
	public FluidStack getResult() {
		return result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeSerializers.MAGMA_CRUCIBLE;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.MAGMA_CRUCIBLE);
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypes.MAGMA_CRUCIBLE;
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<MagmaCrucibleRecipe> {

		public static FluidStack deserializeFluid(JsonObject p_199798_0_) {
			String s = JSONUtils.getString(p_199798_0_, "fluid");
			Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(s));
			if (fluid == null) {
				throw new JsonSyntaxException("Unknown item '" + s + "'");
			} else if (p_199798_0_.has("data")) {
				throw new JsonParseException("Disallowed data tag found");
			} else {
				int i = JSONUtils.getInt(p_199798_0_, "amount", 1000);
				return new FluidStack(fluid, i);
			}
		}

		public MagmaCrucibleRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = (JsonElement) (JSONUtils.isJsonArray(json, "ingredient")
					? JSONUtils.getJsonArray(json, "ingredient")
					: JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
			FluidStack result = deserializeFluid(JSONUtils.getJsonObject(json, "result"));
			int i = JSONUtils.getInt(json, "cooktime", 100);
			return new MagmaCrucibleRecipe(recipeId, s, ingredient, result, i);
		}
		
		public MagmaCrucibleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient = Ingredient.read(buffer);
			FluidStack result = FluidStack.readFromPacket(buffer);
			int i = buffer.readVarInt();
			return new MagmaCrucibleRecipe(recipeId, s, ingredient, result, i);
		}

		public void write(PacketBuffer buffer, MagmaCrucibleRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient.write(buffer);
			recipe.result.writeToPacket(buffer);
			buffer.writeVarInt(recipe.cookTime);
		}

	}
	
	@Override
	public boolean canFit(int w, int h) {
		return true;
	}


}
