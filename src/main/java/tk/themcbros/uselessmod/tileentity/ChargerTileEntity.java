package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.container.ChargerContainer;
import tk.themcbros.uselessmod.helper.TextUtils;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.machine.Upgrade;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChargerTileEntity extends MachineTileEntity {

	private static final int BASE_RF_PER_TICK = 75;

	private int ticks = 0;

	private final IIntArray fields = new IIntArray() {
		@Override
		public int get(int i) {
			switch (i) {
				case 0:
					return ChargerTileEntity.this.energyStorage.getEnergyStored();
				case 1:
					return ChargerTileEntity.this.energyStorage.getMaxEnergyStored();
				default:
					return 0;
			}
		}

		@Override
		public void set(int i, int value) {
			switch (i) {
				case 0:
					ChargerTileEntity.this.energyStorage.setEnergyStored(value);
					break;
				case 1:
					ChargerTileEntity.this.energyStorage.setCapacity(value);
					break;
				default:
					break;
			}
		}

		@Override
		public int size() {
			return 2;
		}
	};

	public ChargerTileEntity() {
		super(ModTileEntities.CHARGER, MachineTier.STANDARD, false);
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return TextUtils.translate("container", "charger");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new ChargerContainer(id, player, this, this.upgradeInventory, this.fields);
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, @Nullable Direction direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, Direction direction) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		this.items.set(i, itemStack);
	}

	@Override
	public void tick() {
		this.receiveEnergyFromSlot(1);
		if (this.canRun()) {
			if (this.ticks != 20) this.ticks = 20;
			this.extractEnergyToSlot(0);
			this.sendUpdate(this.getActiveState(), true);
		} else {
			if(this.ticks >= 0) --this.ticks;
		}

		if (this.ticks <= 0) {
			this.sendUpdate(this.getInactiveState(), false);
		}
	}

	private void extractEnergyToSlot(int index) {
		final ItemStack energySlotStack = this.items.get(index);
		if(!energySlotStack.isEmpty()) {
			IEnergyStorage handler = energySlotStack.getCapability(CapabilityEnergy.ENERGY).orElse(null);
			if(handler == null) return;
			int accept = handler.receiveEnergy(Math.min(Math.max(this.energyStorage.getEnergyStored(), this.energyStorage.getMaxEnergyStored() - this.energyStorage.getEnergyStored()), this.getRfPerTick()), true);
			if(this.energyStorage.getEnergyStored() >= accept)
				this.energyStorage.modifyEnergyStored(-handler.receiveEnergy(accept, false));
		}
	}

	private boolean canRun() {
		ItemStack energyStack = this.items.get(0);
		return this.energyStorage.getEnergyStored() > 0 && !energyStack.isEmpty() &&
				energyStack.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::canReceive).orElse(false);
	}

	private int getRfPerTick() {
		int defaultUsage = BASE_RF_PER_TICK;
		float speed = 1.0f;
		for(ItemStack stack : this.upgradeInventory.getStacks()) {
			if(!stack.isEmpty() && stack.getItem() instanceof UpgradeItem) {
				if(((UpgradeItem) stack.getItem()).getUpgrade() == Upgrade.SPEED) {
					speed += (0.5f * stack.getCount());
				}
			}
		}
		defaultUsage = (int) (defaultUsage * speed);
		return defaultUsage;
	}

}
