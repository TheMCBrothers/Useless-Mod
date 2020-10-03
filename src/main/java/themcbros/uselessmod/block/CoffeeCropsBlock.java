package themcbros.uselessmod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.Tags;
import themcbros.uselessmod.init.ItemInit;

public class CoffeeCropsBlock extends CropsBlock {

    private final boolean isWild;

    public CoffeeCropsBlock(Properties builder, boolean isWild) {
        super(builder);
        this.isWild = isWild;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemInit.COFFEE_SEEDS.get();
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.isWild ? state.isIn(Tags.Blocks.DIRT) : super.isValidGround(state, worldIn, pos);
    }
}
