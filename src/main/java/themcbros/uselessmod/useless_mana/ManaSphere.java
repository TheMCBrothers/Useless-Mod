package themcbros.uselessmod.useless_mana;

import net.minecraft.util.math.BlockPos;

import java.util.Random;

/**
 * @author TheMCBrothers
 */
public class ManaSphere {

    private final BlockPos center;
    private final float radius;

    private float currentMana;

    public ManaSphere(BlockPos center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public float getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(float currentMana) {
        this.currentMana = currentMana;
    }

    public BlockPos getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public static boolean isCenterChunk(long seed, int chunkX, int chunkZ) {
        Random random = new Random(seed + chunkX * 1766557063L + chunkZ * 21766558031L);
        return random.nextFloat() < .03F;
    }

    public static float getRadius(long seed, int chunkX, int chunkZ) {
        Random random = new Random(seed + chunkX * 289567469393L + chunkZ * 8746257131L);
        return random.nextFloat() * 40 + 20;
    }

    public static int getRandomYOffset(long seed, int chunkX, int chunkZ) {
        Random random = new Random(seed + chunkX * 34260932401L + chunkZ * 289458249961L);
        return random.nextInt(60) + 40;
    }

}
