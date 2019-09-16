package tk.themcbros.uselessmod.client.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CoffeeMachineContainer;

public class CoffeeMachineScreen extends ContainerScreen<CoffeeMachineContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/coffee_machine.png");
	
	private Rectangle waterTankRectangle = new Rectangle(7, 6, 16, 45);
	private Rectangle energyBar = new Rectangle(152, 6, 16, 45);
	
	private static final int TEX_WIDTH = 16;
	private static final int TEX_HEIGHT = 16;
	private static final int MIN_FLUID_HEIGHT = 1; // ensure tiny amounts of fluid are still visible

	private final int capacityMb;
	private final int width;
	private final int height;
	
	public CoffeeMachineScreen(CoffeeMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.capacityMb = MachineConfig.coffee_machine_water_capacity.get();
		this.width = waterTankRectangle.width;
		this.height = waterTankRectangle.height;
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
		
		// Draw Background
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		
		// Draw Energy Bar
		int k = this.container.getEnergyStoredScaled();
		this.blit(i + energyBar.x, j + energyBar.y + energyBar.height - k, 177, 19, energyBar.width, k);
		
		// Draw Arrow
		int l = this.container.getCookTimeScaled();
		this.blit(i + 67, j + 43, 176, 0, l + 1, 18);
		
		GlStateManager.enableBlend();
		GlStateManager.enableAlphaTest();

		// Draw fluid
		drawFluid(waterTankRectangle.x + i, waterTankRectangle.y + j, new FluidStack(Fluids.WATER, this.container.getWaterAmount()));

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Draw lines over fluid
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(waterTankRectangle.x + i, waterTankRectangle.y + j, 195, 19, waterTankRectangle.width, waterTankRectangle.height);
		
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
	
	// DRAW STUFF
	
	private void drawFluid(final int xPosition, final int yPosition, @Nullable FluidStack fluidStack) {
		if (fluidStack == null) {
			return;
		}
		Fluid fluid = fluidStack.getFluid();
		if (fluid == null) {
			return;
		}

		TextureAtlasSprite fluidStillSprite = getStillFluidSprite(fluidStack);

		FluidAttributes attributes = fluid.getAttributes();
		int fluidColor = attributes.getColor(fluidStack);

		int amount = fluidStack.getAmount();
		int scaledAmount = (amount * height) / capacityMb;
		if (amount > 0 && scaledAmount < MIN_FLUID_HEIGHT) {
			scaledAmount = MIN_FLUID_HEIGHT;
		}
		if (scaledAmount > height) {
			scaledAmount = height;
		}

		drawTiledSprite(xPosition, yPosition, width, height, fluidColor, scaledAmount, fluidStillSprite);
	}

	private void drawTiledSprite(final int xPosition, final int yPosition, final int tiledWidth, final int tiledHeight, int color, int scaledAmount, TextureAtlasSprite sprite) {
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
		setGLColorFromInt(color);

		final int xTileCount = tiledWidth / TEX_WIDTH;
		final int xRemainder = tiledWidth - (xTileCount * TEX_WIDTH);
		final int yTileCount = scaledAmount / TEX_HEIGHT;
		final int yRemainder = scaledAmount - (yTileCount * TEX_HEIGHT);

		final int yStart = yPosition + tiledHeight;

		for (int xTile = 0; xTile <= xTileCount; xTile++) {
			for (int yTile = 0; yTile <= yTileCount; yTile++) {
				int width = (xTile == xTileCount) ? xRemainder : TEX_WIDTH;
				int height = (yTile == yTileCount) ? yRemainder : TEX_HEIGHT;
				int x = xPosition + (xTile * TEX_WIDTH);
				int y = yStart - ((yTile + 1) * TEX_HEIGHT);
				if (width > 0 && height > 0) {
					int maskTop = TEX_HEIGHT - height;
					int maskRight = TEX_WIDTH - width;

					drawTextureWithMasking(x, y, sprite, maskTop, maskRight, 100);
				}
			}
		}
	}

	private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
		Minecraft minecraft = Minecraft.getInstance();
		AtlasTexture textureMapBlocks = minecraft.getTextureMap();
		Fluid fluid = fluidStack.getFluid();
		FluidAttributes attributes = fluid.getAttributes();
		ResourceLocation fluidStill = attributes.getStill(fluidStack);
		return textureMapBlocks.getSprite(fluidStill);
	}

	private static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		float alpha = ((color >> 24) & 0xFF) / 255F;

		GlStateManager.color4f(red, green, blue, alpha);
	}

	private static void drawTextureWithMasking(double xCoord, double yCoord, TextureAtlasSprite textureSprite, int maskTop, int maskRight, double zLevel) {
		double uMin = textureSprite.getMinU();
		double uMax = textureSprite.getMaxU();
		double vMin = textureSprite.getMinV();
		double vMax = textureSprite.getMaxV();
		uMax = uMax - (maskRight / 16.0 * (uMax - uMin));
		vMax = vMax - (maskTop / 16.0 * (vMax - vMin));

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferBuilder.pos(xCoord, yCoord + 16, zLevel).tex(uMin, vMax).endVertex();
		bufferBuilder.pos(xCoord + 16 - maskRight, yCoord + 16, zLevel).tex(uMax, vMax).endVertex();
		bufferBuilder.pos(xCoord + 16 - maskRight, yCoord + maskTop, zLevel).tex(uMax, vMin).endVertex();
		bufferBuilder.pos(xCoord, yCoord + maskTop, zLevel).tex(uMin, vMin).endVertex();
		tessellator.draw();
	}

}
