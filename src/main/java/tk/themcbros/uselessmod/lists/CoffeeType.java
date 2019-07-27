package tk.themcbros.uselessmod.lists;

import net.minecraft.potion.Potion;
import net.minecraft.util.IStringSerializable;

public enum CoffeeType implements IStringSerializable {

	BLACK(0, "black", ModPotions.BLACK_COFFEE, 0x44211A),
	MILK(1, "milk", ModPotions.MILK_COFFEE, 0x96493A),
	SUGAR(2, "sugar", ModPotions.SUGAR_COFFEE, 0x7F3E31),
	MILK_SUGAR(3, "milk_sugar", ModPotions.MILK_SUGAR_COFFEE, 0x99493B);
	
	private final int index;
	private final String name;
	private final Potion potion;
	private final int color;

	private CoffeeType(int index, String name, Potion potion, int color) {
		this.index = index;
		this.name = name;
		this.potion = potion;
		this.color = color;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public Potion getPotion() {
		return potion;
	}
	
	public int getColor() {
		return color;
	}
}
