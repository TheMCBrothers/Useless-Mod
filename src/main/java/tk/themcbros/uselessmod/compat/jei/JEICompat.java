package tk.themcbros.uselessmod.compat.jei;

//@JeiPlugin
public class JEICompat /*implements IModPlugin {
	
	public static final ResourceLocation ID = new ResourceLocation(UselessMod.MOD_ID, "jeicompat");

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
		registration.registerSubtypeInterpreter(ModItems.COFFEE_CUP, itemStack -> {
			return itemStack.hasTag() && itemStack.getTag().contains("CoffeeType") ? itemStack.getTag().getString("CoffeeType") : "coffee_cup";
		});
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER), RecipeCategoryUid.CRUSHER, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_CRUSHER), RecipeCategoryUid.ELECTRIC_CRUSHER);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE), VanillaRecipeCategoryUid.FURNACE);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR), RecipeCategoryUid.COMPRESSOR);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.COFFEE_MACHINE), RecipeCategoryUid.COFFEE);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.GLOWSTONE_GENERATOR), RecipeCategoryUid.GENERATOR);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(
				new CrusherRecipeCategory(helper),
				new ElectricCrusherRecipeCategory(helper),
				new CompressorRecipeCategory(helper),
				new CoffeeRecipeCategory(helper),
				new GlowstoneGeneratorRecipeCategory(helper)
		);
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(UselessRecipeValidator.getCrusherRecipes(), RecipeCategoryUid.CRUSHER);
		registration.addRecipes(UselessRecipeValidator.getCrusherRecipes(), RecipeCategoryUid.ELECTRIC_CRUSHER);
		registration.addRecipes(UselessRecipeValidator.getCompressorRecipes(), RecipeCategoryUid.COMPRESSOR);
		registration.addRecipes(UselessRecipeValidator.getCoffeeRecipes(), RecipeCategoryUid.COFFEE);
		registration.addRecipes(UselessRecipeValidator.getGlowstoneGeneratorRecipes(), RecipeCategoryUid.GENERATOR);
		registration.addRecipes(ClosetRecipeMaker.createClosetRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		
		List<ItemStack> stacks = Lists.newArrayList();
		for(IClosetMaterial casingMaterial : ClosetRegistry.CASINGS.getKeys()) {
			for(IClosetMaterial beddingMaterial : ClosetRegistry.BEDDINGS.getKeys()) {
				ItemStack stack = ClosetRegistry.createItemStack(casingMaterial, beddingMaterial);
				stacks.add(stack);
			}
		}
		registration.addIngredientInfo(stacks, VanillaTypes.ITEM, "uselessmod.jei.closet_desc");
		registration.addIngredientInfo(new ItemStack(ModItems.PAINT_BRUSH), VanillaTypes.ITEM, "uselessmod.jei.paint_brush_desc");
		registration.addIngredientInfo(new ItemStack(ModItems.PAINT_BUCKET), VanillaTypes.ITEM, "uselessmod.jei.paint_bucket_desc");
	}
	
	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
		EnergyIngredientHelper ingredientHelper = new EnergyIngredientHelper();
		EnergyIngredientRenderer ingredientRenderer = new EnergyIngredientRenderer(null);
		registration.register(EnergyIngredient.TYPE, Collections.emptyList(), ingredientHelper, ingredientRenderer);
	}
	
	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(CrusherContainer.class, RecipeCategoryUid.CRUSHER, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(CrusherContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(ElectricCrusherContainer.class, RecipeCategoryUid.ELECTRIC_CRUSHER, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(CompressorContainer.class, RecipeCategoryUid.COMPRESSOR, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(CoffeeMachineContainer.class, RecipeCategoryUid.COFFEE, 2, 2, 6, 36);
		registration.addRecipeTransferHandler(GlowstoneGeneratorContainer.class, RecipeCategoryUid.GENERATOR, 0, 1, 1, 36);
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(CrusherScreen.class, 79, 35, 24, 17, RecipeCategoryUid.CRUSHER);
		registration.addRecipeClickArea(ElectricCrusherScreen.class, 70, 34, 24, 17, RecipeCategoryUid.ELECTRIC_CRUSHER);
		registration.addRecipeClickArea(ElectricFurnaceScreen.class, 79, 34, 24, 17, VanillaRecipeCategoryUid.FURNACE);
		registration.addRecipeClickArea(CompressorScreen.class, 79, 34, 24, 17, RecipeCategoryUid.COMPRESSOR);
		registration.addRecipeClickArea(CoffeeMachineScreen.class, 67, 43, 41, 18, RecipeCategoryUid.COFFEE);
		registration.addRecipeClickArea(GlowstoneGeneratorScreen.class, 108, 32, 24, 17, RecipeCategoryUid.GENERATOR);
	}

}
*/{}