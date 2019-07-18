package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.helper.UselessUtils;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;
import tk.themcbros.uselessmod.tileentity.PaintBucketTileEntity;

import java.util.List;
import java.util.ArrayList;

public class PaintBucketBlock extends Block {

	public final VoxelShape SHAPE;

	public PaintBucketBlock(Properties properties) {
		super(properties);

		SHAPE = this.generateShape();
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PaintBucketTileEntity();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity != null && tileEntity instanceof PaintBucketTileEntity)
				((PaintBucketTileEntity) tileEntity).setColor(stack.getTag().getInt("color"));
		}
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(player.getHeldItem(handIn).getItem() == ModItems.PAINT_BRUSH) {
			ColorableTileEntity tileEntity = (ColorableTileEntity) worldIn.getTileEntity(pos);
			player.getHeldItem(handIn).getTag().putInt("color", tileEntity.getColor());
			return true;
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
        int[] rgb = PaintBrushItem.WHITE;
        if(stack.hasTag() && stack.getTag().contains("color")) {
            rgb = UselessUtils.rgbIntToIntArray(stack.getTag().getInt("color"));
        }

        tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".tooltip", TextFormatting.RED + "" + rgb[0] + TextFormatting.GREEN + " " + rgb[1] + TextFormatting.BLUE + " " + rgb[2]));
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		ColorableTileEntity canvas = (ColorableTileEntity) world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		CompoundNBT compound = new CompoundNBT();
		if(canvas != null)
			compound.putInt("color", canvas.getColor());
		else
			compound.putInt("color", 0xFFFFFF);
		stack.setTag(compound);
		return stack;
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		for(DyeColor color : DyeColor.values()) {
            ItemStack baseColours = new ItemStack(this);
            baseColours.setTag(new CompoundNBT());
            float[] colourComponents = color.getColorComponentValues();
            int colour = (int) (colourComponents[0] * 255F);
            colour = (int) ((colour << 8) + colourComponents[1] * 255F);
            colour = (int) ((colour << 8) + colourComponents[2] * 255F);


            baseColours.getTag().putInt("color", colour);
            items.add(baseColours);
        }
	}

	private VoxelShape generateShape()
	{
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
		for(VoxelShape shape : shapes)
		{
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	@java.lang.Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
