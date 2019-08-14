package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;

public class PaintBucketBlock extends ColorableBlock {
	
	public final VoxelShape SHAPE;

	public PaintBucketBlock(Properties properties) {
		super(properties);
		
		SHAPE = this.generateShape();
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		final ItemStack stack = player.getHeldItem(handIn);
		if(stack.getItem() instanceof DyeItem) {
			DyeItem dyeItem = (DyeItem) stack.getItem();
			float[] colourComponents = dyeItem.getDyeColor().getColorComponentValues();
			int colour = (int) (colourComponents[0] * 255F);
			colour = (int) ((colour << 8) + colourComponents[1] * 255F);
			colour = (int) ((colour << 8) + colourComponents[2] * 255F);
			
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity != null && tileEntity instanceof ColorableTileEntity)
				((ColorableTileEntity) tileEntity).setColor(colour);
			if(!player.abilities.isCreativeMode) stack.shrink(1);
			return true;
		}
		return false;
	}

	private VoxelShape generateShape() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.125, 0, 0.125, 0.875, 0.062, 0.875)); // BOTTOM
		shapes.add(VoxelShapes.create(0.125, 0.062, 0.125, 0.875, 0.625, 0.188)); // WALLN
		shapes.add(VoxelShapes.create(0.125, 0.062, 0.812, 0.875, 0.625, 0.875)); // WALLS
		shapes.add(VoxelShapes.create(0.125, 0.062, 0.188, 0.188, 0.625, 0.812)); // WALLW
		shapes.add(VoxelShapes.create(0.812, 0.062, 0.188, 0.875, 0.625, 0.812)); // WALLE
		shapes.add(VoxelShapes.create(0.062, 0.625, 0.062, 0.938, 0.688, 0.188)); // WALL2N
		shapes.add(VoxelShapes.create(0.062, 0.625, 0.812, 0.938, 0.688, 0.938)); // WALL2S
		shapes.add(VoxelShapes.create(0.062, 0.625, 0.188, 0.188, 0.688, 0.812)); // WALL2W
		shapes.add(VoxelShapes.create(0.812, 0.625, 0.188, 0.938, 0.688, 0.812)); // WALL2E
		shapes.add(VoxelShapes.create(0.188, 0.062, 0.188, 0.812, 0.562, 0.812)); // CUBE

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	@java.lang.Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return SHAPE;
	}

	@java.lang.Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@java.lang.Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@java.lang.Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@java.lang.Override
	public boolean isSolid(BlockState state) {
		return false;
	}
}
