package tk.themcbros.uselessmod.client.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.CoffeeMachineContainer;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeMachineScreen extends ContainerScreen<CoffeeMachineContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/coffee_machine.png");
	private static final ResourceLocation FLUID_TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/fluids.png");
	
	private Rectangle waterTankRectangle = new Rectangle(7, 6, 16, 45);
	private Rectangle milkTankRectangle = new Rectangle(27, 6, 16, 45);
	private Rectangle energyBar = new Rectangle(152, 6, 16, 45);
	
	public CoffeeMachineScreen(CoffeeMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private boolean isInBounds(int mouseX, int mouseY, Rectangle rect) {
		return mouseX >= rect.x+guiLeft && mouseX <= rect.x+rect.width+guiLeft && mouseY >= rect.y+guiTop && mouseY <= rect.y+rect.height+guiTop;
	}
	
	@Override
	protected void init() {
		super.init();
//		this.buttons.add(new Button(guiLeft + 111, guiTop + 25, 18, 18, "C", new IPressable() {
//			@Override
//			public void onPress(Button btn) {
//				// Send packet
//			}
//		}));
	}
	
	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);
		if (this.isInBounds(mouseX, mouseY, waterTankRectangle)) {
			int waterAmount = this.container.getWaterAmount();
			int maxWaterAmount = this.container.getMaxWaterAmount();
			List<String> tooltip = new ArrayList<String>();
			tooltip.add("Water Tank");
			tooltip.add(waterAmount + " / " + maxWaterAmount + " mB");
			this.renderTooltip(tooltip, mouseX, mouseY,
					(font == null ? this.font : font));
		}
		if (this.isInBounds(mouseX, mouseY, milkTankRectangle)) {
			int coffeeBeansAmount = this.container.getMilkAmount();
			int maxCoffeeBeansAmount = this.container.getMaxMilkAmount();
			List<String> tooltip = new ArrayList<String>();
			tooltip.add(new TranslationTextComponent(ModItems.COFFEE_BEANS.getTranslationKey()).getFormattedText());
			tooltip.add(coffeeBeansAmount + " / " + maxCoffeeBeansAmount);
			this.renderTooltip(tooltip, mouseX, mouseY,
					(font == null ? this.font : font));
		}
		if (this.isInBounds(mouseX, mouseY, energyBar)) {
			int energy = this.container.getEnergyStored();
			int maxEnergy = this.container.getMaxEnergyStored();
			List<String> tooltip = new ArrayList<String>();
			tooltip.add(energy + " / " + maxEnergy + " RF");
			this.renderTooltip(tooltip, mouseX, mouseY,
					(font == null ? this.font : font));
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		
		int k = this.container.getEnergyStoredScaled();
		this.blit(i + energyBar.x, j + energyBar.y + energyBar.height - k, 177, 19, energyBar.width, k);
		
		int l = this.container.getCookTimeScaled();
		this.blit(i + 67, j + 43, 176, 0, l + 1, 18);
		
		//Draw fluid
        this.minecraft.getTextureManager().bindTexture(FLUID_TEXTURES);
        int waterFluidHeight = this.container.getWaterGuiHeight(waterTankRectangle.height);
        this.blit(guiLeft + waterTankRectangle.x, guiTop + waterTankRectangle.y + (waterTankRectangle.height - waterFluidHeight), 0, 0, waterTankRectangle.width, waterFluidHeight);
        int milkFluidHeight = this.container.getCoffeeBeansGuiHeight(milkTankRectangle.height);
        this.blit(guiLeft + milkTankRectangle.x, guiTop + milkTankRectangle.y + (milkTankRectangle.height - milkFluidHeight), 32, 0, milkTankRectangle.width, milkFluidHeight);

        //Draw lines over fluid
        this.minecraft.getTextureManager().bindTexture(TEXTURES);
        this.blit(waterTankRectangle.x + guiLeft, waterTankRectangle.y + guiTop, 195, 19, waterTankRectangle.width, waterTankRectangle.height);
        this.blit(milkTankRectangle.x + guiLeft, milkTankRectangle.y + guiTop, 195, 19, milkTankRectangle.width, milkTankRectangle.height);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F,
				(float) (this.ySize - 96 + 3), 4210752);
	}

}
