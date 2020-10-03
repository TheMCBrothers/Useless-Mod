package themcbros.uselessmod.world.structure;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import themcbros.uselessmod.UselessMod;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class UselessHousePieces {
    private static final ResourceLocation LOCATION_TOP = UselessMod.rl("useless_house/top");
    private static final ResourceLocation LOCATION_MIDDLE = UselessMod.rl("useless_house/middle");
    private static final ResourceLocation LOCATION_BOTTOM = UselessMod.rl("useless_house/bottom");
    private static final Map<ResourceLocation, BlockPos> field_207621_d = ImmutableMap.of(LOCATION_TOP, new BlockPos(3, 5, 5), LOCATION_MIDDLE, new BlockPos(1, 3, 1), LOCATION_BOTTOM, new BlockPos(3, 6, 7));
    private static final Map<ResourceLocation, BlockPos> field_207622_e = ImmutableMap.of(LOCATION_TOP, BlockPos.ZERO, LOCATION_MIDDLE, new BlockPos(2, -3, 4), LOCATION_BOTTOM, new BlockPos(0, -3, -2));

    public static void func_236991_a_(TemplateManager templateManager, BlockPos blockPos, Rotation rotation, List<StructurePiece> structurePieces, Random random) {
        if (random.nextDouble() < 0.5D) {
            int i = random.nextInt(8) + 4;
            structurePieces.add(new UselessHousePieces.Piece(templateManager, LOCATION_BOTTOM, blockPos, rotation, i * 3));

            for(int j = 0; j < i - 1; ++j) {
                structurePieces.add(new UselessHousePieces.Piece(templateManager, LOCATION_MIDDLE, blockPos, rotation, j * 3));
            }
        }

        structurePieces.add(new UselessHousePieces.Piece(templateManager, LOCATION_TOP, blockPos, rotation, 0));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation field_207615_d;
        private final Rotation field_207616_e;

        public Piece(TemplateManager p_i49313_1_, ResourceLocation p_i49313_2_, BlockPos p_i49313_3_, Rotation p_i49313_4_, int p_i49313_5_) {
            super(Structures.uselessHousePiece, 0);
            this.field_207615_d = p_i49313_2_;
            BlockPos blockpos = UselessHousePieces.field_207622_e.get(p_i49313_2_);
            this.templatePosition = p_i49313_3_.add(blockpos.getX(), blockpos.getY() - p_i49313_5_, blockpos.getZ());
            this.field_207616_e = p_i49313_4_;
            this.func_207614_a(p_i49313_1_);
        }

        public Piece(TemplateManager p_i50566_1_, CompoundNBT p_i50566_2_) {
            super(Structures.uselessHousePiece, p_i50566_2_);
            this.field_207615_d = new ResourceLocation(p_i50566_2_.getString("Template"));
            this.field_207616_e = Rotation.valueOf(p_i50566_2_.getString("Rot"));
            this.func_207614_a(p_i50566_1_);
        }

        private void func_207614_a(TemplateManager p_207614_1_) {
            Template template = p_207614_1_.getTemplateDefaulted(this.field_207615_d);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.field_207616_e).setMirror(Mirror.NONE).setCenterOffset(UselessHousePieces.field_207621_d.get(this.field_207615_d)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.field_207615_d.toString());
            tagCompound.putString("Rot", this.field_207616_e.name());
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = worldIn.getTileEntity(pos.down());
                if (tileentity instanceof ChestTileEntity) {
                    ((ChestTileEntity)tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
                }

            }
        }

        @Override
        public boolean func_230383_a_(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.field_207616_e).setMirror(Mirror.NONE).setCenterOffset(UselessHousePieces.field_207621_d.get(this.field_207615_d)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = UselessHousePieces.field_207622_e.get(this.field_207615_d);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
            int i = p_230383_1_.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
            boolean flag = super.func_230383_a_(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
            if (this.field_207615_d.equals(UselessHousePieces.LOCATION_TOP)) {
                BlockPos blockpos3 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3, 0, 5)));
                BlockState blockstate = p_230383_1_.getBlockState(blockpos3.down());
                if (!blockstate.isAir() && !blockstate.isIn(Blocks.LADDER)) {
                    p_230383_1_.setBlockState(blockpos3, Blocks.SNOW_BLOCK.getDefaultState(), 3);
                }
            }

            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
