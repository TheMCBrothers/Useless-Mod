package tk.themcbros.uselessmod.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModFluids;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.lists.ModTags;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class UselessWaterFluid extends FlowingFluid {

    public UselessWaterFluid() {
    }

    public Fluid getFlowingFluid() {
        return ModFluids.FLOWING_USELESS_WATER;
    }

    public Fluid getStillFluid() {
        return ModFluids.USELESS_WATER;
    }

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public Item getFilledBucket() {
        return ModItems.USELESS_WATER_BUCKET;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(World world, BlockPos pos, IFluidState fluidState, Random random) {
        if (!fluidState.isSource() && !fluidState.get(FALLING)) {
            if (random.nextInt(64) == 0) {
                world.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
            }
        } else if (random.nextInt(10) == 0) {
            world.addParticle(ParticleTypes.UNDERWATER, (float)pos.getX() + random.nextFloat(), (float)pos.getY() + random.nextFloat(), (float)pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticleData() {
        return ParticleTypes.DRIPPING_WATER;
    }

    protected boolean canSourcesMultiply() {
        return true;
    }

    protected void beforeReplacingBlock(IWorld world, BlockPos pos, BlockState state) {
        TileEntity lvt_4_1_ = state.getBlock().hasTileEntity() ? world.getTileEntity(pos) : null;
        Block.spawnDrops(state, world.getWorld(), pos, lvt_4_1_);
    }

    public int getSlopeFindDistance(IWorldReader world) {
        return 4;
    }

    public BlockState getBlockState(IFluidState state) {
        return ModBlocks.USELESS_WATER.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    public boolean isEquivalentTo(Fluid fluid) {
        return fluid == ModFluids.USELESS_WATER || fluid == ModFluids.FLOWING_USELESS_WATER;
    }

    public int getLevelDecreasePerBlock(IWorldReader world) {
        return 1;
    }

    public int getTickRate(IWorldReader world) {
        return 5;
    }

    public boolean canDisplace(IFluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(ModTags.Fluids.USELESS_WATER);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder( new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).overlay(new ResourceLocation("block/water_overlay"))
                .translationKey("block.uselessmod.useless_water")
                .color(0xFF468b44).density(1000).build(this);
    }

    public static class Flowing extends UselessWaterFluid {
        public Flowing() {
        }

        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        public int getLevel(IFluidState state) {
            return state.get(LEVEL_1_8);
        }

        public boolean isSource(IFluidState state) {
            return false;
        }
    }

    public static class Source extends UselessWaterFluid {
        public Source() {
        }

        public int getLevel(IFluidState state) {
            return 8;
        }

        public boolean isSource(IFluidState state) {
            return true;
        }
    }

}
