package tk.themcbros.uselessmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.helper.UselessUtils;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.CanvasTileEntity;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;

public class CanvasBlock extends Block {

	public CanvasBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CanvasTileEntity();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity != null && tileEntity instanceof CanvasTileEntity)
				((CanvasTileEntity) tileEntity).setColor(stack.getTag().getInt("color"));
		}
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(player.getHeldItem(handIn).getItem() == ModItems.PAINT_BRUSH) {
			ColorableTileEntity canvas = (ColorableTileEntity) worldIn.getTileEntity(pos);
			canvas.setColor(player.getHeldItem(handIn).getTag().getInt("color"));
			worldIn.markForRerender(pos);
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
		ItemStack stack = new ItemStack(ModBlocks.CANVAS);
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
	
}
