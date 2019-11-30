package tk.themcbros.uselessmod.recipes;

import com.google.common.collect.Lists;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

import java.util.List;

@ObjectHolder(UselessMod.MOD_ID)
@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeSerializers {

	private static final List<IRecipeSerializer<?>> SERIALIZERS = Lists.newArrayList();

	public static final IRecipeSerializer<CrusherRecipe> CRUSHING = register("crushing", new CrusherRecipe.Serializer());
	public static final IRecipeSerializer<CompressorRecipe> COMPRESSING = register("compressing", new CompressorRecipe.Serializer());
	public static final IRecipeSerializer<CoffeeRecipe> COFFEE = register("coffee", new CoffeeRecipe.Serializer());
	public static final IRecipeSerializer<GlowstoneGeneratorRecipe> GENERATING = register("generating", new GlowstoneGeneratorRecipe.Serializer());
	public static final IRecipeSerializer<MagmaCrucibleRecipe> MAGMA_CRUCIBLE = register("magma_crucible", new MagmaCrucibleRecipe.Serializer());

	public static final IRecipeSerializer<ClosetRecipe> CLOSET = register("closet", new SpecialRecipeSerializer<>(ClosetRecipe::new));
	public static final IRecipeSerializer<PaintBrushRecipe> PAINT_BRUSH = register("paint_brush", new SpecialRecipeSerializer<>(PaintBrushRecipe::new));
	public static final IRecipeSerializer<LightSwitchRecipe> LIGHT_SWITCH = register("light_switch", new SpecialRecipeSerializer<>(LightSwitchRecipe::new));
	public static final IRecipeSerializer<NoRemainingShapelessRecipe> NO_REMAINING_SHAPELESS = register("no_remaining_shapeless", new NoRemainingShapelessRecipe.Serializer());

	private static <T extends IRecipeSerializer<?>> T register(String registryName, T serializer) {
		serializer.setRegistryName(UselessMod.getId(registryName));
		SERIALIZERS.add(serializer);
		return serializer;
	}

	@SubscribeEvent
	public static void onRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
		SERIALIZERS.forEach(event.getRegistry()::register);
		UselessMod.LOGGER.debug("Registered Recipe Serializer");
	}
	
}
