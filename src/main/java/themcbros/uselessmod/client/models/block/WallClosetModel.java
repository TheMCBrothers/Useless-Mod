package themcbros.uselessmod.client.models.block;

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
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IModelData;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;
import themcbros.uselessmod.api.wall_closet.ClosetMaterialInit;
import themcbros.uselessmod.helpers.ClientUtils;
import themcbros.uselessmod.tileentity.WallClosetTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @author TheMCBrothers
 */
public class WallClosetModel implements IBakedModel {

    private final ModelLoader modelLoader;
    private final BlockModel model;
    private final IBakedModel bakedModel;

    private final Map<String, IBakedModel> cache = Maps.newHashMap();

    public WallClosetModel(ModelLoader modelLoader, BlockModel model, IBakedModel bakedModel) {
        this.modelLoader = modelLoader;
        this.model = model;
        this.bakedModel = bakedModel;
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
            customModel = newModel.bakeModel(this.modelLoader, ModelLoader.defaultTextureGetter(),
                    ClientUtils.getRotation(facing), loc);

            this.cache.put(key, customModel);
        }

        assert customModel != null;
        return customModel;
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {
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

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        ClosetMaterial material = getMaterial(extraData.getData(WallClosetTileEntity.CLOSET_MATERIAL));
        Direction facing = getFacing(extraData.getData(WallClosetTileEntity.FACING));
        return this.getCustomModel(material, facing).getQuads(state, side, rand);
    }

    @Nonnull
    private ClosetMaterial getMaterial(@Nullable ClosetMaterial material) {
        if (material == null) {
            material = ClosetMaterialInit.OAK_PLANKS.get();
        }
        return material;
    }

    @Nonnull
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
        return new WallClosetItemOverride();
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        ClosetMaterial material = getMaterial(data.getData(WallClosetTileEntity.CLOSET_MATERIAL));
        Direction facing = getFacing(data.getData(WallClosetTileEntity.FACING));
        return this.getCustomModel(material, facing).getParticleTexture();
    }
}
