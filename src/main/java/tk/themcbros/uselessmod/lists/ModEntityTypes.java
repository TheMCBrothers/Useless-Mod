package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.EntityConfig;
import tk.themcbros.uselessmod.entity.GrenadeEntity;
import tk.themcbros.uselessmod.entity.UselessCowEntity;

@ObjectHolder(UselessMod.MOD_ID)
public class ModEntityTypes {

	private static final List<EntityType<?>> ENTITIES = Lists.newArrayList();
	
	public static final EntityType<UselessCowEntity> USELESS_COW = 
			register("useless_cow", UselessCowEntity::new, EntityClassification.CREATURE, "uselessmod:useless_cow");
	public static final EntityType<GrenadeEntity> GRENADE = 
			register("grenade", EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F), "uselessmod:grenade");
	
	private static <T extends Entity> EntityType<T> register(String regName, IFactory<T> entitySupplier, EntityClassification classification, String id) {
		EntityType.Builder<T> builder = EntityType.Builder.create(entitySupplier, classification);
		return register(regName, builder, id);
	}
	
	private static <T extends Entity> EntityType<T> register(String regName, Builder<T> builder, String id) {
		EntityType<T> type = builder.build(id);
		type.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, regName));
		ENTITIES.add(type);
		return type;
	}

	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<EntityType<?>> event) {
			ENTITIES.forEach(entity -> event.getRegistry().register(entity));
			UselessMod.LOGGER.info("Registered EnitiyTypes");
		}
	}
	
	private static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minCount, int maxCount, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(type.getClassification()).add(new SpawnListEntry(type, weight, minCount, maxCount));
			}
		}
	}
	
	public static void registerEntityWorldSpawns() {
		if(EntityConfig.useless_entity_enabled.get()) {
			registerEntityWorldSpawn(USELESS_COW, 1, 4, 4, Biomes.NETHER, ModBiomes.USELESS_BIOME);
		}
	}
	
}
