package tk.themcbros.uselessmod.machine;

import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;
import tk.themcbros.uselessmod.energy.ConnectionType;

import java.util.Map;

/**
 * @author TheMCLoveMan
 */
public class SideConfig implements INBTSerializable<ListNBT> {

	private final Map<Direction, ConnectionType> sides = Maps.newHashMap();

	/**
	 * @param side The side from the machine
	 * @return Type of configuration
	 */
	public ConnectionType getConnection(Direction side) {
		return sides.getOrDefault(side, ConnectionType.NONE);
	}

	public void setConnectionType(Direction side, ConnectionType type) {
		this.sides.put(side, type);
	}

	@Override
	public ListNBT serializeNBT() {
		ListNBT sideList = new ListNBT();
		this.sides.forEach((side, type) -> {
			if (type == ConnectionType.NONE) return;
			CompoundNBT tag = new CompoundNBT();
			tag.putString("Side", side.getName2());
			tag.putString("Type", type.getName());
			sideList.add(tag);
		});
		return sideList;
	}

	@Override
	public void deserializeNBT(ListNBT sideList) {
		sideList.forEach(nbt -> {
			if (!(nbt instanceof CompoundNBT)) return;
			CompoundNBT tag = (CompoundNBT) nbt;
			Direction side = Direction.byName(tag.getString("Side"));
			ConnectionType type = ConnectionType.byName(tag.getString("Type"));
			SideConfig.this.setConnectionType(side, type);
		});
	}

}
