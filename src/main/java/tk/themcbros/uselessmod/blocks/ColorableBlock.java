package tk.themcbros.uselessmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.helper.UselessUtils;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;

public class ColorableBlock extends Block {

	public ColorableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ColorableTileEntity();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity != null && tileEntity instanceof ColorableTileEntity) {
			if(stack.hasTag() && stack.getTag() != null && stack.getTag().contains("color"))
				((ColorableTileEntity) tileEntity).setColor(stack.getTag().getInt("color"));
			else
				((ColorableTileEntity) tileEntity).setColor(16383998);
		}
		worldIn.notifyBlockUpdate(pos, state, state, 3);
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
		TileEntity tileEntity = world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(tileEntity != null && tileEntity instanceof ColorableTileEntity) {
			stack.setTag(new CompoundNBT());
			stack.getTag().putInt("color", ((ColorableTileEntity) tileEntity).getColor());
		}
		return stack;
	}
	
}
