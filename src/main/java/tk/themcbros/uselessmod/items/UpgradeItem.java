package tk.themcbros.uselessmod.items;

import net.minecraft.item.Item;
import tk.themcbros.uselessmod.machine.Upgrade;

public class UpgradeItem extends Item {
	
	private final Upgrade upgrade;

	public UpgradeItem(Properties properties, Upgrade upgrade) {
		super(properties);
		this.upgrade = upgrade;
	}
	
	public Upgrade getUpgrade() {
		return upgrade;
	}

}
