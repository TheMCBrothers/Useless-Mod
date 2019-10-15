package tk.themcbros.uselessmod.fluids;

import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.LazyOptional;
import tk.themcbros.uselessmod.UselessMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class FluidPipeNetworkManager {

	private static final Collection<LazyOptional<FluidPipeNetwork>> NETWORK_LIST = Collections.synchronizedList(Lists.newArrayList());

	@Nullable
	public static FluidPipeNetwork get(IBlockReader world, BlockPos pos) {
		return getLazy(world, pos).orElse(null);
	}

	@Nonnull
	public static LazyOptional<FluidPipeNetwork> getLazy(IBlockReader world, BlockPos pos) {
		synchronized (NETWORK_LIST) {
			for (LazyOptional<FluidPipeNetwork> network : NETWORK_LIST) {
				if (network.isPresent()) {
					FluidPipeNetwork net = network.orElseThrow(IllegalStateException::new);
					if (net.contains(world, pos)) {
						return network;
					}
				}
			}
		}

		// Create new network
		FluidPipeNetwork network = FluidPipeNetwork.buildNetwork(world, pos);
		LazyOptional<FluidPipeNetwork> lazy = LazyOptional.of(() -> network);
		NETWORK_LIST.add(lazy);
		UselessMod.LOGGER.debug("Created network {}", network);
		return lazy;
	}

	public static void invalidateNetwork(IBlockReader world, BlockPos pos) {
		Collection<LazyOptional<FluidPipeNetwork>> toRemove = NETWORK_LIST.stream()
				.filter(n -> n != null && n.isPresent() && n.orElseThrow(IllegalStateException::new).contains(world, pos))
				.collect(Collectors.toList());
		toRemove.forEach(FluidPipeNetworkManager::invalidateNetwork);
	}

	private static void invalidateNetwork(LazyOptional<FluidPipeNetwork> network) {
		UselessMod.LOGGER.debug("Invalidate network {}", network);
		NETWORK_LIST.removeIf(n -> n.isPresent() && n.equals(network));
		network.invalidate();
	}

}
