package tk.themcbros.uselessmod.tileentity;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
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
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.machine.MachineUpgradeInventory;

public abstract class MachineTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory, IRecipeHolder {
	
	protected CustomEnergyStorage energyStorage;
	protected MachineUpgradeInventory upgradeInventory;
	
	protected Map<ResourceLocation, Integer> recipeUsedMap = Maps.newHashMap();
	protected int recipesUsed;
	
	protected NonNullList<ItemStack> items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	
	public MachineTileEntity(TileEntityType<?> tileEntityTypeIn, int capacity, int value, boolean generator) {
		super(tileEntityTypeIn);
		this.energyStorage = new CustomEnergyStorage(16000, !generator ? value : 0, generator ? value : 0, 0);
		this.upgradeInventory = new MachineUpgradeInventory(this);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.put("Energy", this.energyStorage.serializeNBT());
		this.upgradeInventory.write(compound);
		
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
		this.energyStorage = CustomEnergyStorage.fromNBT(compound.getCompound("Energy"));
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
		return this.energyStorage.getMaxEnergyStored();
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
		
	}

}
