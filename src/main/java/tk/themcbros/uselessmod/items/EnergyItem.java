package tk.themcbros.uselessmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class EnergyItem extends Item {

	public EnergyItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		CompoundNBT tagCompoundNBT = new CompoundNBT();
		CompoundNBT energyCompoundNBT = new CompoundNBT();
		energyCompoundNBT.putInt("Energy", 0);
		tagCompoundNBT.put("Energy", energyCompoundNBT);
		ItemStack stack = new ItemStack(this, 1, tagCompoundNBT);
		return stack;
	}

}
