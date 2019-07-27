package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeSeedsBlock extends CropsBlock {

	public CoffeeSeedsBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return ModItems.COFFEE_SEEDS;
	}

}
