package tk.themcbros.uselessmod.world.structures;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import tk.themcbros.uselessmod.lists.ModEntityTypes;

public class UselessHutStructure extends ScatteredStructure<NoFeatureConfig> {

	private static final List<SpawnListEntry> field_202384_d;
    private static final List<SpawnListEntry> field_214559_aS;

    public UselessHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51424_1_) {
        super(p_i51424_1_);
    }

    public String getStructureName() {
        return "Swamp_Hut";
    }

    public int getSize() {
        return 3;
    }

    public IStartFactory getStartFactory() {
        return UselessHutStructure.Start::new;
    }

    protected int getSeedModifier() {
        return 14357620;
    }

    public List<SpawnListEntry> getSpawnList() {
        return field_202384_d;
    }

    public List<SpawnListEntry> getCreatureSpawnList() {
        return field_214559_aS;
    }

    public boolean func_202383_b(IWorld p_202383_1_, BlockPos p_202383_2_) {
        StructureStart lvt_3_1_ = this.getStart(p_202383_1_, p_202383_2_, true);
        if (lvt_3_1_ != StructureStart.DUMMY && lvt_3_1_ instanceof UselessHutStructure.Start && !lvt_3_1_.getComponents().isEmpty()) {
            StructurePiece lvt_4_1_ = (StructurePiece)lvt_3_1_.getComponents().get(0);
            return lvt_4_1_ instanceof UselessHutPiece;
        } else {
            return false;
        }
    }

    static {
        field_202384_d = Lists.newArrayList(new SpawnListEntry[]{new SpawnListEntry(ModEntityTypes.USELESS_ENTITY, 1, 1, 1)});
        field_214559_aS = Lists.newArrayList(new SpawnListEntry[]{new SpawnListEntry(EntityType.CAT, 1, 1, 1)});
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i50515_1_, int p_i50515_2_, int p_i50515_3_, Biome p_i50515_4_, MutableBoundingBox p_i50515_5_, int p_i50515_6_, long p_i50515_7_) {
            super(p_i50515_1_, p_i50515_2_, p_i50515_3_, p_i50515_4_, p_i50515_5_, p_i50515_6_, p_i50515_7_);
        }

        public void init(ChunkGenerator<?> p_214625_1_, TemplateManager p_214625_2_, int p_214625_3_, int p_214625_4_, Biome p_214625_5_) {
            UselessHutPiece lvt_6_1_ = new UselessHutPiece(this.rand, p_214625_3_ * 16, p_214625_4_ * 16);
            this.components.add(lvt_6_1_);
            this.recalculateStructureSize();
        }
    }

}
