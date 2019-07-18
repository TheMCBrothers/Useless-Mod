package tk.themcbros.uselessmod.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.tileentity.CompressorTileEntity;

public class CompressorBlock extends MachineBlock {

	public CompressorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new CompressorTileEntity();
	}

	@Override
	public void openContainer(World world, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof CompressorTileEntity && player instanceof ServerPlayerEntity) {
			((ServerPlayerEntity) player).openContainer((INamedContainerProvider) tileEntity);
		}
	}

}
