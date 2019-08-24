package tk.themcbros.uselessmod.machine;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import tk.themcbros.uselessmod.tileentity.MachineTileEntity;

public class MachineUpgradeInventory implements IInventory {
	
	private NonNullList<ItemStack> stacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
	private MachineTileEntity machine;
	
	public MachineUpgradeInventory(MachineTileEntity machine) {
		this.machine = machine;
	}
	
	public MachineTileEntity getMachine() {
		return machine;
	}
	
	public NonNullList<ItemStack> getStacks() {
		return stacks;
	}
	
	public CompoundNBT write(CompoundNBT compound) {
		ListNBT listnbt = new ListNBT();

		for (int i = 0; i < this.stacks.size(); ++i) {
			ItemStack itemstack = this.stacks.get(i);
			if (!itemstack.isEmpty()) {
				CompoundNBT compoundnbt = new CompoundNBT();
				compoundnbt.putByte("Slot", (byte) i);
				itemstack.write(compoundnbt);
				listnbt.add(compoundnbt);
			}
		}

		if (!listnbt.isEmpty()) {
			compound.put("Upgrades", listnbt);
		}

		return compound;
	}
	
	public void read(CompoundNBT compound) {
		this.stacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ListNBT listnbt = compound.getList("Upgrades", 10);

		for (int i = 0; i < listnbt.size(); ++i) {
			CompoundNBT compoundnbt = listnbt.getCompound(i);
			int j = compoundnbt.getByte("Slot") & 255;
			if (j >= 0 && j < this.stacks.size()) {
				this.stacks.set(j, ItemStack.read(compoundnbt));
			}
		}
	}
	
	@Override
	public void clear() {
		this.stacks.clear();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return ItemStackHelper.getAndSplit(stacks, arg0, arg1);
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.stacks.get(index);
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return this.machine.isUsableByPlayer(player);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.stacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.stacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.stacks.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if(!flag) machine.markDirty();
	}

	@Override
	public void markDirty() {
		this.machine.markDirty();
	}

}
