package tk.themcbros.uselessmod.world.structures;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import tk.themcbros.uselessmod.entity.UselessEntity;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModEntityTypes;

public class UselessHutPiece extends ScatteredStructurePiece {
    private boolean useless_entity;
    private boolean field_214822_f;

    public UselessHutPiece(Random p_i48652_1_, int p_i48652_2_, int p_i48652_3_) {
        super(IStructurePieceType.TESH, p_i48652_1_, p_i48652_2_, 64, p_i48652_3_, 7, 7, 9);
    }

    public UselessHutPiece(TemplateManager p_i51340_1_, CompoundNBT p_i51340_2_) {
        super(IStructurePieceType.TESH, p_i51340_2_);
        this.useless_entity = p_i51340_2_.getBoolean("UselessEntity");
        this.field_214822_f = p_i51340_2_.getBoolean("Cat");
    }

    protected void readAdditional(CompoundNBT p_143011_1_) {
        super.readAdditional(p_143011_1_);
        p_143011_1_.putBoolean("UselessEntity", this.useless_entity);
        p_143011_1_.putBoolean("Cat", this.field_214822_f);
    }

    public boolean addComponentParts(IWorld p_74875_1_, Random p_74875_2_, MutableBoundingBox p_74875_3_, ChunkPos p_74875_4_) {
        if (!this.func_202580_a(p_74875_1_, p_74875_3_, 0)) {
            return false;
        } else {
        	BlockState planks = Blocks.BIRCH_PLANKS.getDefaultState();
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 5, 1, 7, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 4, 2, 5, 4, 7, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 1, 0, 4, 1, 0, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 2, 2, 3, 3, 2, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 2, 3, 1, 3, 6, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 5, 2, 3, 5, 3, 6, planks, planks, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 2, 7, 4, 3, 7, planks, planks, false);
            BlockState log = Blocks.OAK_LOG.getDefaultState();
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 2, 1, 3, 2, log, log, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 5, 0, 2, 5, 3, 2, log, log, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 7, 1, 3, 7, log, log, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 5, 0, 7, 5, 3, 7, log, log, false);
            this.setBlockState(p_74875_1_, Blocks.BIRCH_FENCE.getDefaultState(), 2, 3, 2, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.BIRCH_FENCE.getDefaultState(), 3, 3, 7, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.AIR.getDefaultState(), 1, 3, 4, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.AIR.getDefaultState(), 5, 3, 4, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.AIR.getDefaultState(), 5, 3, 5, p_74875_3_);
            this.setBlockState(p_74875_1_, ModBlocks.POTTED_RED_ROSE.getDefaultState(), 1, 3, 5, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.CRAFTING_TABLE.getDefaultState(), 3, 2, 6, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.CAULDRON.getDefaultState(), 4, 2, 6, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.OAK_FENCE.getDefaultState(), 1, 2, 1, p_74875_3_);
            this.setBlockState(p_74875_1_, Blocks.OAK_FENCE.getDefaultState(), 5, 2, 1, p_74875_3_);
            BlockState stairsNorth = (BlockState)Blocks.BIRCH_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
            BlockState stairsEast = (BlockState)Blocks.BIRCH_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
            BlockState stairsWest = (BlockState)Blocks.BIRCH_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
            BlockState stairsSouth = (BlockState)Blocks.BIRCH_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 1, 6, 4, 1, stairsNorth, stairsNorth, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 2, 0, 4, 7, stairsEast, stairsEast, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 4, 2, 6, 4, 7, stairsWest, stairsWest, false);
            this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 8, 6, 4, 8, stairsSouth, stairsSouth, false);
            this.setBlockState(p_74875_1_, (BlockState)stairsNorth.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT), 0, 4, 1, p_74875_3_);
            this.setBlockState(p_74875_1_, (BlockState)stairsNorth.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT), 6, 4, 1, p_74875_3_);
            this.setBlockState(p_74875_1_, (BlockState)stairsSouth.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT), 0, 4, 8, p_74875_3_);
            this.setBlockState(p_74875_1_, (BlockState)stairsSouth.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT), 6, 4, 8, p_74875_3_);

            int lvt_9_2_;
            int lvt_10_2_;
            for(lvt_9_2_ = 2; lvt_9_2_ <= 7; lvt_9_2_ += 5) {
                for(lvt_10_2_ = 1; lvt_10_2_ <= 5; lvt_10_2_ += 4) {
                    this.replaceAirAndLiquidDownwards(p_74875_1_, log, lvt_10_2_, -1, lvt_9_2_, p_74875_3_);
                }
            }

            if (!this.useless_entity) {
                lvt_9_2_ = this.getXWithOffset(2, 5);
                lvt_10_2_ = this.getYWithOffset(2);
                int lvt_11_1_ = this.getZWithOffset(2, 5);
                if (p_74875_3_.isVecInside(new BlockPos(lvt_9_2_, lvt_10_2_, lvt_11_1_))) {
                    this.useless_entity = true;
                    UselessEntity lvt_12_1_ = (UselessEntity)ModEntityTypes.USELESS_ENTITY.create(p_74875_1_.getWorld());
                    lvt_12_1_.enablePersistence();
                    lvt_12_1_.setLocationAndAngles((double)lvt_9_2_ + 0.5D, (double)lvt_10_2_, (double)lvt_11_1_ + 0.5D, 0.0F, 0.0F);
                    lvt_12_1_.onInitialSpawn(p_74875_1_, p_74875_1_.getDifficultyForLocation(new BlockPos(lvt_9_2_, lvt_10_2_, lvt_11_1_)), SpawnReason.STRUCTURE, (ILivingEntityData)null, (CompoundNBT)null);
                    p_74875_1_.addEntity(lvt_12_1_);
                }
            }

            this.func_214821_a(p_74875_1_, p_74875_3_);
            return true;
        }
    }

    private void func_214821_a(IWorld p_214821_1_, MutableBoundingBox p_214821_2_) {
        if (!this.field_214822_f) {
            int lvt_3_1_ = this.getXWithOffset(2, 5);
            int lvt_4_1_ = this.getYWithOffset(2);
            int lvt_5_1_ = this.getZWithOffset(2, 5);
            if (p_214821_2_.isVecInside(new BlockPos(lvt_3_1_, lvt_4_1_, lvt_5_1_))) {
                this.field_214822_f = true;
                CatEntity lvt_6_1_ = (CatEntity)EntityType.CAT.create(p_214821_1_.getWorld());
                lvt_6_1_.enablePersistence();
                lvt_6_1_.setLocationAndAngles((double)lvt_3_1_ + 0.5D, (double)lvt_4_1_, (double)lvt_5_1_ + 0.5D, 0.0F, 0.0F);
                lvt_6_1_.onInitialSpawn(p_214821_1_, p_214821_1_.getDifficultyForLocation(new BlockPos(lvt_3_1_, lvt_4_1_, lvt_5_1_)), SpawnReason.STRUCTURE, (ILivingEntityData)null, (CompoundNBT)null);
                p_214821_1_.addEntity(lvt_6_1_);
            }
        }

    }
}