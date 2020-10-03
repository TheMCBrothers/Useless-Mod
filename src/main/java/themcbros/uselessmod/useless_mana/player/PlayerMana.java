package themcbros.uselessmod.useless_mana.player;

import net.minecraft.nbt.CompoundNBT;

/**
 * @author TheMCBrothers
 */
public class PlayerMana {

    private float mana = 0.0F;

    public PlayerMana() {
    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }


    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void write(CompoundNBT compound) {
        compound.putFloat("Mana", this.mana);
    }

    public void read(CompoundNBT tag) {
        this.mana = tag.getFloat("Mana");
    }

}
