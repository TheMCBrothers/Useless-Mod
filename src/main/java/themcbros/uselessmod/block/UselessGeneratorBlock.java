package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.UselessGeneratorTileEntity;

import javax.annotation.Nullable;

public class UselessGeneratorBlock extends Block {

    public UselessGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.USELESS_GENERATOR.get().create();
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            float before = ((LivingEntity) entityIn).getHealth();
            if (entityIn.attackEntityFrom(DamageSource.FALLING_BLOCK, 5.0F)) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity instanceof UselessGeneratorTileEntity) {
                    float after = ((LivingEntity) entityIn).getHealth();
                    ((UselessGeneratorTileEntity) tileEntity).generate((int) ((before - after) * 2));
                }
            }
        }
    }

    @Override
    public VoxelShape getRenderShape(BlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return VoxelShapes.empty();
    }
}
