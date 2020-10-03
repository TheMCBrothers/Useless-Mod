package themcbros.uselessmod.useless_mana;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.PacketDistributor;
import themcbros.uselessmod.network.Messages;
import themcbros.uselessmod.useless_mana.player.PlayerMana;
import themcbros.uselessmod.useless_mana.player.PlayerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TheMCBrothers
 */
public class WorldMana extends WorldSavedData {

    private static final String NAME = "UselessManaData";

    private Map<ChunkPos, ManaSphere> spheres = new HashMap<>();
    private int ticker = 10;

    public WorldMana(String name) {
        super(name);
    }

    public WorldMana() {
        this(NAME);
    }

    public static WorldMana get(ServerWorld world) {
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(WorldMana::new, NAME);
    }

    public float getManaInfluence(ServerWorld world, BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        float mana = 0.0F;
        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                ManaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        mana += (sphere.getRadius() - distance) / sphere.getRadius();
                    }
                }
            }
        }
        return mana;
    }

    public float getManaStrength(ServerWorld world, BlockPos pos) {
        float manaStrength = 0.0F;
        ChunkPos chunkPos = new ChunkPos(pos);
        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                ManaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double factor = (sphere.getRadius() - distance) / sphere.getRadius();
                        manaStrength += factor * sphere.getCurrentMana();
                    }
                }
            }
        }
        return manaStrength;
    }

    public float extractMana(ServerWorld world, BlockPos pos) {
        float manaStrength = getManaInfluence(world, pos);
        if (manaStrength <= 0)
            return 0;
        float extracted = 0.0F;
        ChunkPos chunkPos = new ChunkPos(pos);
        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                ManaSphere sphere = getOrCreateSphereAt(world, cp);
                if (sphere.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(sphere.getCenter());
                    if (distanceSq < sphere.getRadius() * sphere.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double factor = (sphere.getRadius() - distance) / sphere.getRadius();
                        float currentMana = sphere.getCurrentMana();
                        if (factor > currentMana) {
                            factor = currentMana;
                        }
                        currentMana -= factor;
                        extracted += factor;
                        sphere.setCurrentMana(currentMana);
                        markDirty();
                    }
                }
            }
        }
        return extracted;
    }

    public void tick(ServerWorld world) {
        ticker--;
        if (ticker > 0)
            return;
        ticker = 10;
        growMana(world);
        sendMana(world);
    }

    private void growMana(ServerWorld world) {
        for (Map.Entry<ChunkPos, ManaSphere> entry : spheres.entrySet()) {
            ManaSphere sphere = entry.getValue();
            if (sphere.getRadius() > 0) {
                if (world.isBlockLoaded(sphere.getCenter())) {
                    float currentMana = sphere.getCurrentMana();
                    currentMana += .01F;
                    if (currentMana > 5)
                        currentMana = 5;
                    sphere.setCurrentMana(currentMana);
                    markDirty();
                }
            }
        }
    }

    private void sendMana(ServerWorld world) {
        for (PlayerEntity player : world.getPlayers()) {
            float manaStrength = getManaStrength(world, player.getPosition());
            float maxInfluence = getManaInfluence(world, player.getPosition());
            PlayerMana playerMana = PlayerProperties.getPlayerMana(player);
            Messages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SendManaPacket(manaStrength, maxInfluence, playerMana.getMana()));
        }
    }

    private ManaSphere getOrCreateSphereAt(ServerWorld world, ChunkPos cp) {
        ManaSphere sphere = spheres.get(cp);
        if (sphere == null) {
            BlockPos center = cp.asBlockPos().add(8, ManaSphere.getRandomYOffset(world.getSeed(), cp.x, cp.z), 8);
            float radius = 0;
            if (ManaSphere.isCenterChunk(world.getSeed(), cp.x, cp.z)) {
                radius = ManaSphere.getRadius(world.getSeed(), cp.x, cp.z);
            }
            sphere = new ManaSphere(center, radius);
            spheres.put(cp, sphere);
            markDirty();
        }
        return  sphere;
    }

    @Override
    public void read(CompoundNBT nbt) {
        ListNBT list = nbt.getList("spheres", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT sphereNBT = list.getCompound(i);
            ChunkPos pos = new ChunkPos(sphereNBT.getInt("ChunkX"), sphereNBT.getInt("ChunkZ"));
            ManaSphere sphere = new ManaSphere(
                    new BlockPos(sphereNBT.getInt("PosX"), sphereNBT.getInt("PosY"), sphereNBT.getInt("PosZ")),
                    sphereNBT.getFloat("Radius")
            );
            sphere.setCurrentMana(sphereNBT.getInt("Mana"));
            spheres.put(pos, sphere);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (Map.Entry<ChunkPos, ManaSphere> entry : spheres.entrySet()) {
            CompoundNBT sphereNBT = new CompoundNBT();
            ChunkPos pos = entry.getKey();
            ManaSphere sphere = entry.getValue();
            sphereNBT.putInt("ChunkX", pos.x);
            sphereNBT.putInt("ChunkZ", pos.z);
            sphereNBT.putInt("PosX", sphere.getCenter().getX());
            sphereNBT.putInt("PosY", sphere.getCenter().getY());
            sphereNBT.putInt("PosZ", sphere.getCenter().getZ());
            sphereNBT.putFloat("Radius", sphere.getRadius());
            sphereNBT.putFloat("Mana", sphere.getCurrentMana());
            list.add(sphereNBT);
        }
        compound.put("spheres", list);
        return compound;
    }
}
