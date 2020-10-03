package themcbros.uselessmod.api.coffee;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import themcbros.uselessmod.api.UselessRegistries;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author TheMCBrothers
 */
public class CoffeeType extends ForgeRegistryEntry<CoffeeType> {

    private boolean curePotionEffects = false;
    private boolean glint = false;

    private final int color;
    private final Set<Effect> effects;
    private final Ingredient ingredient;

    /**
     * @param color Color displayed in cup
     * @param ingredient Ingredient for making coffee in machine
     * @param effects Effects that will apply to player when drinking
     */
    public CoffeeType(int color, Ingredient ingredient, Set<Effect> effects) {
        this.color = color;
        this.effects = effects;
        this.ingredient = ingredient;
    }

    /**
     * Makes the cup item glint
     * @return Type
     */
    public CoffeeType glint() {
        this.glint = true;
        return this;
    }

    /**
     * !! NOT WORKING !!
     * makes the coffee cure the potion effects
     * @return Type
     */
    public CoffeeType curesPotionsEffects() {
        this.curePotionEffects = true;
        return this;
    }

    /**
     * @return Color displayed in cup
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @return effects applied to player
     */
    public Set<Effect> getEffects() {
        return this.effects;
    }

    /**
     * @return TRUE if the item glints
     */
    public boolean hasGlint() {
        return this.glint;
    }

    /**
     * @return TRUE when coffee cures potion effects
     */
    public boolean doesCurePotionEffects() {
        return this.curePotionEffects;
    }

    /**
     * @return The ingredient for the recipe
     */
    public Ingredient getExtraIngredient() {
        return this.ingredient;
    }

    /**
     * @param id String ID. (example: "uselessmod:milk")
     * @return Coffee Type. If it not exists -> null
     */
    @Nullable
    public static CoffeeType byId(String id) {
        return UselessRegistries.COFFEE_TYPES.getValue(ResourceLocation.tryCreate(id));
    }
}
