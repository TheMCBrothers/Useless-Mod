package net.themcbrothers.uselessmod.world.level.storage.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.themcbrothers.uselessmod.UselessMod;

public class UselessLootTables {
    public static final ResourceKey<LootTable> SHEEP_USELESS = ResourceKey.create(Registries.LOOT_TABLE, UselessMod.rl("entities/useless_sheep/useless"));
}
