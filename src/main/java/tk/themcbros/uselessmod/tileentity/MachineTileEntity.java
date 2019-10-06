package tk.themcbros.uselessmod.tileentity;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.machine.MachineUpgradeInventory;

public abstract class MachineTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory, IRecipeHolder {
	
	protected static final int DEFAULT_RF_PER_TICK = 15;
	
	protected CustomEnergyStorage energyStorage;
	protected MachineUpgradeInventory upgradeInventory;
	protected MachineTier machineTier = MachineTier.STANDARD;
	
	protected Map<ResourceLocation, Integer> recipeUsedMap = Maps.newHashMap();
	protected int recipesUsed;
	
	protected NonNullList<ItemStack> items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	
	public MachineTileEntity(TileEntityType<?> tileEntityTypeIn, MachineTier machineTier, boolean generator) {
		super(tileEntityTypeIn);
		this.machineTier = machineTier;
		this.energyStorage = new CustomEnergyStorage(machineTier.getMachineCapacity(), !generator ? machineTier.getMaxEnergyTransfer() : 0, generator ? machineTier.getMaxEnergyTransfer() : 0, 0);
		this.upgradeInventory = new MachineUpgradeInventory(this);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putString("Tier", this.machineTier != null ? this.machineTier.getName() : "error");
		compound.put("Energy", this.energyStorage.serializeNBT());
		this.upgradeInventory.write(compound);
		ItemStackHelper.saveAllItems(compound, items, false);
		
		compound.putShort("RecipesUsedSize", (short) this.recipeUsedMap.size());
		int i = 0;

		for (Entry<ResourceLocation, Integer> entry : this.recipeUsedMap.entrySet()) {
			compound.putString("RecipeLocation" + i, entry.getKey().toString());
			compound.putInt("RecipeAmount" + i, entry.getValue());
			++i;
		}
		
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.machineTier = MachineTier.byName(compound.getString("Tier"));
		this.energyStorage = CustomEnergyStorage.fromNBT(compound.getCompound("Energy"));
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, items);
		this.upgradeInventory = new MachineUpgradeInventory(this);
		this.upgradeInventory.read(compound);
		
//		this.recipesUsed = this.getBurnTime(this.items.get(1));
		int i = compound.getShort("RecipesUsedSize");

		for (int j = 0; j < i; ++j) {
			ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
			int k = compound.getInt("RecipeAmount" + j);
			this.recipeUsedMap.put(resourcelocation, k);
		}
	}
	
	protected void receiveEnergyFromSlot(int index) {
		final ItemStack energySlotStack = this.items.get(index);
		if(!energySlotStack.isEmpty()) {
			IEnergyStorage handler = energySlotStack.getCapability(CapabilityEnergy.ENERGY).orElse(null);
			if(handler == null) return;
			int accept = handler.extractEnergy(Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), this.machineTier.getMaxEnergyTransfer()), true);
			if(this.getEnergyStored() <= this.getMaxEnergyStored() - accept)
				this.energyStorage.modifyEnergyStored(handler.extractEnergy(accept, false));
		}
	}
	
	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.items.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
	}
	
	@Override
	public void clear() {
		this.items.clear();
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
	public int getInventoryStackLimit() {
		return 64;
	}
	
	protected final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(this::createEnergyHandler);
	
	private IEnergyStorage createEnergyHandler() {
		return this.energyStorage;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY) return energyHandler.cast();
		return super.getCapability(cap, side);
	}
	
	public int getEnergyStored() {
		return this.energyStorage.getEnergyStored();
	}
	
	public int getMaxEnergyStored() {
		return this.machineTier.getMachineCapacity();
	}
	
	@Override
	protected ITextComponent getDefaultName() {
		return this.getDisplayName();
	}
	
	@Override
	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		if (recipe != null) {
	         this.recipeUsedMap.compute(recipe.getId(), (location, integer) -> {
	            return 1 + (integer == null ? 0 : integer);
	         });
	      }
	}

	public void dropXP(PlayerEntity player) {
		UselessMod.LOGGER.debug("TODO: Drop XP method"); // TODO: Drop XP method
	}

	public MachineTier getMachineTier() {
		return this.machineTier;
	}

}
