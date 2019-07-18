package tk.themcbros.uselessmod.client.renders.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.themcbros.uselessmod.entity.GrenadeEntity;
import tk.themcbros.uselessmod.entity.UselessEntity;

@OnlyIn(Dist.CLIENT)
public class UselessRenderRegistry {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(UselessEntity.class, new UselessEntityRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(GrenadeEntity.class, manager -> {
			return new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer());
		});
	}
	
}
