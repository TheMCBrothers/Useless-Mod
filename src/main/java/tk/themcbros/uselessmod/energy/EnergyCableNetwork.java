package tk.themcbros.uselessmod.energy;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.blocks.EnergyCableBlock;
import tk.themcbros.uselessmod.helper.EnergyUtils;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

import java.util.Map;
import java.util.Set;

public class EnergyCableNetwork implements IEnergyStorage {

	private final IBlockReader world;
	private final Map<BlockPos, Set<Connection>> connections = Maps.newHashMap();

	public EnergyCableNetwork(IBlockReader world) {
		this.world = world;
	}

	public boolean contains(IBlockReader world, BlockPos pos) {
		return this.world == world && this.connections.containsKey(pos);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energySent = 0;
		// Try to send energy to each connection
		for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
			BlockPos pos = entry.getKey();
			Set<Connection> connections = entry.getValue();
			for (Connection con : connections) {
				if (con.type.canReceive()) {
					IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(con.side), con.side.getOpposite());
					if (energy != null) {
						energySent += energy.receiveEnergy(maxReceive - energySent, simulate);
					}
				}
			}
		}
		return energySent;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energySent = 0;
		// Try to send energy to each connection
		for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
			BlockPos pos = entry.getKey();
			Set<Connection> connections = entry.getValue();
			for (Connection con : connections) {
				if (con.type.canExtract()) {
					IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(con.side), con.side.getOpposite());
					if (energy != null) {
						energySent += energy.extractEnergy(maxExtract - energySent, simulate);
					}
				}
			}
		}
		return energySent;
	}

	@Override
	public int getEnergyStored() {
		return 0;
	}

	@Override
	public int getMaxEnergyStored() {
		return 0;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	public static EnergyCableNetwork buildNetwork(IBlockReader world, BlockPos pos) {
		EnergyCableNetwork network = new EnergyCableNetwork(world);
		buildCableSet(world, pos, Sets.newHashSet()).forEach(p -> {
			Set<Connection> connections = Sets.newHashSet();
			for (Direction direction : Direction.values()) {
				TileEntity te = world.getTileEntity(p.offset(direction));
				if (te != null && !(te instanceof EnergyCableTileEntity) && te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).isPresent()) {
					ConnectionType type = EnergyCableBlock.getConnection(world.getBlockState(p), direction);
					connections.add(new Connection(type, direction));
				}
			}
			network.connections.put(p, connections);
		});
//		UselessMod.LOGGER.debug("EnergyCableNetwork has {} nodes", network.connections.size());
		return network;
	}

	private static Set<BlockPos> buildCableSet(IBlockReader world, BlockPos pos, Set<BlockPos> set) {
		set.add(pos);
		for (Direction side : Direction.values()) {
			BlockPos pos1 = pos.offset(side);
			ConnectionType type = EnergyCableBlock.getConnection(world.getBlockState(pos), side);
			if (!set.contains(pos1) && world.getTileEntity(pos1) instanceof EnergyCableTileEntity && type.canConnect()) {
				set.add(pos1);
				set.addAll(buildCableSet(world, pos1, set));
			}
		}
		return set;
	}

	@Override
	public String toString() {
		return String.format("EnergyCableNetwork %s (%d cables)", Integer.toHexString(hashCode()), connections.size());
	}

	public static class Connection {
		private final ConnectionType type;
		private final Direction side;

		public Connection(ConnectionType type, Direction side) {
			this.type = type;
			this.side = side;
		}
	}

}
