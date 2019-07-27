package tk.themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {
	
	private final int maxTransfer;

	public EnergyCableTileEntity(int maxTransfer) {
		super(ModTileEntities.ENERGY_CABLE);
		this.maxTransfer = maxTransfer;
	}
	
	public EnergyCableTileEntity() {
		this(100);
	}
	
	public void updateConnections() {
		boolean[] sides = new boolean[Direction.values().length];
		for(Direction direction : Direction.values()) {
			BlockPos pos = this.pos.offset(direction);
			TileEntity tileEntity = this.world.getTileEntity(pos);
			tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent((energyStorage) -> {
				sides[direction.getIndex()] = energyStorage.canExtract() || energyStorage.canReceive();
			});
		}
		
		BlockState state = this.world.getBlockState(this.pos);
		state = state.with(BlockStateProperties.NORTH, Boolean.valueOf(sides[Direction.NORTH.getIndex()]))
				.with(BlockStateProperties.SOUTH, Boolean.valueOf(sides[Direction.SOUTH.getIndex()]))
				.with(BlockStateProperties.EAST, Boolean.valueOf(sides[Direction.EAST.getIndex()]))
				.with(BlockStateProperties.WEST, Boolean.valueOf(sides[Direction.WEST.getIndex()]))
				.with(BlockStateProperties.UP, Boolean.valueOf(sides[Direction.UP.getIndex()]))
				.with(BlockStateProperties.DOWN, Boolean.valueOf(sides[Direction.DOWN.getIndex()]));
		this.world.setBlockState(this.pos, state);
		
		this.markDirty();
	}
	
	@Override
	public void tick() {
		if(!world.isRemote) {
			@SuppressWarnings("unused")
			int i = maxTransfer;
		}
	}

}
