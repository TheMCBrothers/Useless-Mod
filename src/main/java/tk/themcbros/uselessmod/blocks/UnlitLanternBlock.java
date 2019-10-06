package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class UnlitLanternBlock extends LanternBlock {

	public UnlitLanternBlock(Properties props) {
		super(props);
	}
	
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.LANTERN);
	}
	
	@Override
	public Item asItem() {
		return Items.LANTERN;
	}

}
