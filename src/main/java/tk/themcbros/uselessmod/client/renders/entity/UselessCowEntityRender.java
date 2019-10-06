package tk.themcbros.uselessmod.client.renders.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.client.models.entity.UselessCowEntityModel;
import tk.themcbros.uselessmod.entity.UselessCowEntity;

@OnlyIn(Dist.CLIENT)
public class UselessCowEntityRender extends LivingRenderer<UselessCowEntity, UselessCowEntityModel>{

	public UselessCowEntityRender(EntityRendererManager manager) {
		super(manager, new UselessCowEntityModel(), 0f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(UselessCowEntity arg0) {
		return new ResourceLocation(UselessMod.MOD_ID, "textures/entity/useless_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<UselessCowEntity> {

		@Override
		public EntityRenderer<? super UselessCowEntity> createRenderFor(EntityRendererManager manager) {
			return new UselessCowEntityRender(manager);
		}
		
	}

}
