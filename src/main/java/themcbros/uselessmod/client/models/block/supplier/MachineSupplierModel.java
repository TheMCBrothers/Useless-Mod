package themcbros.uselessmod.client.models.block.supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import themcbros.uselessmod.tileentity.MachineSupplierTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MachineSupplierModel implements IDynamicBakedModel {
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        RenderType layer = MinecraftForgeClient.getRenderLayer();

        BlockState mimic = extraData.getData(MachineSupplierTileEntity.MIMIC);
        if (mimic == null) mimic = Blocks.COBBLESTONE.getDefaultState();
        if (layer == null || RenderTypeLookup.canRenderInLayer(mimic, layer)) {
            IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(mimic);
            return model.getQuads(mimic, side, rand, EmptyModelData.INSTANCE);
        }
        return Collections.emptyList();
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        BlockState mimic = data.getData(MachineSupplierTileEntity.MIMIC);
        if (mimic != null) {
            IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(mimic);
            return model.getParticleTexture(EmptyModelData.INSTANCE);
        }
        return this.getParticleTexture();
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {
        BlockState mimic = null;

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MachineSupplierTileEntity)
            mimic = ((MachineSupplierTileEntity) tileEntity).getMimic();

        tileData.setData(MachineSupplierTileEntity.MIMIC, mimic);
        return tileData;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE)
                .apply(new ResourceLocation("block/cobblestone"));
    }

    @Override
    public ItemOverrideList getOverrides() {
        return MachineSupplierModelOverride.INSTANCE;
    }
}
