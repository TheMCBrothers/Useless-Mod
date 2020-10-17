package themcbros.uselessmod.client.models.block.wall_closet;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.IResourceManager;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;
import themcbros.uselessmod.api.wall_closet.ClosetMaterialInit;
import themcbros.uselessmod.tileentity.WallClosetTileEntity;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

@SuppressWarnings("deprecation")
public class WallClosetModel implements IDynamicBakedModel {

    private static final ItemOverrideList OVERRIDE = new WallClosetItemOverride();

    private final ModelBakery modelBakery;
    private final BlockModel model;
    private final IBakedModel bakedModel;
    private final IModelTransform modelTransform;

    private final Map<String, IBakedModel> cache = Maps.newHashMap();

    public WallClosetModel(ModelBakery modelBakery, BlockModel model, IModelTransform modelTransform, Function<RenderMaterial, TextureAtlasSprite> spriteGetter) {
        this.modelBakery = modelBakery;
        this.model = model;
        this.bakedModel = model.bakeModel(modelBakery, model, spriteGetter, modelTransform, UselessMod.rl("closet"), true);
        this.modelTransform = modelTransform;
    }

    public IBakedModel getCustomModel(ClosetMaterial closetMaterial, Direction facing) {
        IBakedModel customModel;

        String key = Objects.requireNonNull(closetMaterial.getRegistryName()).toString() + ";" + facing.getName2();

        IBakedModel possibleModel = this.cache.get(key);

        if (possibleModel != null) {
            customModel = possibleModel;
        } else {
            List<BlockPart> elements = Lists.newArrayList();
            for (BlockPart part : this.model.getElements()) {
                elements.add(new BlockPart(part.positionFrom, part.positionTo, Maps.newHashMap(part.mapFaces),
                        part.partRotation, part.shade));
            }

            BlockModel newModel = new BlockModel(this.model.getParentLocation(), elements,
                    Maps.newHashMap(this.model.textures), this.model.isAmbientOcclusion(), this.model.getGuiLight(),
                    this.model.getAllTransforms(), Lists.newArrayList(this.model.getOverrides()));
            newModel.name = this.model.name;
            newModel.parent = this.model.parent;

            RenderMaterial material = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, closetMaterial.getTextureLocation());
            newModel.textures.put("planks", Either.left(material));
            newModel.textures.put("particle", Either.left(material));

            ResourceLocation loc = UselessMod.rl("closet_overriding");
            customModel = newModel.bakeModel(this.modelBakery, newModel, ModelLoader.defaultTextureGetter(), this.modelTransform, loc, true);

            this.cache.put(key, customModel);
        }

        return customModel;
    }

    @Override
    public IModelData getModelData(IBlockDisplayReader world, BlockPos pos, BlockState state, IModelData tileData) {
        ClosetMaterial material = ClosetMaterialInit.OAK_PLANKS.get();
        Direction facing = Direction.NORTH;

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof WallClosetTileEntity) {
            material = ((WallClosetTileEntity) tileEntity).getMaterial();
        }
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            facing = state.get(BlockStateProperties.HORIZONTAL_FACING);
        }

        tileData.setData(WallClosetTileEntity.CLOSET_MATERIAL, material);
        tileData.setData(WallClosetTileEntity.FACING, facing);
        return tileData;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return this.getCustomModel(ClosetMaterialInit.OAK_PLANKS.get(), Direction.NORTH).getQuads(state, side, rand);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand, IModelData extraData) {
        ClosetMaterial material = getMaterial(extraData.getData(WallClosetTileEntity.CLOSET_MATERIAL));
        Direction facing = getFacing(extraData.getData(WallClosetTileEntity.FACING));
        return this.getCustomModel(material, facing).getQuads(state, side, rand);
    }

    private ClosetMaterial getMaterial(@Nullable ClosetMaterial material) {
        if (material == null) {
            material = ClosetMaterialInit.OAK_PLANKS.get();
        }
        return material;
    }

    private Direction getFacing(@Nullable Direction facing) {
        if (facing == null) {
            facing = Direction.NORTH;
        }
        return facing;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return this.bakedModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.bakedModel.isGui3d();
    }

    @Override
    public boolean isSideLit() {
        return this.bakedModel.isSideLit();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return this.bakedModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return this.bakedModel.getParticleTexture();
    }

    @Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack mat) {
        return this.bakedModel.handlePerspective(cameraTransformType, mat);
    }

    @Override
    public ItemOverrideList getOverrides() {
        return OVERRIDE;
    }

    @Override
    public TextureAtlasSprite getParticleTexture(IModelData data) {
        ClosetMaterial material = getMaterial(data.getData(WallClosetTileEntity.CLOSET_MATERIAL));
        Direction facing = getFacing(data.getData(WallClosetTileEntity.FACING));
        return this.getCustomModel(material, facing).getParticleTexture();
    }

    public static class Geometry implements IModelGeometry<Geometry> {
        @Override
        public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
            BlockModel ownerModel = (BlockModel) owner.getOwnerModel();
            if (ownerModel == null)
                throw new RuntimeException("Wall Closet owner model is null");
            BlockModel blockModel = ownerModel.parent;
            if (blockModel == null)
                throw new RuntimeException("Wall Closet parent model is null");
            return new WallClosetModel(bakery, blockModel, modelTransform, spriteGetter);
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
            if (!modelContents.has("parent"))
                throw new RuntimeException("Wall Closet model requires 'parent' value.");
            return new Geometry();
        }
    }

}
