package themcbros.uselessmod.block;

import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import themcbros.uselessmod.init.ItemInit;

public class UselessCropsBlock extends CropsBlock {

    public UselessCropsBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemInit.USELESS_WHEAT_SEEDS.get();
    }
}
