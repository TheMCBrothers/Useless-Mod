package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import tk.themcbros.uselessmod.lists.ModItems;

public class UselessCropsBlock extends CropsBlock {

	public UselessCropsBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return ModItems.USELESS_WHEAT_SEEDS;
	}

}
