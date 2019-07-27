package tk.themcbros.uselessmod.jei;

import javax.annotation.Nullable;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.client.gui.CompressorScreen;
import tk.themcbros.uselessmod.client.gui.CrusherScreen;
import tk.themcbros.uselessmod.client.gui.ElectricCrusherScreen;
import tk.themcbros.uselessmod.client.gui.ElectricFurnaceScreen;
import tk.themcbros.uselessmod.container.CompressorContainer;
import tk.themcbros.uselessmod.container.CrusherContainer;
import tk.themcbros.uselessmod.container.ElectricCrusherContainer;
import tk.themcbros.uselessmod.jei.categories.CompressorRecipeCategory;
import tk.themcbros.uselessmod.jei.categories.CrusherRecipeCategory;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;

@JeiPlugin
public class JEICompat implements IModPlugin {
	
	public static final ResourceLocation ID = new ResourceLocation(UselessMod.MOD_ID, "jeicompat");
	
	@Nullable
	private IRecipeCategory<CrusherRecipe> crusherCategory;
	@Nullable
	private IRecipeCategory<CompressorRecipe> compressorCategory;

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		registration.registerSubtypeInterpreter(ModBlocks.CLOSET.asItem(), itemStack -> {
			CompoundNBT tag = itemStack.getChildTag("uselessmod");
			String casingId = tag.getString("casingId");
			String beddingId = tag.getString("beddingId");
			return casingId + beddingId;
		});
		registration.registerSubtypeInterpreter(ModItems.PAINT_BRUSH, itemStack -> {
			return Integer.toString(itemStack.getTag().getInt("color"));
		});
		registration.registerSubtypeInterpreter(ModBlocks.CANVAS.asItem(), itemStack -> {
			return Integer.toString(itemStack.getTag().getInt("color"));
		});
		registration.registerSubtypeInterpreter(ModBlocks.PAINT_BUCKET.asItem(), itemStack -> {
			return Integer.toString(itemStack.getTag().getInt("color"));
		});
		registration.registerSubtypeInterpreter(ModItems.COFFEE_CUP, itemStack -> {
			return itemStack.getTag().getString("CoffeeType");
		});
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER), RecipeCategoryUid.CRUSHER, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_CRUSHER), RecipeCategoryUid.CRUSHER);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE), VanillaRecipeCategoryUid.FURNACE);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR), RecipeCategoryUid.COMPRESSOR);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		crusherCategory = new CrusherRecipeCategory(helper);
		compressorCategory = new CompressorRecipeCategory(helper);
		registration.addRecipeCategories(
				crusherCategory,
				compressorCategory
		);
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		UselessRecipeValidator.Results results = UselessRecipeValidator.getValidRecipes(crusherCategory, compressorCategory);
		registration.addRecipes(results.getCrusherRecipes(), RecipeCategoryUid.CRUSHER);
		registration.addRecipes(results.getCompressorRecipes(), RecipeCategoryUid.COMPRESSOR);
		registration.addRecipes(ClosetRecipeMaker.createClosetRecipes(), VanillaRecipeCategoryUid.CRAFTING);
	}
	
	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(CrusherContainer.class, RecipeCategoryUid.CRUSHER, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(CrusherContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(ElectricCrusherContainer.class, RecipeCategoryUid.CRUSHER, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(CompressorContainer.class, RecipeCategoryUid.COMPRESSOR, 0, 1, 2, 36);
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(CrusherScreen.class, 79, 35, 24, 17, RecipeCategoryUid.CRUSHER);
		registration.addRecipeClickArea(ElectricCrusherScreen.class, 62, 34, 24, 17, RecipeCategoryUid.CRUSHER);
		registration.addRecipeClickArea(ElectricFurnaceScreen.class, 79, 34, 24, 17, VanillaRecipeCategoryUid.FURNACE);
		registration.addRecipeClickArea(CompressorScreen.class, 79, 34, 24, 17, RecipeCategoryUid.COMPRESSOR);
//		registration.addRecipeClickArea(GlowstoneGeneratorScreen.class, 108, 32, 24, 17, RecipeCategoryUid.GLOWSTONE_GENERATER);
	}

}
