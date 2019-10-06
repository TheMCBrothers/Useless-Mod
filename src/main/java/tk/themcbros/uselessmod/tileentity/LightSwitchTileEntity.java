package tk.themcbros.uselessmod.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class LightSwitchTileEntity extends TileEntity {
	
	private List<BlockPos> blockPositions = Lists.newArrayList();

	public LightSwitchTileEntity() {
		super(ModTileEntities.LIGHT_SWITCH);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		blockPositions = Lists.newArrayList();
		if(compound.contains("Lights")) {
			for(long l : compound.getLongArray("Lights")) {
				blockPositions.add(BlockPos.fromLong(l));
			}
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		List<Long> longs = Lists.newArrayList();
		if(blockPositions.size() > 0) {
			for(BlockPos blockPos : blockPositions) {
				longs.add(blockPos.toLong());
			}
			compound.putLongArray("Lights", longs);
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
