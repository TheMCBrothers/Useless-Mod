package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;
import net.themcbrothers.lib.wrench.WrenchableBlock;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintBucketBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class PaintBucketBlock extends BaseEntityBlock implements SimpleWaterloggedBlock, WrenchableBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final VoxelShape SHAPE;

    public PaintBucketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.TRUE));
        SHAPE = makeShape();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return defaultBlockState().setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos newPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, newState, world, pos, newPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // Interaction with Wrench
        if (this.tryWrench(state, level, pos, player, hand, hit)) {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (level.getBlockEntity(pos) instanceof PaintBucketBlockEntity blockEntity) {
            final ItemStack stack = player.getItemInHand(hand);

            // Interaction with Dye Item
            if (DyeColor.getColor(stack) != null) {
                player.setItemInHand(hand, ItemHandlerHelper.insertItem(blockEntity.stackHandler, stack, false));
                return InteractionResult.sidedSuccess(level.isClientSide);
            }

            // Interaction with bucket or fluid container
            LazyOptional<IFluidHandlerItem> fluidHandler = FluidUtil.getFluidHandler(stack);
            if (fluidHandler.isPresent()) {
                fluidHandler.invalidate();
                if (FluidUtil.interactWithFluidHandler(player, hand, level, pos, null)) {
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }

                return InteractionResult.FAIL;
            }

            // Interaction with Stick
            if (stack.is(Tags.Items.RODS_WOODEN)) {
                if (blockEntity.colorTank.getFluid().getFluid().is(FluidTags.WATER)
                        && blockEntity.colorTank.getFluidAmount() == FluidType.BUCKET_VOLUME) {
                    DyeColor color = DyeColor.getColor(blockEntity.stackHandler.getStackInSlot(0));
                    if (color != null) {
                        blockEntity.setColor(color.getTextureDiffuseColors());
                        blockEntity.stackHandler.setStackInSlot(0, ItemStack.EMPTY);
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }

            if (blockEntity.hasColor()) {
                // Interaction with Paint Brush
                if (stack.is(ModItems.PAINT_BRUSH.get()) && !stack.isDamaged()) {
                    int bucketColor = blockEntity.getColor();
                    int brushColor = stack.getOrCreateTag().getInt("Color");

                    if (bucketColor != brushColor || stack.isDamaged()) {
                        stack.getOrCreateTag().putInt("Color", bucketColor);
                        stack.setDamageValue(0);
                        blockEntity.colorTank.drain(100, IFluidHandler.FluidAction.EXECUTE);
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof PaintBucketBlockEntity blockEntity) {
            Containers.dropContents(level, pos, NonNullList.of(blockEntity.stackHandler.getStackInSlot(0)));
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext collision) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.empty();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType pathComputationType) {
        return false;
    }

    private VoxelShape makeShape() {
        return Stream.of(
                Block.box(10, 0, 6, 11, 1, 10),
                Block.box(5, 0, 6, 6, 1, 10),
                Block.box(6, 0, 4, 10, 8, 5),
                Block.box(6, 0, 11, 10, 8, 12),
                Block.box(4, 0, 6, 5, 8, 10),
                Block.box(11, 0, 6, 12, 8, 10),
                Block.box(5, 0, 5, 6, 8, 6),
                Block.box(5, 0, 10, 6, 8, 11),
                Block.box(10, 0, 10, 11, 8, 11),
                Block.box(10, 0, 5, 11, 8, 6),
                Block.box(6, 0, 5, 10, 1, 11)
        ).reduce(Shapes::or).orElseThrow();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.PAINT_BUCKET.get().create(pos, state);
    }
}
