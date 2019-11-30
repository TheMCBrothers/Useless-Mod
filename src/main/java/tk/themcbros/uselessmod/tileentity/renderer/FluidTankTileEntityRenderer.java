package tk.themcbros.uselessmod.tileentity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import tk.themcbros.uselessmod.tileentity.FluidTankTileEntity;

public class FluidTankTileEntityRenderer extends TileEntityRenderer<FluidTankTileEntity> {

	private static final float TANK_THICKNESS = 0.05f;

	@Override
	public void render(FluidTankTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.disableBlend();
		GlStateManager.translated(x, y, z);

		bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
		renderFluid(tileEntity);

		GlStateManager.popMatrix();
	}

	private static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		float alpha = ((color >> 24) & 0xFF) / 255F;

		GlStateManager.color4f(red, green, blue, alpha);
	}

	private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
		Minecraft minecraft = Minecraft.getInstance();
		AtlasTexture textureMapBlocks = minecraft.getTextureMap();
		Fluid fluid = fluidStack.getFluid();
		FluidAttributes attributes = fluid.getAttributes();
		ResourceLocation fluidStill = attributes.getStill(fluidStack);
		return textureMapBlocks.getSprite(fluidStill);
	}

	private void renderFluid(FluidTankTileEntity tank) {
		if (tank == null) return;

		FluidStack fluid = tank.getTank().getFluid();
		if (fluid.isEmpty()) return;

		FluidAttributes renderFluid = fluid.getFluid().getAttributes();

		float scale = (1.0f - TANK_THICKNESS/2 - TANK_THICKNESS) * fluid.getAmount() / (tank.getTank().getCapacity());

		if (scale > 0.0f) {
			TextureAtlasSprite textureSprite = getStillFluidSprite(fluid);

			RenderHelper.disableStandardItemLighting();

			setGLColorFromInt(renderFluid.getColor(fluid));

			final int maskRight = 16;
			final int maskTop = 16;

			double uMin = textureSprite.getMinU();
			double uMax = textureSprite.getMaxU();
			double vMin = textureSprite.getMinV();
			double vMax = textureSprite.getMaxV();
			uMax = uMax - (maskRight / 16.0 * (uMax - uMin));
			vMax = vMax - (maskTop / 16.0 * (vMax - vMin));

			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferBuilder = tessellator.getBuffer();
			bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

			float tankThickness1 = 0 + TANK_THICKNESS;
			float tankThickness2 = 1 - TANK_THICKNESS;

			// TOP
			bufferBuilder.pos(tankThickness1, scale + tankThickness1, tankThickness1).tex(uMin, vMin).endVertex(); // TOP LEFT
			bufferBuilder.pos(tankThickness1, scale + tankThickness1, tankThickness2).tex(uMin, vMax).endVertex(); // BOTTOM LEFT
			bufferBuilder.pos(tankThickness2, scale + tankThickness1, tankThickness2).tex(uMax, vMax).endVertex(); // BOTTOM RIGHT
			bufferBuilder.pos(tankThickness2, scale + tankThickness1, tankThickness1).tex(uMax, vMin).endVertex(); // TOP RIGHT

			tessellator.draw();

			RenderHelper.enableStandardItemLighting();
		}
	}
}
