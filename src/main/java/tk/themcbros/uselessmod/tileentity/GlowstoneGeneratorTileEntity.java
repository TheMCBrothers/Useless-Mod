package tk.themcbros.uselessmod.tileentity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.container.GlowstoneGeneratorContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;

public class GlowstoneGeneratorTileEntity extends MachineTileEntity {

	private int cookTime;
	
	private static final int[] SLOTS_IN = new int[] { 0 };
	
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
		super(ModTileEntities.GLOWSTONE_GENERATOR, MachineTier.STANDARD, true);
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
		ItemStack input = items.get(0);
		if(!items.get(0).isEmpty() && isItemFuel(input) && getEnergyStored() <= getMaxEnergyStored() - getFuelValue(input)) {
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
		return side == Direction.DOWN ? new int[] {} : SLOTS_IN;
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
		return 1;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(0);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(0, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.cookTime = 0;
			this.markDirty();
		}

	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("CookTime", this.cookTime);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.cookTime = compound.getInt("CookTime");
	}
	
	private LazyOptional<? extends IItemHandler>[] itemHandler = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (!this.removed && side != null
				&& cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (side == Direction.UP)
				return itemHandler[0].cast();
			else if (side == Direction.DOWN)
				return itemHandler[1].cast();
			else
				return itemHandler[2].cast();
		}
		return super.getCapability(cap, side);
	}
	
	@Override
	public void remove() {
		for(int i = 0; i < itemHandler.length; i++)
			itemHandler[i].invalidate();
		super.remove();
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new GlowstoneGeneratorContainer(id, player);
	}

}
