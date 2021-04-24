package themcbros.uselessmod.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import themcbros.uselessmod.UselessMod;

public class UselessHouseStructure extends Structure<NoFeatureConfig> {

    public static final ResourceLocation ID = UselessMod.rl("useless_house");

    public static final Structure<NoFeatureConfig> INSTANCE = new UselessHouseStructure(NoFeatureConfig.CODEC);

    public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CONFIGURED_INSTANCE =
            INSTANCE.withConfiguration(NoFeatureConfig.INSTANCE);

    public UselessHouseStructure(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return UselessHouseStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public String getStructureName() {
        return UselessMod.MOD_ID + ":useless_house";
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> configStructure, int p_i225806_2_, int p_i225806_3_,
                     MutableBoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(configStructure, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator,
                                   TemplateManager templateManager, int chunkX, int chunkY, Biome biome,
                                   NoFeatureConfig featureConfig) {
            int i = chunkX * 16;
            int j = chunkY * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.randomRotation(this.rand);
            UselessHousePieces.func_236991_a_(templateManager, blockpos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}