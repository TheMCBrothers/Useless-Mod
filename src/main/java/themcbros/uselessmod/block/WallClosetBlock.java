package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;
import themcbros.uselessmod.api.wall_closet.ClosetMaterialInit;
import themcbros.uselessmod.helpers.ItemHelper;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.WallClosetTileEntity;
import themcbros.uselessmod.util.Styles;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WallClosetBlock extends Block implements IWaterLoggable {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(1, 1, 9, 15, 15, 16);
    private static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(0, 1, 1, 7, 15, 15);
    private static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(1, 1, 0, 15, 15, 7);
    private static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(9, 1, 1, 16, 15, 15);

    public WallClosetBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, WATERLOGGED);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        for (ClosetMaterial material : UselessRegistries.CLOSET_MATERIALS) {
            ItemStack stack = new ItemStack(this);
            stack.getOrCreateTag().putString("Material", String.valueOf(material.getRegistryName()));
            items.add(stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getOrCreateTag().contains("Material", Constants.NBT.TAG_STRING)) {
            ClosetMaterial mat = UselessRegistries.CLOSET_MATERIALS.getValue(ResourceLocation.tryCreate(stack.getOrCreateTag().getString("Material")));
            if (mat != null)
                tooltip.add(new TranslationTextComponent(mat.getBlock().getTranslationKey()).setStyle(Styles.USELESS_ENERGY));
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction facing = state.get(FACING);
        return facing == Direction.WEST ? SHAPE_WEST : facing == Direction.SOUTH ? SHAPE_SOUTH : facing == Direction.EAST ? SHAPE_EAST : SHAPE_NORTH;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()) == Fluids.WATER.getDefaultState());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (hit.getFace() == state.get(FACING)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof WallClosetTileEntity) {
                WallClosetTileEntity tileCloset = (WallClosetTileEntity) tileEntity;
                if (!worldIn.isRemote) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, tileCloset, pos);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.WALL_CLOSET.get().create();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity != null && state.getBlock() != newState.getBlock()) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                    .ifPresent(itemHandler -> ItemHelper.dropItemHandlerItems(worldIn, pos, itemHandler));
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof WallClosetTileEntity) {
            ((WallClosetTileEntity) tileentity).closetTick();
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getDefaultState() : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        ItemStack stack = new ItemStack(this);
        String mat = String.valueOf(getWallCloset(world, pos).getRegistryName());
        stack.getOrCreateTag().putString("Material", mat);
        return stack;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof WallClosetTileEntity) {
            if (stack.getOrCreateTag().contains("Material", Constants.NBT.TAG_STRING)) {
                ResourceLocation key = ResourceLocation.tryCreate(stack.getOrCreateTag().getString("Material"));
                ClosetMaterial mat = UselessRegistries.CLOSET_MATERIALS.getValue(key);
                if (mat != null) {
                    ((WallClosetTileEntity) tileEntity).setClosetMaterial(() -> mat);
                }
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        return Objects.requireNonNull(world.getTileEntity(pos)).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                .map(ItemHandlerHelper::calcRedstoneFromInventory).orElse(0);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return this.rotate(state, direction);
    }

    // BLOCK OVERRIDES

    private ClosetMaterial getWallCloset(IBlockReader world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof WallClosetTileEntity) {
            return ((WallClosetTileEntity) tileEntity).getMaterial();
        }
        return ClosetMaterialInit.OAK_PLANKS.get();
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return getWallCloset(world, pos).getBlock().getSoundType(state, world, pos, entity);
    }

    @Override
    public float getExplosionResistance(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion) {
        return getWallCloset(world, pos).getBlock().getExplosionResistance(state, world, pos, explosion);
    }

    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return getWallCloset(world, pos).getBlock().getSlipperiness(state, world, pos, entity);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return getWallCloset(world, pos).getBlock().getLightValue(state, world, pos);
    }

}
