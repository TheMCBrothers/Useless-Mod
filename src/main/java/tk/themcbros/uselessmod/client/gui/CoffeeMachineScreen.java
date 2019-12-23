package tk.themcbros.uselessmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CoffeeMachineContainer;
import tk.themcbros.uselessmod.helper.TextUtils;

import java.awt.*;

public class CoffeeMachineScreen extends MachineFluidScreen<CoffeeMachineContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/coffee_machine.png");
	
	private Rectangle waterTankRectangle = new Rectangle(7, 6, 16, 45);
	private Rectangle energyBar = new Rectangle(152, 6, 16, 45);
	
	public CoffeeMachineScreen(CoffeeMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, MachineConfig.coffee_machine_water_capacity.get());
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void init() {
		super.init();
	}
	
	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);
		if (isPointInRegion(waterTankRectangle.x, waterTankRectangle.y, waterTankRectangle.width, waterTankRectangle.height, mouseX, mouseY)) {
			int waterAmount = this.container.getWaterAmount();
			int maxWaterAmount = this.container.getMaxWaterAmount();
			ITextComponent text = TextUtils.fluidWithMax(waterAmount, maxWaterAmount);
			this.renderTooltip(text.getFormattedText(), mouseX, mouseY);
		}
		if (isPointInRegion(energyBar.x, energyBar.y, energyBar.width, energyBar.height, mouseX, mouseY)) {
			int energy = this.container.getEnergyStored();
			int maxEnergy = this.container.getMaxEnergyStored();
			ITextComponent text = TextUtils.energyWithMax(energy, maxEnergy);
			this.renderTooltip(text.getFormattedText(), mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		
		// Draw Background
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		
		// Draw Energy Bar
		int k = this.container.getEnergyStoredScaled(45);
		this.blit(i + energyBar.x, j + energyBar.y + energyBar.height - k, 177, 19, energyBar.width, k);
		
		// Draw Arrow
		int l = this.container.getCookTimeScaled();
		this.blit(i + 67, j + 43, 176, 0, l + 1, 18);

		RenderSystem.enableBlend();
		RenderSystem.enableAlphaTest();

		// Draw fluid
		drawFluid(waterTankRectangle.x + i, waterTankRectangle.y + j, new FluidStack(this.container.getWaterTankFluid(), this.container.getWaterAmount()));

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Draw lines over fluid
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(waterTankRectangle.x + i, waterTankRectangle.y + j, 195, 19, waterTankRectangle.width, waterTankRectangle.height);

		RenderSystem.disableAlphaTest();
		RenderSystem.disableBlend();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F,
				(float) (this.ySize - 96 + 3), 4210752);
	}

}
