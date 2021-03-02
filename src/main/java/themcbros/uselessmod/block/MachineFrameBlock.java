package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import themcbros.uselessmod.helpers.WrenchUtils;

import java.util.stream.Stream;

public class MachineFrameBlock extends Block {

    private final VoxelShape SHAPE;

    public MachineFrameBlock(Properties properties) {
        super(properties);
        SHAPE = this.generateShape();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if (!stack.isEmpty()) {
            if (WrenchUtils.isWrench(stack)) {
                if (player.isSneaking()) {
                    WrenchUtils.dismantleBlock(state, worldIn, pos, null, null, null);
                    return ActionResultType.func_233537_a_(worldIn.isRemote);
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.fullCube();
    }

    private VoxelShape generateShape() {
        return Stream.of(
                Block.makeCuboidShape(1, 14, 15, 3, 15, 16),
                Block.makeCuboidShape(2, 13, 15, 4, 14, 16),
                Block.makeCuboidShape(3, 12, 15, 5, 13, 16),
                Block.makeCuboidShape(7, 8, 15, 8, 9, 16),
                Block.makeCuboidShape(6, 9, 15, 8, 10, 16),
                Block.makeCuboidShape(5, 10, 15, 7, 11, 16),
                Block.makeCuboidShape(4, 11, 15, 6, 12, 16),
                Block.makeCuboidShape(1, 1, 15, 3, 2, 16),
                Block.makeCuboidShape(2, 2, 15, 4, 3, 16),
                Block.makeCuboidShape(3, 3, 15, 5, 4, 16),
                Block.makeCuboidShape(7, 7, 15, 8, 8, 16),
                Block.makeCuboidShape(6, 6, 15, 8, 7, 16),
                Block.makeCuboidShape(5, 5, 15, 7, 6, 16),
                Block.makeCuboidShape(4, 4, 15, 6, 5, 16),
                Block.makeCuboidShape(13, 14, 15, 15, 15, 16),
                Block.makeCuboidShape(12, 13, 15, 14, 14, 16),
                Block.makeCuboidShape(11, 12, 15, 13, 13, 16),
                Block.makeCuboidShape(8, 8, 15, 9, 9, 16),
                Block.makeCuboidShape(8, 9, 15, 10, 10, 16),
                Block.makeCuboidShape(9, 10, 15, 11, 11, 16),
                Block.makeCuboidShape(10, 11, 15, 12, 12, 16),
                Block.makeCuboidShape(13, 1, 15, 15, 2, 16),
                Block.makeCuboidShape(12, 2, 15, 14, 3, 16),
                Block.makeCuboidShape(11, 3, 15, 13, 4, 16),
                Block.makeCuboidShape(8, 7, 15, 9, 8, 16),
                Block.makeCuboidShape(8, 6, 15, 10, 7, 16),
                Block.makeCuboidShape(9, 5, 15, 11, 6, 16),
                Block.makeCuboidShape(10, 4, 15, 12, 5, 16),
                Block.makeCuboidShape(15, 1, 1, 16, 2, 3),
                Block.makeCuboidShape(15, 2, 2, 16, 3, 4),
                Block.makeCuboidShape(15, 3, 3, 16, 4, 5),
                Block.makeCuboidShape(15, 7, 7, 16, 8, 8),
                Block.makeCuboidShape(15, 6, 6, 16, 7, 8),
                Block.makeCuboidShape(15, 5, 5, 16, 6, 7),
                Block.makeCuboidShape(15, 4, 4, 16, 5, 6),
                Block.makeCuboidShape(15, 14, 1, 16, 15, 3),
                Block.makeCuboidShape(15, 13, 2, 16, 14, 4),
                Block.makeCuboidShape(15, 12, 3, 16, 13, 5),
                Block.makeCuboidShape(15, 8, 7, 16, 9, 8),
                Block.makeCuboidShape(15, 9, 6, 16, 10, 8),
                Block.makeCuboidShape(15, 10, 5, 16, 11, 7),
                Block.makeCuboidShape(15, 11, 4, 16, 12, 6),
                Block.makeCuboidShape(15, 1, 13, 16, 2, 15),
                Block.makeCuboidShape(15, 2, 12, 16, 3, 14),
                Block.makeCuboidShape(15, 3, 11, 16, 4, 13),
                Block.makeCuboidShape(15, 7, 8, 16, 8, 9),
                Block.makeCuboidShape(15, 6, 8, 16, 7, 10),
                Block.makeCuboidShape(15, 5, 9, 16, 6, 11),
                Block.makeCuboidShape(15, 4, 10, 16, 5, 12),
                Block.makeCuboidShape(15, 14, 13, 16, 15, 15),
                Block.makeCuboidShape(15, 13, 12, 16, 14, 14),
                Block.makeCuboidShape(15, 12, 11, 16, 13, 13),
                Block.makeCuboidShape(15, 8, 8, 16, 9, 9),
                Block.makeCuboidShape(15, 9, 8, 16, 10, 10),
                Block.makeCuboidShape(15, 10, 9, 16, 11, 11),
                Block.makeCuboidShape(15, 11, 10, 16, 12, 12),
                Block.makeCuboidShape(1, 14, 0, 3, 15, 1),
                Block.makeCuboidShape(2, 13, 0, 4, 14, 1),
                Block.makeCuboidShape(3, 12, 0, 5, 13, 1),
                Block.makeCuboidShape(7, 8, 0, 8, 9, 1),
                Block.makeCuboidShape(6, 9, 0, 8, 10, 1),
                Block.makeCuboidShape(5, 10, 0, 7, 11, 1),
                Block.makeCuboidShape(4, 11, 0, 6, 12, 1),
                Block.makeCuboidShape(1, 1, 0, 3, 2, 1),
                Block.makeCuboidShape(2, 2, 0, 4, 3, 1),
                Block.makeCuboidShape(3, 3, 0, 5, 4, 1),
                Block.makeCuboidShape(7, 7, 0, 8, 8, 1),
                Block.makeCuboidShape(6, 6, 0, 8, 7, 1),
                Block.makeCuboidShape(5, 5, 0, 7, 6, 1),
                Block.makeCuboidShape(4, 4, 0, 6, 5, 1),
                Block.makeCuboidShape(13, 14, 0, 15, 15, 1),
                Block.makeCuboidShape(12, 13, 0, 14, 14, 1),
                Block.makeCuboidShape(11, 12, 0, 13, 13, 1),
                Block.makeCuboidShape(8, 8, 0, 9, 9, 1),
                Block.makeCuboidShape(8, 9, 0, 10, 10, 1),
                Block.makeCuboidShape(9, 10, 0, 11, 11, 1),
                Block.makeCuboidShape(10, 11, 0, 12, 12, 1),
                Block.makeCuboidShape(13, 1, 0, 15, 2, 1),
                Block.makeCuboidShape(12, 2, 0, 14, 3, 1),
                Block.makeCuboidShape(11, 3, 0, 13, 4, 1),
                Block.makeCuboidShape(8, 7, 0, 9, 8, 1),
                Block.makeCuboidShape(8, 6, 0, 10, 7, 1),
                Block.makeCuboidShape(9, 5, 0, 11, 6, 1),
                Block.makeCuboidShape(10, 4, 0, 12, 5, 1),
                Block.makeCuboidShape(14, 0, 0, 16, 1, 2),
                Block.makeCuboidShape(0, 0, 0, 2, 1, 2),
                Block.makeCuboidShape(0, 0, 14, 2, 1, 16),
                Block.makeCuboidShape(14, 0, 14, 16, 1, 16),
                Block.makeCuboidShape(1, 1, 1, 15, 15, 15),
                Block.makeCuboidShape(14, 15, 0, 16, 16, 2),
                Block.makeCuboidShape(14, 15, 14, 16, 16, 16),
                Block.makeCuboidShape(0, 15, 14, 2, 16, 16),
                Block.makeCuboidShape(0, 15, 0, 2, 16, 2),
                Block.makeCuboidShape(0, 10, 9, 1, 11, 11),
                Block.makeCuboidShape(0, 9, 8, 1, 10, 10),
                Block.makeCuboidShape(0, 8, 8, 1, 9, 9),
                Block.makeCuboidShape(0, 12, 11, 1, 13, 13),
                Block.makeCuboidShape(0, 13, 12, 1, 14, 14),
                Block.makeCuboidShape(0, 14, 13, 1, 15, 15),
                Block.makeCuboidShape(0, 4, 10, 1, 5, 12),
                Block.makeCuboidShape(0, 5, 9, 1, 6, 11),
                Block.makeCuboidShape(0, 6, 8, 1, 7, 10),
                Block.makeCuboidShape(0, 7, 8, 1, 8, 9),
                Block.makeCuboidShape(0, 3, 11, 1, 4, 13),
                Block.makeCuboidShape(0, 2, 12, 1, 3, 14),
                Block.makeCuboidShape(0, 1, 13, 1, 2, 15),
                Block.makeCuboidShape(0, 11, 4, 1, 12, 6),
                Block.makeCuboidShape(0, 10, 5, 1, 11, 7),
                Block.makeCuboidShape(0, 9, 6, 1, 10, 8),
                Block.makeCuboidShape(0, 8, 7, 1, 9, 8),
                Block.makeCuboidShape(0, 12, 3, 1, 13, 5),
                Block.makeCuboidShape(0, 13, 2, 1, 14, 4),
                Block.makeCuboidShape(0, 14, 1, 1, 15, 3),
                Block.makeCuboidShape(0, 4, 4, 1, 5, 6),
                Block.makeCuboidShape(0, 5, 5, 1, 6, 7),
                Block.makeCuboidShape(0, 6, 6, 1, 7, 8),
                Block.makeCuboidShape(0, 7, 7, 1, 8, 8),
                Block.makeCuboidShape(0, 3, 3, 1, 4, 5),
                Block.makeCuboidShape(0, 2, 2, 1, 3, 4),
                Block.makeCuboidShape(0, 1, 1, 1, 2, 3),
                Block.makeCuboidShape(0, 11, 10, 1, 12, 12)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR))
                .orElse(VoxelShapes.fullCube());
    }

}
