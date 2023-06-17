package net.themcbrothers.uselessmod.data.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class UselessLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(UselessBlockLoot::new, LootContextParamSets.BLOCK), new LootTableProvider.SubProviderEntry(UselessEntityLoot::new, LootContextParamSets.ENTITY)));
    }
}
