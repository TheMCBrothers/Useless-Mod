package tk.themcbros.uselessmod.items;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.caps.CapabilityProviderEnergy;
import tk.themcbros.uselessmod.caps.EnergyConversionStorage;
import tk.themcbros.uselessmod.energy.IEnergyContainerItem;
import tk.themcbros.uselessmod.helper.NBTHelper;
import tk.themcbros.uselessmod.helper.TextUtils;
import tk.themcbros.uselessmod.machine.MachineTier;

import javax.annotation.Nullable;
import java.util.List;

public class EnergyPickaxeItem extends PickaxeItem implements IEnergyContainerItem {

	public static final String TAG_ENERGY = "Energy";
	public static final int RF_PER_BREAK = 20;
	
	public EnergyPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder.maxDamage(0));
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			ItemStack itemStack = new ItemStack(this);
			NBTHelper.setInt(itemStack, TAG_ENERGY, this.getMaxEnergyStored(itemStack));
			items.add(itemStack);
		}
		super.fillItemGroup(group, items);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		ITextComponent component = TextUtils.energyWithMax(this.getEnergyStored(stack), this.getMaxEnergyStored(stack));
		tooltip.add(component.applyTextStyle(TextFormatting.GRAY));
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0xFFFF0000;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return this.hasEnoughEnergy(stack) ? super.getDestroySpeed(stack, state) : 1f;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = this.getAttributeModifiers(slot);
		if (slot == EquipmentSlotType.MAINHAND) {
			double attackDamage = this.hasEnoughEnergy(stack) ? (double) this.attackDamage : (double) this.attackDamage/2;
			double attackSpeed = this.hasEnoughEnergy(stack)  ? (double) this.attackSpeed : (double) this.attackSpeed/2;
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double stored = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack) + 1;
		double max = this.getMaxEnergyStored(stack) + 1;
		return stored / max;
	}
	
	/**
	 * @param container The energy item
	 * @return TRUE if the item has enough energy to break a block
	 */
	public boolean hasEnoughEnergy(ItemStack container) {
		return this.getEnergyStored(container) >= RF_PER_BREAK;
	}
	
	/**
	 * @param container The energy item with the energy storage.
	 * @return The IEnergyStorage from the container item.
	 */
	@Nullable
	public IEnergyStorage getIEnergyStorage(ItemStack container) {
		return container.getCapability(CapabilityEnergy.ENERGY).orElse(null);
	}
	
	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.UNCOMMON;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean canHarvestBlock(ItemStack stack, BlockState state) {
		return super.canHarvestBlock(stack, state) && this.hasEnoughEnergy(stack);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		this.extractEnergy(stack, RF_PER_BREAK, false);
		return true;
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean isDamageable() {
		return false;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if(!worldIn.isRemote && state.getBlockHardness(worldIn, pos) > 0.0f) {
			this.extractEnergy(stack, RF_PER_BREAK, false);
		}
		return true;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, RF_PER_BREAK));
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, RF_PER_BREAK));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, TAG_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return MachineTier.COFFEE.getMachineCapacity();
	}

}
