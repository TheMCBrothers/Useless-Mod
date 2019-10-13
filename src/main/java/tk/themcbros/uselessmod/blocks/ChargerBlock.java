package tk.themcbros.uselessmod.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import tk.themcbros.uselessmod.tileentity.ChargerTileEntity;

import javax.annotation.Nullable;

public class ChargerBlock extends MachineBlock {

	public ChargerBlock(Properties builder) {
		super(builder);
	}

	@Override
	public void openContainer(World world, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof ChargerTileEntity && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity);
		}
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new ChargerTileEntity();
	}
}
