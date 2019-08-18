package tk.themcbros.uselessmod.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class LightSwitchTileEntity extends TileEntity {
	
	private List<BlockPos> blockPositions = new ArrayList<BlockPos>();

	public LightSwitchTileEntity() {
		super(ModTileEntities.LIGHT_SWITCH);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		blockPositions = new ArrayList<BlockPos>();
		if(compound.contains("blocks")) {
			for(long l : compound.getLongArray("blocks")) {
				blockPositions.add(BlockPos.fromLong(l));
			}
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		List<Long> longs = new ArrayList<Long>();
		if(blockPositions.size() > 0) {
			for(BlockPos blockPos : blockPositions) {
				longs.add(blockPos.toLong());
			}
			compound.putLongArray("blocks", longs);
		}
		return super.write(compound);
	}
	
	public List<BlockPos> getBlockPositions() {
		return blockPositions;
	}
	
	public void setBlockPositions(List<BlockPos> blockPositions) {
		this.blockPositions = blockPositions;
	}

}
