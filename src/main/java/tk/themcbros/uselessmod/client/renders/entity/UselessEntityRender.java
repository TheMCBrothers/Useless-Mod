package tk.themcbros.uselessmod.client.renders.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.client.models.entity.UselessEntityModel;
import tk.themcbros.uselessmod.entity.UselessEntity;

@OnlyIn(Dist.CLIENT)
public class UselessEntityRender extends LivingRenderer<UselessEntity, UselessEntityModel>{

	public UselessEntityRender(EntityRendererManager manager) {
		super(manager, new UselessEntityModel(), 0f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(UselessEntity arg0) {
		return new ResourceLocation(UselessMod.MOD_ID, "textures/entity/useless_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<UselessEntity> {

		@Override
		public EntityRenderer<? super UselessEntity> createRenderFor(EntityRendererManager manager) {
			return new UselessEntityRender(manager);
		}
		
	}

}
