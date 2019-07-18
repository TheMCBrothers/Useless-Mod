package tk.themcbros.uselessmod.tileentity;

import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.container.GlowstoneGeneratorContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class GlowstoneGeneratorTileEntity extends MachineTileEntity {

	private NonNullList<ItemStack> generatorStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	private int cookTime;
	
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 3;
		}
		
		@Override
		public void set(int id, int value) {
			switch (id) {
			case 0:
				GlowstoneGeneratorTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				GlowstoneGeneratorTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				GlowstoneGeneratorTileEntity.this.cookTime = value;
			}
		}
		
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return GlowstoneGeneratorTileEntity.this.getEnergyStored();
			case 1:
				return GlowstoneGeneratorTileEntity.this.getMaxEnergyStored();
			case 2:
				return GlowstoneGeneratorTileEntity.this.cookTime;
			default:
				return 0;
			}
		}
	};
	
	public GlowstoneGeneratorTileEntity() {
		super(ModTileEntities.GLOWSTONE_GENERATOR);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.glowstone_generator");
	}

	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return new GlowstoneGeneratorContainer(p_createMenu_1_, p_createMenu_2_, this, fields);
	}
	
	private void sendEnergy() {
		if(this.energyStorage.getEnergyStored() > 0) {
			for(Direction facing : Direction.values()) {
				TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(facing));
				if(tileEntity != null) {
					IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).orElse(null);
					if(handler != null && handler.canReceive()) {
						int accepted = handler.receiveEnergy(this.energyStorage.getEnergyStored(), false);
						this.energyStorage.modifyEnergyStored(-accepted);
						if(this.energyStorage.getEnergyStored() <= 0)
							break;
					}
				}
			}
			this.markDirty();
		}
	}
	
//	private void sendEnergy() {
//		energy.ifPresent(energy -> {
//			AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
//			if (capacity.get() > 0) {
//				for (Direction direction : Direction.values()) {
//					TileEntity te = this.world.getTileEntity(this.pos.offset(direction));
//					if (te != null) {
//						boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
//							if (handler.canExtract()) {
//								int received = handler.receiveEnergy(Math.min(capacity.get(), 100), false);
//								capacity.addAndGet(received);
//								energy.extractEnergy(received, false);
//								markDirty();
//								return capacity.get() > 0;
//							}
//							return true;
//						}).orElse(true);
//						if (!doContinue)
//							return;
//					}
//				}
//			}
//		});
//	}
	
	private boolean isActive() {
		return this.cookTime > 0;
	}

	@Override
	public void tick() {
		boolean flag = this.isActive();
		ItemStack input = generatorStacks.get(0);
		if(!generatorStacks.get(0).isEmpty() && isItemFuel(input) && getEnergyStored() <= getMaxEnergyStored() - getFuelValue(input)) {
			cookTime++;
			if(cookTime == 25) {
				energyStorage.modifyEnergyStored(getFuelValue(input));
				cookTime = 0;
				input.shrink(1);
			}
		}
		
		if(flag != isActive()) {
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, Boolean.valueOf(this.isActive())), 3);
		}
		
		sendEnergy();
	}
	
	public static boolean isItemFuel(ItemStack stack) {
		return getFuelValue(stack) > 0;
	}

	private static int getFuelValue(ItemStack stack) {
		if(stack.getItem() == Items.GLOWSTONE_DUST) return 1000;
		else if(stack.getItem() == Item.BLOCK_TO_ITEM.get(Blocks.GLOWSTONE)) return 4000;
		else return 0;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return null;
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return this.generatorStacks.size();
	}

	@Override
	public boolean isEmpty() {
		return this.generatorStacks.get(0).isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.generatorStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(generatorStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(generatorStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.generatorStacks.get(0);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.generatorStacks.set(0, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.cookTime = 0;
			this.markDirty();
		}

	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}

	@Override
	public void clear() {
		this.generatorStacks.clear();
	}

	@Override
	public CompoundNBT writeRestorableToNBT(CompoundNBT compound) {
		ItemStackHelper.saveAllItems(compound, generatorStacks);
		compound.putInt("CookTime", this.cookTime);
		return compound;
	}

	@Override
	public void readRestorableFromNBT(CompoundNBT compound) {
		generatorStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, generatorStacks);
		this.cookTime = compound.getInt("CookTime");
	}
	
	private LazyOptional<? extends IItemHandler>[] itemHandler = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.values());
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return itemHandler[0].cast();
		return super.getCapability(cap, side);
	}
	
	@Override
	public void remove() {
		for(int i = 0; i < itemHandler.length; i++)
			itemHandler[i].invalidate();
		super.remove();
	}

}
