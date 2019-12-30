package tk.themcbros.uselessmod.client.renders.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.themcbros.uselessmod.lists.ModEntityTypes;

@OnlyIn(Dist.CLIENT)
public class UselessRenderRegistry {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.USELESS_COW, UselessCowEntityRender::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GRENADE, manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
	}
	
}
