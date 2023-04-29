package net.themcbrothers.uselessmod.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.holdersets.AnyHolderSet;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.worldgen.UselessOrePlacements;

import java.io.IOException;
import java.util.Map;

public class UselessBiomeModifierProvider implements DataProvider {
    private final DataProvider provider;

    public UselessBiomeModifierProvider(DataGenerator generator, ExistingFileHelper existingFileHelper, RegistryOps<JsonElement> registryOps) {
        this.provider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, UselessMod.MOD_ID,
                registryOps, ForgeRegistries.Keys.BIOME_MODIFIERS, generateBiomeModifiers(registryOps.registryAccess));
    }

    private static Map<ResourceLocation, BiomeModifier> generateBiomeModifiers(RegistryAccess registryAccess) {
        Map<ResourceLocation, BiomeModifier> biomeModifierMap = Maps.newHashMap();

        generateOreModifiers(
                registryAccess.registryOrThrow(Registry.BIOME_REGISTRY),
                registryAccess.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY),
                biomeModifierMap
        );

        return biomeModifierMap;
    }

    private static void generateOreModifiers(Registry<Biome> biomes, Registry<PlacedFeature> placedFeatures,
                                             Map<ResourceLocation, BiomeModifier> modifierMap) {
        HolderSet<Biome> allBiomes = new AnyHolderSet<>(biomes);

        addOreToBiomeGen(modifierMap, UselessOrePlacements.ORE_USELESS_MIDDLE.getId().getPath(), allBiomes, placedFeatures);
        addOreToBiomeGen(modifierMap, UselessOrePlacements.ORE_USELESS_NETHER.getId().getPath(), allBiomes, placedFeatures);
        addOreToBiomeGen(modifierMap, UselessOrePlacements.ORE_USELESS_END.getId().getPath(), allBiomes, placedFeatures);
    }

    private static void addOreToBiomeGen(Map<ResourceLocation, BiomeModifier> map, String name, HolderSet<Biome> biomes, Registry<PlacedFeature> pfRegistry) {
        ResourceKey<PlacedFeature> resourceKey = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, UselessMod.rl(name));
        ForgeBiomeModifiers.AddFeaturesBiomeModifier addFeaturesBiomeModifier =
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes,
                        HolderSet.direct(Holder.Reference.createStandAlone(pfRegistry, resourceKey)),
                        GenerationStep.Decoration.UNDERGROUND_ORES);

        map.put(UselessMod.rl(name + "_generation"), addFeaturesBiomeModifier);
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        this.provider.run(output);
    }

    @Override
    public String getName() {
        return "Biome Modifiers: " + UselessMod.MOD_ID;
    }
}
