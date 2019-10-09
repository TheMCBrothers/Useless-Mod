package tk.themcbros.uselessmod.machine;

import com.google.common.collect.Maps;
import net.minecraft.util.Direction;
import tk.themcbros.uselessmod.energy.ConnectionType;

import java.util.Map;

/**
 * @author TheMCLoveMan
 */
public class SideConfig {

	// todo

	private final Map<Direction, ConnectionType> sides = Maps.newHashMap();

	/**
	 * @param side The side from the machine
	 * @return Type of configuration
	 */
	public ConnectionType getConnection(Direction side) {
		return sides.containsKey(side) ? sides.get(side) : ConnectionType.NONE;
	}

}
