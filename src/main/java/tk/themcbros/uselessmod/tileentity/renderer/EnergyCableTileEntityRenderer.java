package tk.themcbros.uselessmod.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

public class EnergyCableTileEntityRenderer extends TileEntityRenderer<EnergyCableTileEntity> {

	private ResourceLocation textureCable = new ResourceLocation(UselessMod.MOD_ID, "textures/block/energy_cable.png");
	
	@Override
	public void render(EnergyCableTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		this.bindTexture(textureCable);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-x, -y, -z);
	}
	
}
