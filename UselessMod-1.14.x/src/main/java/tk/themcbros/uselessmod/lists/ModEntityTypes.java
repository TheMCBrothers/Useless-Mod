package tk.themcbros.uselessmod.lists;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.entity.GrenadeEntity;
import tk.themcbros.uselessmod.entity.UselessEntity;

@ObjectHolder(UselessMod.MOD_ID)
public class ModEntityTypes {

	public static final EntityType<UselessEntity> USELESS_ENTITY = EntityType.Builder.create(UselessEntity::new, EntityClassification.CREATURE).build(EntityNames.USELESS_ENTITY.toString());
	public static final EntityType<GrenadeEntity> GRENADE = EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(EntityNames.GRENADE.toString());
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<EntityType<?>> event) {
			IForgeRegistry<EntityType<?>> registry = event.getRegistry();
			
			registry.register(USELESS_ENTITY.setRegistryName(EntityNames.USELESS_ENTITY));
			registry.register(GRENADE.setRegistryName(EntityNames.GRENADE));
			
			registerEntityWorldSpawns();
		}
	}
	
	public static Item registerSpawnEgg(String registryName, EntityType<?> type, int primaryColor, int secondaryColor) {
		SpawnEggItem item = new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP));
		item.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, registryName));
		return item;
	}
	
	private static void registerEntityWorldSpawn(EntityType<?> type, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(type.getClassification()).add(new SpawnListEntry(type, 10, 1, 10));
			}
		}
	}
	
	public static void registerEntityWorldSpawns() {
		registerEntityWorldSpawn(USELESS_ENTITY, Biomes.PLAINS);
	}
	
}
