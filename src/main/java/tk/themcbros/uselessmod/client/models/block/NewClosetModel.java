package tk.themcbros.uselessmod.client.models.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Either;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IModelData;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.helper.ClientUtils;
import tk.themcbros.uselessmod.tileentity.ClosetTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NewClosetModel implements IBakedModel {

    private final ItemOverrideList ITEM_OVERRIDE = new ClosetItemOverride();

    private ModelLoader modelLoader;
    private BlockModel model;
    private IBakedModel bakedModel;

    private final Map<String, IBakedModel> cache = Maps.newHashMap();

    public NewClosetModel(ModelLoader modelLoader, BlockModel model, IBakedModel bakedModel) {
        this.modelLoader = modelLoader;
        this.model = model;
        this.bakedModel = bakedModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return this.getCustomModel(ClosetRegistry.CASINGS.getKeys().get(0), ClosetRegistry.BEDDINGS.getKeys().get(0), Direction.NORTH)
                .getQuads(state, side, rand);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        IClosetMaterial casing = extraData.getData(ClosetTileEntity.CASING);
        IClosetMaterial bedding = extraData.getData(ClosetTileEntity.BEDDING);
        Direction facing = extraData.getData(ClosetTileEntity.FACING);
        return this.getCustomModel(casing, bedding, facing).getQuads(state, side, rand);
    }

    public IBakedModel getCustomModel(@Nullable IClosetMaterial casing, @Nullable IClosetMaterial bedding, @Nullable Direction facing) {
        if (casing == null) casing = ClosetRegistry.CASINGS.getKeys().get(0);
        if (bedding == null) bedding = ClosetRegistry.BEDDINGS.getKeys().get(0);
        if (facing == null) facing = Direction.NORTH;

        String casingTexture = casing.getTexture();
        String beddingTexture = bedding.getTexture();

        return this.getCustomModel(casingTexture, beddingTexture, facing);
    }

    public IBakedModel getCustomModel(String casingResource, String beddingResource, Direction facing) {
        IBakedModel customModel = this.bakedModel;

        String key = casingResource + "," + beddingResource + "," + facing.toString();

        IBakedModel possibleModel = this.cache.get(key);

        if (possibleModel != null) {
            customModel = possibleModel;
        } else if (this.model != null) {
            List<BlockPart> elements = Lists.newArrayList();
            for (BlockPart part : this.model.getElements()) {
                elements.add(new BlockPart(part.positionFrom, part.positionTo, Maps.newHashMap(part.mapFaces),
                        part.partRotation, part.shade));
            }

            BlockModel newModel = new BlockModel(this.model.getParentLocation(), elements,
                    Maps.newHashMap(this.model.textures), this.model.isAmbientOcclusion(), this.model.func_230176_c_(),
                    this.model.getAllTransforms(), Lists.newArrayList(this.model.getOverrides()));
            newModel.name = this.model.name;
            newModel.parent = this.model.parent;

            Material beddingMaterial = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(beddingResource));
            Material casingMaterial = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(casingResource));
            newModel.textures.put("bedding", Either.left(beddingMaterial));
            newModel.textures.put("casing", Either.left(casingMaterial));
            newModel.textures.put("particle", Either.left(casingMaterial));

            ResourceLocation loc = UselessMod.getId("closet_overriding");
            customModel = newModel.bakeModel(this.modelLoader, ModelLoader.defaultTextureGetter(),
                    ClientUtils.getRotation(facing), loc);

            this.cache.put(key, customModel);
        }

        assert customModel != null;
        return customModel;
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        IClosetMaterial casing = data.getData(ClosetTileEntity.CASING);
        IClosetMaterial bedding = data.getData(ClosetTileEntity.BEDDING);
        Direction facing = data.getData(ClosetTileEntity.FACING);
        return this.getCustomModel(casing, bedding, facing).getParticleTexture();
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull ILightReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {
        IClosetMaterial casing = ClosetRegistry.CASINGS.getKeys().get(0);
        IClosetMaterial bedding = ClosetRegistry.BEDDINGS.getKeys().get(0);
        Direction facing = Direction.NORTH;

        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof ClosetTileEntity) {
            casing = ((ClosetTileEntity) tile).getCasingId();
            bedding = ((ClosetTileEntity) tile).getBeddingId();
        }

        if (state.has(BlockStateProperties.HORIZONTAL_FACING)) {
            facing = state.get(BlockStateProperties.HORIZONTAL_FACING);
        }

        tileData.setData(ClosetTileEntity.CASING, casing);
        tileData.setData(ClosetTileEntity.BEDDING, bedding);
        tileData.setData(ClosetTileEntity.FACING, facing);
        return tileData;
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
    public boolean func_230044_c_() {
        return this.isGui3d();
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
        return ITEM_OVERRIDE;
    }
}
