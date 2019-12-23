package tk.themcbros.uselessmod.compat.jei.categories;

public class CrusherRecipeCategory /*implements IRecipeCategory<CrusherRecipe> {

	private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID + ":textures/gui/container/crusher.png");
	
	private static final int input = 0;
	private static final int output = 2;
	
	private final IDrawableStatic staticFlame;
	private final IDrawableAnimated animatedFlame;
	private final IDrawableAnimated animatedArrow;
	
	private final IDrawable background, icon;
	
	public CrusherRecipeCategory(IGuiHelper helper) {
		staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);
		
		background = helper.createDrawable(TEXTURES, 55, 16, 82, 54);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.CRUSHER));
	}
	
	@Override
	public void draw(CrusherRecipe recipe, double mouseX, double mouseY) {
		animatedFlame.draw(1, 20);
		animatedArrow.draw(24, 18);
		
		float experience = recipe.getExperience();
		if (experience > 0) {
			String experienceString = new TranslationTextComponent("gui.jei.category.smelting.experience", experience).getFormattedText();
			Minecraft minecraft = Minecraft.getInstance();
			FontRenderer fontRenderer = minecraft.fontRenderer;
			int stringWidth = fontRenderer.getStringWidth(experienceString);
			fontRenderer.drawString(experienceString, background.getWidth() - stringWidth, 0, 0xFF808080);
		}
	}
	
	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.CRUSHER;
	}

	@Override
	public Class<? extends CrusherRecipe> getRecipeClass() {
		return CrusherRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("container.uselessmod.crusher").getFormattedText();
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setIngredients(CrusherRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, 0, 0);
		stacks.init(output, false, 60, 18);
		stacks.set(ingredients);
	}
	
}*/{}
