package tk.themcbros.uselessmod.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.helper.UselessUtils;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;

public class PaintBrushItem extends Item {
	
	public static final int[] WHITE = new int[] {249,255,254};
	private static final int DEFAULT_USES = 16;

	public PaintBrushItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		ItemStack itemstack = context.getItem();
		BlockPos blockpos = context.getPos();
		Direction direction = context.getFace();
		BlockState blockstate = world.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block == ModBlocks.CANVAS && this.hasUsesLeft(itemstack)) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof ColorableTileEntity) {
				ColorableTileEntity colorBlock = (ColorableTileEntity) tileentity;
				if(colorBlock.getColor() != this.getColor(itemstack)) {
					colorBlock.setColor(this.getColor(itemstack));
					colorBlock.markDirty();
					if(!player.abilities.isCreativeMode) this.useBrush(itemstack);
					world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
					return ActionResultType.SUCCESS;
				}
			}
		} else if (block == ModBlocks.PAINT_BUCKET && direction == Direction.UP) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof ColorableTileEntity) {
				ColorableTileEntity colorBlock = (ColorableTileEntity) tileentity;
				if(this.getLeftUses(itemstack) < DEFAULT_USES || this.getColor(itemstack) != colorBlock.getColor()) {
					this.setColor(itemstack, colorBlock.getColor());
					this.setUses(itemstack, DEFAULT_USES);
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.PASS;
	}
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        int[] rgb = WHITE;
		if (stack.hasTag() && stack.getTag().contains("color")) {
			rgb = UselessUtils.rgbIntToIntArray(stack.getTag().getInt("color"));

        	tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".tooltip", TextFormatting.RED + "" + rgb[0] + TextFormatting.GREEN + " " + rgb[1] + TextFormatting.BLUE + " " + rgb[2]));
		}
	
		if (stack.hasTag() && stack.getTag().contains("uses"))
			tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".tooltip_uses", TextFormatting.RED + "" + stack.getTag().getInt("uses")));
	}

	public boolean hasUsesLeft(ItemStack stack) {
		return getLeftUses(stack) > 0;
	}

	public int getLeftUses(ItemStack stack) {
		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound != null)
			return nbttagcompound.getInt("uses");

		return 0;
	}

	public void useBrush(ItemStack stack) {
		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound == null) {
			nbttagcompound = new CompoundNBT();
			stack.setTag(nbttagcompound);
		}

		int uses = nbttagcompound.contains("uses") ? nbttagcompound.getInt("uses") : 0;
		if (uses > 0) {
			uses--;
			nbttagcompound.putInt("uses", uses);
			if(uses <= 0) stack.setTag(null);
		}
	}

	public void setUses(ItemStack stack, int uses) {
		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound == null) {
			nbttagcompound = new CompoundNBT();
			stack.setTag(nbttagcompound);
		}

		nbttagcompound.putInt("uses", uses);
	}

	public boolean hasColor(ItemStack stack) {
		CompoundNBT nbttagcompound = stack.getTag();
		return nbttagcompound != null && nbttagcompound.contains("color", 3);

	}

	public int getColor(ItemStack stack) {

		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound != null)
			return nbttagcompound.getInt("color");

		return 16383998;

	}

	public void removeColor(ItemStack stack) {

		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound != null)
			nbttagcompound.remove("color");

	}

	public void setColor(ItemStack stack, int color) {

		CompoundNBT nbttagcompound = stack.getTag();

		if (nbttagcompound == null) {
			nbttagcompound = new CompoundNBT();
			stack.setTag(nbttagcompound);
		}

		nbttagcompound.putInt("color", color);
	}

}
