package tk.themcbros.uselessmod.fluids;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.blocks.EnergyCableBlock;
import tk.themcbros.uselessmod.blocks.FluidPipeBlock;
import tk.themcbros.uselessmod.energy.ConnectionType;
import tk.themcbros.uselessmod.energy.EnergyCableNetwork;
import tk.themcbros.uselessmod.helper.EnergyUtils;
import tk.themcbros.uselessmod.helper.FluidUtils;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class FluidPipeNetwork implements IFluidHandler {

	private final IBlockReader world;
	private final Map<BlockPos, Set<Connection>> connections = Maps.newHashMap();

	public FluidPipeNetwork(IBlockReader world) {
		this.world = world;
	}

	public boolean contains(IBlockReader world, BlockPos pos) {
		return this.world == world && this.connections.containsKey(pos);
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Nonnull
	@Override
	public FluidStack getFluidInTank(int tank) {
		return FluidStack.EMPTY;
	}

	@Override
	public int getTankCapacity(int tank) {
		return 0;
	}

	@Override
	public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
		return true;
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		int fluidSent = 0;
		// Try to send fluid to each connection
		for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
			BlockPos pos = entry.getKey();
			Set<Connection> connections = entry.getValue();
			for (Connection con : connections) {
				if (con.type.canReceive()) {
					IFluidHandler fluid = FluidUtils.getFluid(world, pos.offset(con.side), con.side.getOpposite());
					if (fluid != null) {
						FluidStack stack = new FluidStack(resource, resource.getAmount() - fluidSent);
						fluidSent += fluid.fill(stack, action);
					}
				}
			}
		}
		return fluidSent;
	}

	@Nonnull
	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		return drain(resource.getAmount(), action);
	}

	@Nonnull
	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		Fluid returnFluid = Fluids.EMPTY;
		int fluidSent = 0;
		// Try to send fluid to each connection
		for (Map.Entry<BlockPos, Set<Connection>> entry : connections.entrySet()) {
			BlockPos pos = entry.getKey();
			Set<Connection> connections = entry.getValue();
			for (Connection con : connections) {
				if (con.type.canReceive()) {
					IFluidHandler fluid = FluidUtils.getFluid(world, pos.offset(con.side), con.side.getOpposite());
					if (fluid != null) {
						FluidStack fluidStack = fluid.drain(maxDrain, FluidAction.SIMULATE);
						if (returnFluid == Fluids.EMPTY)
							returnFluid = fluidStack.getFluid();
						if (returnFluid.equals(fluidStack.getFluid()))
							fluidSent += fluid.drain(maxDrain, action).getAmount();
					}
				}
			}
		}
		return new FluidStack(returnFluid, fluidSent);
	}

	public static FluidPipeNetwork buildNetwork(IBlockReader world, BlockPos pos) {
		FluidPipeNetwork network = new FluidPipeNetwork(world);
		buildCableSet(world, pos, Sets.newHashSet()).forEach(p -> {
			Set<Connection> connections = Sets.newHashSet();
			for (Direction direction : Direction.values()) {
				TileEntity te = world.getTileEntity(p.offset(direction));
				if (te != null && !(te instanceof EnergyCableTileEntity) && te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction.getOpposite()).isPresent()) {
					ConnectionType type = FluidPipeBlock.getConnection(world.getBlockState(p), direction);
					connections.add(new Connection(type, direction));
				}
			}
			network.connections.put(p, connections);
		});
//		UselessMod.LOGGER.debug("FluidPipeNetwork has {} nodes", network.connections.size());
		return network;
	}

	private static Set<BlockPos> buildCableSet(IBlockReader world, BlockPos pos, Set<BlockPos> set) {
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

	public static class Connection {
		private final ConnectionType type;
		private final Direction side;

		public Connection(ConnectionType type, Direction side) {
			this.type = type;
			this.side = side;
		}
	}

}
