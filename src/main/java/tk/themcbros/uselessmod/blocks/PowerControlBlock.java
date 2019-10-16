package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.helper.IHammer;
import tk.themcbros.uselessmod.tileentity.PowerControlTileEntity;

public class PowerControlBlock extends Block implements IHammer {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public PowerControlBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.FALSE));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
								boolean isMoving) {
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		boolean flag1 = state.get(POWERED);
		if (flag && !flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.TRUE), 1 | 2 | 4);
		} else if (!flag && flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.FALSE), 1 | 2 | 4);
		}

	}

	@Override
	public ActionResultType onHammer(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		TileEntity tileEntity = world.getTileEntity(pos);
		return tileEntity instanceof IHammer ? ((IHammer) tileEntity).onHammer(context) : ActionResultType.PASS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PowerControlTileEntity();
	}

}
