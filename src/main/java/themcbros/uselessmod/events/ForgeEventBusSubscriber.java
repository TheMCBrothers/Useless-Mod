package themcbros.uselessmod.events;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = UselessMod.MOD_ID)
public class ForgeEventBusSubscriber {

    private static final ResourceLocation GRASS_DROP = new ResourceLocation("minecraft", "blocks/grass");

    @SubscribeEvent
    public static void lootTableLoad(final LootTableLoadEvent event) {
        if (event.getName().equals(GRASS_DROP)) {
            event.getTable().addPool(LootPool.builder()
                    .addEntry(TableLootEntry.builder(UselessMod.rl("blocks/useless_wheat_grass_drops")))
                    .name("useless_wheat_grass_drops").build());
            event.getTable().addPool(LootPool.builder()
                    .addEntry(TableLootEntry.builder(UselessMod.rl("blocks/ender_seeds_grass_drops")))
                    .name("ender_seeds_grass_drops").build());
            event.getTable().addPool(LootPool.builder()
                    .addEntry(TableLootEntry.builder(UselessMod.rl("blocks/coffee_grass_drops")))
                    .name("coffee_grass_drops").build());
        }
    }

}
