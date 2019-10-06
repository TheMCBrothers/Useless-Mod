package tk.themcbros.uselessmod.closet;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.UselessMod;

public interface IClosetMaterial {

	/**
	 * Texture location that for material, eg 'minecraft:block/white_wool'
	 */
	public String getTexture();

	/**
	 * The translation key using for the tooltip
	 */
	public ITextComponent getTooltip();

	/**
	 * The string saved to the item NBT and tileentity NBT, used for material
	 * lookup. This should be unique and is usually a block registry name, e.g
	 * 'minecraft:white_wool'
	 */
	public String getSaveId();

	/**
	 * The ingredient used in the crafting recipe of the closet
	 */
	default Ingredient getIngredient() {
		return Ingredient.EMPTY;
	}
	
	/**
	 * Receives the registry name, in default implementation is used
	 */
	default IClosetMaterial setRegName(String regName) {
		return this;
	}
	
	public static IClosetMaterial NULL = new IClosetMaterial() {
		
		@Override
		public ITextComponent getTooltip() {
			return new TranslationTextComponent("closet.null");
		}
		
		@Override
		public String getTexture() {
			return UselessMod.MOD_ID + ":missing_closet";
		}
		
		@Override
		public String getSaveId() {
			return "missing";
		}
	};
	
	public static IClosetMaterial getHolder(String id) {
		return new IClosetMaterial() {
			@Override
			public String getTexture() {
				return UselessMod.MOD_ID + ":missing_closet";
			}

			@Override
			public ITextComponent getTooltip() {
				return new TranslationTextComponent("closet.uselessmod.null");
			}

			@Override
			public String getSaveId() {
				return id;
			}
		};
	}

}
