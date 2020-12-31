package themcbros.uselessmod.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.wires.Connection;
import blusunrize.immersiveengineering.api.wires.WireType;
import blusunrize.immersiveengineering.api.wires.localhandlers.EnergyTransferHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import themcbros.uselessmod.UselessMod;

import javax.annotation.Nonnull;

public class UselessWireType extends WireType implements EnergyTransferHandler.IEnergyWire {
    UselessWireType() {
    }

    @Override
    public String getUniqueName() {
        return UselessMod.MOD_ID + "_useless_wire";
    }

    @Override
    public int getColour(Connection connection) {
        return 0x66DA66;
    }

    @Override
    public double getSlack() {
        return 1.02d;
    }

    @Override
    public TextureAtlasSprite getIcon(Connection connection) {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE)
                .apply(ResourceLocation.tryCreate("uselessmod:block/useless_block"));
    }

    @Override
    public int getMaxLength() {
        return 32;
    }

    @Override
    public ItemStack getWireCoil(Connection connection) {
        return new ItemStack(ImmersiveCompat.USELESS_WIRE_COIL);
    }

    @Override
    public double getRenderDiameter() {
        return 0.07d;
    }

    @Nonnull
    @Override
    public String getCategory() {
        return WireType.HV_CATEGORY;
    }

    @Override
    public int getTransferRate() {
        return 32768;
    }

    @Override
    public double getBasicLossRate(Connection connection) {
        double length = Math.sqrt(connection.getEndA().getPosition()
                .distanceSq(Vector3d.copy(connection.getEndB().getPosition()), false));
        return 0.5d * length / (double)this.getMaxLength();
    }

    @Override
    public double getLossRate(Connection connection, int i) {
        return 0.0d;
    }
}
