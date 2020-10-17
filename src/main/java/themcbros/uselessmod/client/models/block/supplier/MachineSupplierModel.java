package themcbros.uselessmod.client.models.block.supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.IResourceManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import themcbros.uselessmod.tileentity.MachineSupplierTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class MachineSupplierModel implements IDynamicBakedModel {
    private static final ItemOverrideList OVERRIDE = new MachineSupplierModelOverride();

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
        BlockState mimic = Blocks.COBBLESTONE.getDefaultState();

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
        return OVERRIDE;
    }

    private static class Geometry implements IModelGeometry<Geometry> {
        @Override
        public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
            return new MachineSupplierModel();
        }

        @Override
        public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            return Collections.emptyList();
        }
    }

    public static class Loader implements IModelLoader<Geometry> {
        public static final Loader INSTANCE = new Loader();

        private Loader() {}

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {
        }

        @Override
        public Geometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            return new Geometry();
        }
    }

}
