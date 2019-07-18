package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.tileentity.CreativePowerBlockTileEntity;

public class CreativePowerBlock extends Block {

	public CreativePowerBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CreativePowerBlockTileEntity();
	}

}
