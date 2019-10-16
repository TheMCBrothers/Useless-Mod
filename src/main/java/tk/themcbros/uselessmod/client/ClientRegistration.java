package tk.themcbros.uselessmod.client;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.client.models.block.PowerControlBlockModel;
import tk.themcbros.uselessmod.lists.ModBlocks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID, value = Dist.CLIENT)
public class ClientRegistration {

	@SubscribeEvent
	public static void onTextureStitch(final TextureStitchEvent.Pre event) {
		if (!event.getMap().getBasePath().equals("textures")) {
			return;
		}

		// Power Control Block
		event.addSprite(new ResourceLocation(UselessMod.MOD_ID, "block/power_control_none"));
		event.addSprite(new ResourceLocation(UselessMod.MOD_ID, "block/power_control_input"));
		event.addSprite(new ResourceLocation(UselessMod.MOD_ID, "block/power_control_output"));
	}

	@SubscribeEvent
	public static void onModelBake(final ModelBakeEvent event) {
		// Power Control Block
		PowerControlBlockModel powerControlBlockModel = new PowerControlBlockModel(DefaultVertexFormats.BLOCK);
		ModBlocks.POWER_CONTROL_BLOCK.getStateContainer().getValidStates()
				.forEach(state -> event.getModelRegistry().put(BlockModelShapes.getModelLocation(state), powerControlBlockModel));

	}

}
