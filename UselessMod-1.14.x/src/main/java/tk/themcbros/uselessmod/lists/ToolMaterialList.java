package tk.themcbros.uselessmod.lists;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMaterialList implements IItemTier {
	
	useless(10.0F, 9.0F, 25, 3, 750, ModItems.USELESS_INGOT),
	super_useless(12.0F, 10.0F, 30, 3, 1000, ModItems.SUPER_USELESS_INGOT);
	
	private float attackDamage, efficiency;
	private int enchantability, harvestLevel, maxUses;
	private Ingredient repairMaterial;
	
	private ToolMaterialList(float attackDamage, float efficiency, int enchantability, int harvestLevel, int maxUses, Item repairMaterial) {
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.enchantability = enchantability;
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.repairMaterial = Ingredient.fromItems(repairMaterial);
	}
	
	private ToolMaterialList(float attackDamage, float efficiency, int enchantability, int harvestLevel, int maxUses, ItemStack repairMaterial) {
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.enchantability = enchantability;
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.repairMaterial = Ingredient.fromStacks(repairMaterial);
	}
	
	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial;
	}

}
