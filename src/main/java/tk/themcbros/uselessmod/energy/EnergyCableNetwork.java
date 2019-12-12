package tk.themcbros.uselessmod.energy;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.blocks.EnergyCableBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.helper.EnergyUtils;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class EnergyCableNetwork implements IEnergyStorage {

	public static final int TRANSFER_PER_CONNECTION = MachineConfig.energy_cable_capacity.get();

	private final IWorldReader world;
	private final Map<BlockPos, Set<Connection>> connections = Maps.newHashMap();
	private boolean connectionsBuilt;
	private int energyStored;

	public EnergyCableNetwork(IWorldReader world, Set<BlockPos> cables, int energyStored) {
		this.world = world;
		cables.forEach(pos -> connections.put(pos, Collections.emptySet()));
		this.energyStored = energyStored;
	}

	public boolean contains(IWorldReader world, BlockPos pos) {
		return this.world == world && this.connections.containsKey(pos);
	}

	public int getCableCount() {
		return this.connections.size();
	}

	public Connection getConnection(BlockPos pos, Direction side) {
		if (connections.containsKey(pos)) {
			for (Connection connection : connections.get(pos)) {
				if (connection.side == side) {
					return connection;
				}
			}
		}
		return new Connection(this, side, ConnectionType.NONE);
	}

	private void updateCableEnergy() {
		int energyPerCable = energyStored / getCableCount();
		connections.keySet().forEach(p -> {
			TileEntity tileEntity = world.getTileEntity(p);
			if (tileEntity instanceof EnergyCableTileEntity) {
				((EnergyCableTileEntity) tileEntity).energyStored = energyPerCable;
			}
		});
	}

	void invalidate() {
		connections.values().forEach(set -> set.forEach(con -> con.getLazyOptional().invalidate()));
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		buildConnections();
		int received = Math.min(getMaxEnergyStored() - energyStored, Math.min(maxReceive, TRANSFER_PER_CONNECTION));
		if (received > 0) {
			if (!simulate) {
				energyStored += received;
				updateCableEnergy();
			}
		}
		return received;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		buildConnections();
		int extracted = Math.min(energyStored, Math.min(maxExtract, TRANSFER_PER_CONNECTION));
		if (extracted > 0) {
			if (!simulate) {
				energyStored -= extracted;
				updateCableEnergy();
			}
		}
		return extracted;
	}

	void sendEnergy() {
		buildConnections();

		// Send stored energy to connected blocks
		for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
			BlockPos pos = entry.getKey();
			Set<Connection> connections = entry.getValue();
			for (Connection con : connections) {
				if (con.type.canExtract()) {
					IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(con.side), con.side.getOpposite());
					if (energy != null && energy.canReceive()) {
						int toSend = extractEnergy(TRANSFER_PER_CONNECTION, true);
						int accepted = energy.receiveEnergy(toSend, false);
						extractEnergy(accepted, false);
					}
				}
			}
		}
	}


	@Override
	public int getEnergyStored() {
		return energyStored;
	}

	@Override
	public int getMaxEnergyStored() {
		return TRANSFER_PER_CONNECTION * connections.size();
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	static EnergyCableNetwork buildNetwork(IWorldReader world, BlockPos pos) {
		Set<BlockPos> cables = buildCableSet(world, pos);
		int energyStored = cables.stream().mapToInt(p -> {
			TileEntity tileEntity = world.getTileEntity(p);
			return tileEntity instanceof EnergyCableTileEntity ? ((EnergyCableTileEntity) tileEntity).energyStored : 0;
		}).sum();
		return new EnergyCableNetwork(world, cables, energyStored);
	}

	private static Set<BlockPos> buildCableSet(IWorldReader world, BlockPos pos) {
		return buildCableSet(world, pos, Sets.newHashSet());
	}

	private static Set<BlockPos> buildCableSet(IWorldReader world, BlockPos pos, Set<BlockPos> set) {
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

	private void buildConnections() {
		// Determine all connections. This will be done once the connections are actually needed.
		if (!connectionsBuilt) {
			connections.keySet().forEach(p -> connections.put(p, getConnections(world, p)));
			connectionsBuilt = true;
		}
	}

	private Set<Connection> getConnections(IBlockReader world, BlockPos pos) {
		// Get all connections for the wire at pos
		Set<Connection> connections = Sets.newHashSet();
		for (Direction direction : Direction.values()) {
			TileEntity te = world.getTileEntity(pos.offset(direction));
			if (te != null && !(te instanceof EnergyCableTileEntity) && te.getCapability(CapabilityEnergy.ENERGY).isPresent()) {
				ConnectionType type = EnergyCableBlock.getConnection(world.getBlockState(pos), direction);
				connections.add(new Connection(this, direction, type));
			}
		}
		return connections;
	}

	@Override
	public String toString() {
		return String.format("EnergyCableNetwork %s, %d cables, %,d FE", Integer.toHexString(hashCode()), connections.size(), energyStored);
	}

	public static class Connection implements IEnergyStorage {
		private final EnergyCableNetwork network;
		private final Direction side;
		private final ConnectionType type;
		private final LazyOptional<Connection> lazyOptional;

		Connection(EnergyCableNetwork network, Direction side, ConnectionType type) {
			this.network = network;
			this.side = side;
			this.type = type;
			this.lazyOptional = LazyOptional.of(() -> this);
		}

		public LazyOptional<Connection> getLazyOptional() {
			return lazyOptional;
		}

		@Override
		public int receiveEnergy(int maxReceive, boolean simulate) {
			if (!canReceive()) {
				return 0;
			}
			return network.receiveEnergy(maxReceive, simulate);
		}

		@Override
		public int extractEnergy(int maxExtract, boolean simulate) {
			if (!canExtract()) {
				return 0;
			}
			return network.extractEnergy(maxExtract, simulate);
		}

		@Override
		public int getEnergyStored() {
			return network.energyStored;
		}

		@Override
		public int getMaxEnergyStored() {
			return network.getMaxEnergyStored();
		}

		@Override
		public boolean canExtract() {
			return type.canExtract();
		}

		@Override
		public boolean canReceive() {
			return type.canReceive();
		}
	}

}
