package themcbros.uselessmod.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import themcbros.uselessmod.helpers.ColorUtils;
import themcbros.uselessmod.helpers.ItemHelper;
import themcbros.uselessmod.helpers.ShapeHelper;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.IWrenchableTileEntity;
import themcbros.uselessmod.tileentity.PaintBucketTileEntity;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author TheMCBrothers
 */
public class PaintBucketBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final VoxelShape SHAPE;
    private final VoxelShape RAYTRACE_SHAPE;

    public PaintBucketBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.FALSE));
        this.SHAPE = this.generateShape();
        this.RAYTRACE_SHAPE = this.generateRaytraceShape();
    }

    private VoxelShape generateShape() {
        final List<VoxelShape> shapes = Lists.newArrayList();
        shapes.add(Block.makeCuboidShape(10, 0, 6, 11, 1, 10));
        shapes.add(Block.makeCuboidShape(5, 0, 6, 6, 1, 10));
        shapes.add(Block.makeCuboidShape(6, 0, 4, 10, 8, 5));
        shapes.add(Block.makeCuboidShape(6, 0, 11, 10, 8, 12));
        shapes.add(Block.makeCuboidShape(4, 0, 6, 5, 8, 10));
        shapes.add(Block.makeCuboidShape(11, 0, 6, 12, 8, 10));
        shapes.add(Block.makeCuboidShape(5, 0, 5, 6, 8, 6));
        shapes.add(Block.makeCuboidShape(5, 0, 10, 6, 8, 11));
        shapes.add(Block.makeCuboidShape(10, 0, 10, 11, 8, 11));
        shapes.add(Block.makeCuboidShape(10, 0, 5, 11, 8, 6));
        shapes.add(Block.makeCuboidShape(6, 0, 5, 10, 1, 11));
        return ShapeHelper.combineAll(shapes);
    }

    private VoxelShape generateRaytraceShape() {
        final List<VoxelShape> shapes = Lists.newArrayList();
        shapes.add(Block.makeCuboidShape(5, 0, 5, 10, 10, 10));
        return ShapeHelper.combineAll(shapes);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
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
        return SHAPE;
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return RAYTRACE_SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        final ItemStack stack = player.getHeldItem(handIn);
        final DyeColor color = DyeColor.getColor(stack);
        final LazyOptional<IFluidHandlerItem> fluidHandlerItem = FluidUtil.getFluidHandler(stack);
        if ((color != null || fluidHandlerItem.isPresent() || stack.getItem() == Items.STICK) && tileEntity instanceof PaintBucketTileEntity) {
            if (stack.getItem() == Items.STICK) {
                PaintBucketTileEntity paintBucket = (PaintBucketTileEntity) tileEntity;
                if (paintBucket.color.getFluid().getFluid().isIn(FluidTags.WATER)
                        && paintBucket.color.getFluidAmount() == FluidAttributes.BUCKET_VOLUME) {
                    DyeColor col = DyeColor.getColor(paintBucket.stackHandler.getStackInSlot(0));
                    if (col != null) {
                        paintBucket.color.setColor(col.getColorValue());
                        paintBucket.stackHandler.setStackInSlot(0, ItemStack.EMPTY);
                        return ActionResultType.func_233537_a_(worldIn.isRemote);
                    }
                }
                return ActionResultType.FAIL;
            }

            if (fluidHandlerItem.isPresent()) {
                fluidHandlerItem.invalidate();
                boolean success = FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace());
                if (success)
                    return ActionResultType.func_233537_a_(worldIn.isRemote);
                return ActionResultType.FAIL;
            }
            if (color != null) {
                if (!((PaintBucketTileEntity) tileEntity).color.hasColor()) {
                    ItemStack stack1 = ((PaintBucketTileEntity) tileEntity).stackHandler.insertItem(0, stack, false);
                    if (!ItemStack.areItemStacksEqual(stack, stack1)) {
                        stack.shrink(1);
                        return ActionResultType.func_233537_a_(worldIn.isRemote);
                    }
                    return ActionResultType.FAIL;
                }
            }
        }

        if (tileEntity instanceof IWrenchableTileEntity) {
            if (((IWrenchableTileEntity) tileEntity).tryWrench(state, player, handIn, hit))
                return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (!state.isIn(newState.getBlock()) && tileEntity instanceof PaintBucketTileEntity) {
            PaintBucketTileEntity paintBucket = (PaintBucketTileEntity) tileEntity;
            ItemHelper.dropItemHandlerItems(worldIn, pos, paintBucket.stackHandler);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getChildTag("BlockEntityTag");
        if (tag != null && tag.contains("Color", Constants.NBT.TAG_COMPOUND)) {
            CompoundNBT fluidTag = tag.getCompound("Color");
            if (fluidTag.contains("Tag", Constants.NBT.TAG_COMPOUND)
                    && fluidTag.getCompound("Tag").contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
                int col = fluidTag.getCompound("Tag").getInt("Color");
                tooltip.add(ColorUtils.getHexAsText(col));
            }
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.PAINT_BUCKET.get().create();
    }
}
