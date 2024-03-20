package net.themcbrothers.uselessmod.data.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.themcbrothers.uselessmod.world.worldgen.UselessOrePlacements;

public final class UselessBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderSet<Biome> overworldBiomes = biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD);
        HolderSet<Biome> netherBiomes = biomeGetter.getOrThrow(BiomeTags.IS_NETHER);
        HolderSet<Biome> endBiomes = biomeGetter.getOrThrow(BiomeTags.IS_END);

        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_USELESS_MIDDLE, overworldBiomes);
        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_USELESS_NETHER, netherBiomes);
        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_USELESS_END, endBiomes);

        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_SUPER_USELESS_LARGE, overworldBiomes);
        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_SUPER_USELESS_NETHER, netherBiomes);
        registerOreModifier(context, placedFeatures, UselessOrePlacements.ORE_SUPER_USELESS_END, endBiomes);
    }

    private static void registerOreModifier(BootstrapContext<BiomeModifier> context, HolderGetter<PlacedFeature> placedFeatures, ResourceKey<PlacedFeature> key, HolderSet<Biome> biomes) {
        BiomeModifiers.AddFeaturesBiomeModifier modifier = new BiomeModifiers.AddFeaturesBiomeModifier(biomes, HolderSet.direct(placedFeatures.getOrThrow(key)), GenerationStep.Decoration.UNDERGROUND_ORES);
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(key.location() + "_generation")), modifier);
    }
}
