package tk.themcbros.uselessmod.client.gui;

import java.awt.Rectangle;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.MagmaCrucibleContainer;
import tk.themcbros.uselessmod.helper.TextUtils;

public class MagmaCrucibleScreen extends MachineFluidScreen<MagmaCrucibleContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/magma_crucible.png");
	
	private Rectangle tankRectangle = new Rectangle(109, 15, 16, 45);
	private Rectangle energyBar = new Rectangle(152, 6, 16, 45);
	
	public MagmaCrucibleScreen(MagmaCrucibleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
		if (isPointInRegion(tankRectangle.x, tankRectangle.y, tankRectangle.width, tankRectangle.height, mouseX, mouseY)) {
			ITextComponent text = TextUtils.fluidWithMax(this.container.getFluidTankHandler());
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
		
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		
		// Draw Background
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		
		// Draw Energy Bar
		int k = this.container.getEnergyStoredScaled(45);
		this.blit(i + energyBar.x, j + energyBar.y + energyBar.height - k, 177, 18, energyBar.width, k);
		
		// Draw Arrow
		int l = this.container.getCookTimeScaled();
		this.blit(i + 73, j + 29, 176, 0, l + 1, 17);
		
		GlStateManager.enableBlend();
		GlStateManager.enableAlphaTest();

		// Draw fluid
		drawFluid(tankRectangle.x + i, tankRectangle.y + j, new FluidStack(this.container.getTankFluid(), this.container.getFluidAmount()));

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Draw lines over fluid
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(tankRectangle.x + i, tankRectangle.y + j, 195, 18, tankRectangle.width, tankRectangle.height);
		
		GlStateManager.disableAlphaTest();
		GlStateManager.disableBlend();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F,
				(float) (this.ySize - 96 + 3), 4210752);
	}

}
