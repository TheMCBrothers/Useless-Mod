package themcbros.uselessmod.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class ClientUtils {

    public static ModelRotation getRotation(Direction facing) {
        switch (facing) {
            case DOWN:  return ModelRotation.X90_Y0;
            case UP:    return ModelRotation.X270_Y0;
            case NORTH: return ModelRotation.X0_Y0;
            case SOUTH: return ModelRotation.X0_Y180;
            case WEST:  return ModelRotation.X0_Y270;
            case EAST:  return ModelRotation.X0_Y90;
        }
        throw new IllegalArgumentException(String.valueOf(facing));
    }

    public static ClientWorld getWorld() {
        return Minecraft.getInstance().world;
    }
}
