package themcbros.uselessmod.client.renderer.tilentity;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.tileentity.UselessChestTileEntity;

public class UselessChestTileEntityRenderer extends ChestTileEntityRenderer<UselessChestTileEntity> {

    private static final RenderMaterial CHEST_USELESS_MATERIAL;
    private static final RenderMaterial CHEST_USELESS_LEFT_MATERIAL;
    private static final RenderMaterial CHEST_USELESS_RIGHT_MATERIAL;

    public UselessChestTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected RenderMaterial getMaterial(UselessChestTileEntity chestTileEntity, ChestType chestType) {
        return getChestMaterial(chestType);
    }

    private static RenderMaterial getChestMaterial(String name) {
        return new RenderMaterial(Atlases.CHEST_ATLAS, UselessMod.rl("entity/chest/" + name));
    }

    private static RenderMaterial getChestMaterial(ChestType chestType) {
        switch(chestType) {
            case LEFT:
                return CHEST_USELESS_LEFT_MATERIAL;
            case RIGHT:
                return CHEST_USELESS_RIGHT_MATERIAL;
            case SINGLE:
            default:
                return CHEST_USELESS_MATERIAL;
        }
    }

    static {
        CHEST_USELESS_MATERIAL = getChestMaterial("useless_oak");
        CHEST_USELESS_LEFT_MATERIAL = getChestMaterial("useless_oak_left");
        CHEST_USELESS_RIGHT_MATERIAL = getChestMaterial("useless_oak_right");
    }

}
