package tk.themcbros.uselessmod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.tileentity.ClosetTileEntity;

public class ClosetBlock extends Block implements IWaterLoggable {
	
	private final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(1d, 1d, 9d, 15d, 15d, 16d);
	private final VoxelShape EAST_SHAPE = Block.makeCuboidShape(0d, 1d, 1d, 7d, 15d, 15d);
	private final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(1d, 1d, 0d, 15d, 15d, 7d);
	private final VoxelShape WEST_SHAPE = Block.makeCuboidShape(9d, 1d, 1d, 16d, 15d, 15d);

	public ClosetBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(BlockStateProperties.WATERLOGGED, Boolean.FALSE)
				.with(BlockStateProperties.OPEN, Boolean.FALSE));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING);
		return 	facing == Direction.NORTH ? NORTH_SHAPE :
				facing == Direction.EAST ? EAST_SHAPE :
				facing == Direction.SOUTH ? SOUTH_SHAPE :
				facing == Direction.WEST ? WEST_SHAPE :
				VoxelShapes.fullCube();
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof ClosetTileEntity && player instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) player).openContainer((INamedContainerProvider) tileEntity);
			}
		}
		return true;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction dir = state.get(BlockStateProperties.HORIZONTAL_FACING);
		return func_220055_a(worldIn, pos.offset(dir.getOpposite()), dir);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.WATERLOGGED, BlockStateProperties.OPEN);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains("uselessmod")) {
			CompoundNBT tag = stack.getTag().getCompound("uselessmod");

			TileEntity tile = worldIn.getTileEntity(pos);

			if (tile instanceof ClosetTileEntity) {
				ClosetTileEntity closet = (ClosetTileEntity) tile;
				closet.setBeddingId(ClosetRegistry.BEDDINGS.get(tag.getString("beddingId")));
				closet.setCasingId(ClosetRegistry.CASINGS.get(tag.getString("casingId")));
			}
		}
	}
	 
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		CompoundNBT tag = stack.getChildTag("uselessmod");
		if (tag != null) {
			IClosetMaterial casingId = ClosetRegistry.CASINGS.get(tag.getString("casingId"));
			IClosetMaterial beddingId = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId"));
			tooltip.add(casingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
			tooltip.add(beddingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
		}
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		for (IClosetMaterial beddingId : ClosetRegistry.BEDDINGS.getKeys())
			for (IClosetMaterial casingId : ClosetRegistry.CASINGS.getKeys())
				items.add(ClosetRegistry.createItemStack(casingId, beddingId));
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {

		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof ClosetTileEntity) {
			IClosetMaterial casing = ((ClosetTileEntity) tile).getCasingId();
			IClosetMaterial bedding = ((ClosetTileEntity) tile).getBeddingId();
			return ClosetRegistry.createItemStack(casing, bedding);
		}

		UselessMod.LOGGER.debug("Unable to get data from ClosetTileEntity. This should never happen.");
		return ItemStack.EMPTY;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = this.getDefaultState();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		
		blockstate = blockstate.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
		return blockstate.with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(BlockStateProperties.WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return stateIn;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ClosetTileEntity();
	}
	
}
