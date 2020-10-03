package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.init.ModDamageSources;

import java.util.Random;
import java.util.function.Predicate;

public class EnderSeedsBlock extends CropsBlock {

    private static final Predicate<BlockState> IS_END_STONE = state -> state.isIn(Tags.Blocks.END_STONES);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 2.0D, 11.0D),
            Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 3.0D, 11.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 12.0D, 15.0D),
            Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 12.0D, 15.0D)};

    public EnderSeedsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return hasSolidSideOnTop(worldIn, pos) || super.isValidGround(state, worldIn, pos);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        int i = this.getAge(state);
        if (i < this.getMaxAge()) {
            float f = getUselessGrowthChance(worldIn, pos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(5000.0F / f) + 100) <= 200)) {
                worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
    }

    private static float getUselessGrowthChance(IBlockReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState state = worldIn.getBlockState(blockpos);

        float f = 40.0f;

        for (int i = 0; i < 10; i++) {
            BlockPos acceleratorPos = blockpos.down(1 + i);
            BlockState acceleratorState = worldIn.getBlockState(acceleratorPos);
            if (acceleratorState.getBlock() == BlockInit.SUPER_USELESS_BLOCK.get()) {
                f += 100.0F;
            } else if (acceleratorState.getBlock() == BlockInit.USELESS_BLOCK.get()) {
                f += 20.0F;
            } else {
                break;
            }
        }

        if (IS_END_STONE.test(state)) f *= 5F;
        return f;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        boolean flag = state.get(this.getAgeProperty()) > 3;
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.BEE) {
            entityIn.setMotionMultiplier(state, new Vector3d(0.8F, 0.75D, 0.8F));
            if (!worldIn.isRemote && flag && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
                double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
                double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    entityIn.attackEntityFrom(ModDamageSources.ENDER_SEEDS, 1.0F);
                }
            }

        } else if (flag) {
            if (!(entityIn instanceof ItemEntity &&
                    (((ItemEntity) entityIn).getItem().getItem() instanceof EnderPearlItem || ((ItemEntity) entityIn).getItem().getItem() == ItemInit.ENDER_SEEDS.get())))
                entityIn.attackEntityFrom(ModDamageSources.ENDER_SEEDS, 1.0F);
        }
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemInit.ENDER_SEEDS.get();
    }
}
