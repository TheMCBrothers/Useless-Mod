package themcbros.uselessmod.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.helpers.ShapeHelper;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.CoffeeCupTileEntity;
import themcbros.uselessmod.tileentity.IWrenchableTileEntity;

import javax.annotation.Nullable;
import java.util.List;

public class CupBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private final ImmutableMap<BlockState, VoxelShape> SHAPES;
    private final VoxelShape RAYTRACE_SHAPE;

    public CupBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.FALSE).with(FACING, Direction.NORTH));
        SHAPES = this.generateShapes(this.stateContainer.getValidStates());
        RAYTRACE_SHAPE = Block.makeCuboidShape(5, 0, 5, 11, 7, 11);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IWrenchableTileEntity) {
            if (((IWrenchableTileEntity) tileEntity).tryWrench(state, player, handIn, hit))
                return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states) {
        final VoxelShape[] shape1 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(10, 0, 6, 11, 7, 10));
        final VoxelShape[] shape2 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(6, 0, 6, 10, 1, 10));
        final VoxelShape[] shape3 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(5, 0, 6, 6, 7, 10));
        final VoxelShape[] shape4 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(6, 0, 10, 10, 7, 11));
        final VoxelShape[] shape5 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(6, 0, 5, 10, 7, 6));
        final VoxelShape[] shape6 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(11, 1, 7.5, 13, 2, 8.5));
        final VoxelShape[] shape7 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(11, 5, 7.5, 13, 6, 8.5));
        final VoxelShape[] shape8 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(13, 2, 7.5, 14, 5, 8.5));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
        for (BlockState state : states) {
            List<VoxelShape> shapes = Lists.newArrayList();
            Direction facing = state.get(FACING);
            shapes.add(shape1[facing.getHorizontalIndex()]);
            shapes.add(shape2[facing.getHorizontalIndex()]);
            shapes.add(shape3[facing.getHorizontalIndex()]);
            shapes.add(shape4[facing.getHorizontalIndex()]);
            shapes.add(shape5[facing.getHorizontalIndex()]);
            shapes.add(shape6[facing.getHorizontalIndex()]);
            shapes.add(shape7[facing.getHorizontalIndex()]);
            shapes.add(shape8[facing.getHorizontalIndex()]);
            builder.put(state, ShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return stateIn;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return RAYTRACE_SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER)
                .with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof CoffeeCupTileEntity) {
            CoffeeType type = ItemInit.COFFEE_CUP.get().getCoffeeType(stack);
            ((CoffeeCupTileEntity) tileEntity).setCoffeeType(type);
        }
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof CoffeeCupTileEntity) {
            CoffeeType coffeeType = ((CoffeeCupTileEntity) tileEntity).getCoffeeType();
            if (coffeeType != null) return ItemInit.COFFEE_CUP.get().getStack(coffeeType);
        }
        return new ItemStack(ItemInit.CUP.get());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.COFFEE_CUP.get().create();
    }
}
