package themcbros.uselessmod.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.client.renderer.tilentity.ISTERProvider;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.energy.ItemEnergyStorage;
import themcbros.uselessmod.helpers.ShapeHelper;
import themcbros.uselessmod.helpers.TextUtils;
import themcbros.uselessmod.init.StatsInit;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.IWrenchableTileEntity;
import themcbros.uselessmod.util.Styles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("deprecation")
public class CoffeeMachineBlock extends Block implements IWaterLoggable, IBlockItemProvider {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CoffeeMachineBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.FALSE));
        SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states) {
        final VoxelShape[] shape01 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 1, 2, 7, 9, 14));
        final VoxelShape[] shape02 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(1, 0, 5, 3, 10, 11));
//        final VoxelShape[] shape03 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 8.936818495220002, -1.2507292521300055, 13, 12.936818495219999, -0.2507292521300002));
//        final VoxelShape[] shape04 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 6.475538003343319, 15.370139966757726, 13, 10.475538003343315, 16.37013996675773));
        final VoxelShape[] shape05 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 10.695518130045144, 3.5307337294603585, 13, 12.695518130045144, 12.468733729460359));
        final VoxelShape[] shape06 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 9, 3, 7, 11, 13));
        final VoxelShape[] shape07 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 11, 3.5999999999999996, 4, 12, 12.1));
        final VoxelShape[] shape08 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(9, 12.6955, 9, 11, 13.1955, 11));
        final VoxelShape[] shape09 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(9, 12.6955, 5, 11, 13.1955, 7));
        final VoxelShape[] shape10 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(9, 8.6955, 7, 11, 10.6955, 9));
        final VoxelShape[] shape11 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(3, 0, 2, 8, 1, 14));
        final VoxelShape[] shape12 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(8, 0, 13, 13, 1, 14));
        final VoxelShape[] shape13 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(12, 0, 2, 13, 1, 13));
        final VoxelShape[] shape14 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(8, 0, 2, 12, 1, 3));
        final VoxelShape[] shape15 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(8, 0, 3, 12, 1, 13));
        final VoxelShape[] shape16 = ShapeHelper.getRotatedShapes(Block.makeCuboidShape(4, 12.6955, 5, 5, 12.696499999999999, 11));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.get(FACING);
            List<VoxelShape> shapes = Lists.newArrayList();
            shapes.add(shape01[direction.getHorizontalIndex()]);
            shapes.add(shape02[direction.getHorizontalIndex()]);
//            shapes.add(shape03[direction.getHorizontalIndex()]);
//            shapes.add(shape04[direction.getHorizontalIndex()]);
            shapes.add(shape05[direction.getHorizontalIndex()]);
            shapes.add(shape06[direction.getHorizontalIndex()]);
            shapes.add(shape07[direction.getHorizontalIndex()]);
            shapes.add(shape08[direction.getHorizontalIndex()]);
            shapes.add(shape09[direction.getHorizontalIndex()]);
            shapes.add(shape10[direction.getHorizontalIndex()]);
            shapes.add(shape11[direction.getHorizontalIndex()]);
            shapes.add(shape12[direction.getHorizontalIndex()]);
            shapes.add(shape13[direction.getHorizontalIndex()]);
            shapes.add(shape14[direction.getHorizontalIndex()]);
            shapes.add(shape15[direction.getHorizontalIndex()]);
            shapes.add(shape16[direction.getHorizontalIndex()]);
            builder.put(state, ShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()) == Fluids.WATER.getDefaultState());
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return stateIn;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.COFFEE_MACHINE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IWrenchableTileEntity) {
            if (((IWrenchableTileEntity) tileEntity).tryWrench(state, player, handIn, hit))
                return ActionResultType.SUCCESS;
        }
        if (!this.filledTank(worldIn, pos, player, handIn, hit.getFace())) {
            if (tileEntity instanceof INamedContainerProvider && player instanceof ServerPlayerEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, pos);
                player.addStat(StatsInit.INTERACT_WITH_COFFEE_MACHINE);
            }
        }
        return ActionResultType.SUCCESS;
    }

    private boolean filledTank(World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand handIn, Direction side) {
        ItemStack container = playerEntity.getHeldItem(handIn);
        return FluidUtil.getFluidHandler(worldIn, pos, side).map(fluidHandler -> playerEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                .map(playerInventory -> {
                    FluidActionResult result = FluidUtil.tryEmptyContainerAndStow(container, fluidHandler, playerInventory,
                            Integer.MAX_VALUE, playerEntity, true);
                    if (result.isSuccess()) {
                        playerEntity.setHeldItem(handIn, result.getResult());
                        return true;
                    }
                    return false;
                }).orElse(false)).orElse(false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag tooltipFlag) {
        CompoundNBT tag = stack.getChildTag("BlockEntityTag");
        if (tag != null) {
            if (tag.contains("Fluid", Constants.NBT.TAG_COMPOUND)) {
                FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompound("Fluid"));
                tooltip.add((((IFormattableTextComponent) TextUtils.fluidName(fluidStack)).appendString(": ").append(TextUtils.fluidAmount(fluidStack.getAmount())))
                        .setStyle(Styles.MODE_FLUID));
            }
            if (tag.contains("EnergyStored", Constants.NBT.TAG_ANY_NUMERIC)) {
                int forgeEnergy = tag.getInt("EnergyStored");
                tooltip.add(TextUtils.energy(forgeEnergy).setStyle(Styles.USELESS_ENERGY));
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getDefaultState() : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockItem provideBlockItem(Block block, Item.Properties properties) {
        properties.setISTER(ISTERProvider::useless);
        return new CoffeeMachineItem(block, properties);
    }

    private static class CoffeeMachineItem extends BlockItem {

        public CoffeeMachineItem(Block blockIn, Properties builder) {
            super(blockIn, builder);
        }

        @Nullable
        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
            return new CoffeeMachineWrapper(stack);
        }

    }

    private static class CoffeeMachineWrapper implements ICapabilityProvider {

        private final FluidHandlerItemStack fluidHandler;
        private final ItemEnergyStorage energyStorage;

        private CoffeeMachineWrapper(ItemStack container) {
            this.fluidHandler = new FluidHandlerItemStack(container, Config.SERVER_CONFIG.coffeeMachineWaterCapacity.get());
            this.energyStorage = new ItemEnergyStorage(container, Config.SERVER_CONFIG.coffeeMachineEnergyCapacity.get(), 0, 1000);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (cap == CapabilityUselessEnergy.USELESS_ENERGY || cap == CapabilityEnergy.ENERGY)
                return LazyOptional.of(() -> this.energyStorage).cast();
            if (cap == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
                return LazyOptional.of(() -> this.fluidHandler).cast();
            return LazyOptional.empty();
        }
    }

}
