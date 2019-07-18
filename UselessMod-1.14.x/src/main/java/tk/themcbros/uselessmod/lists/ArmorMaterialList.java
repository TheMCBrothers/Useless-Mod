package tk.themcbros.uselessmod.lists;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import tk.themcbros.uselessmod.UselessMod;

public enum ArmorMaterialList implements IArmorMaterial {
	
	USELESS("useless", 250, 25, new int[] {8,10,9,7}, ModItems.USELESS_INGOT, "entity.ender_dragon.growl", 1f),
	SUPER_USELESS("super_useless", 500, 25, new int[] {8,10,9,7}, ModItems.SUPER_USELESS_INGOT, "entity.ender_dragon.growl", 2f);
	
	private static final int[] max_damage_array = new int[] { 13, 15, 16, 11 };
	private String name, equipSound;
	private int durability, enchantability;
	private int[] damageReductionAmount;
	private Item repairItem;
	private float toughness;
	
	private ArmorMaterialList(String name, int durability, int enchantability, int[] damageReductionAmount, Item repairItem, String equipSound, float toughness) {
		this.name = name;
		this.durability = durability;
		this.enchantability = enchantability;
		this.damageReductionAmount = damageReductionAmount;
		this.repairItem = repairItem;
		this.equipSound = equipSound;
		this.toughness = toughness;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) {
		return this.damageReductionAmount[slot.getIndex()];
	}

	@Override
	public int getDurability(EquipmentSlotType slot) {
		return max_damage_array[slot.getIndex()] * this.durability;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public String getName() {
		return UselessMod.MOD_ID + ":" + this.name;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairItem);
	}

	@Override
	public SoundEvent getSoundEvent() {
		return new SoundEvent(new ResourceLocation(equipSound));
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

}
