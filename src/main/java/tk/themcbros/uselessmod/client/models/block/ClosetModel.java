package tk.themcbros.uselessmod.client.models.block;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.common.model.TRSRTransformation;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.tileentity.ClosetTileEntity;

@SuppressWarnings("deprecation")
public class ClosetModel implements IBakedModel {
	
	public static ClosetItemOverride ITEM_OVERIDE = new ClosetItemOverride();

	private ModelLoader modelLoader;
	private BlockModel model;
	private IBakedModel bakedModel;

	private final VertexFormat format;
	private final Map<String, IBakedModel> cache = Maps.newHashMap();

	public ClosetModel(ModelLoader modelLoader, BlockModel model, IBakedModel bakedModel, VertexFormat format) {
		this.modelLoader = modelLoader;
		this.model = model;
		this.bakedModel = bakedModel;
		this.format = format;
	}

	public IBakedModel getCustomModel(IClosetMaterial casing, IClosetMaterial bedding, Direction facing) {
		// Hotfix for possible optifine bug
        if (casing == null) { casing = ClosetRegistry.CASINGS.getKeys().get(0); }
        if (bedding == null) { bedding = ClosetRegistry.BEDDINGS.getKeys().get(0); }
        if (facing == null) { facing = Direction.NORTH; }
		
		String casingTex = casing.getTexture();
		String beddingTex = bedding.getTexture();

		return this.getCustomModel(casingTex, beddingTex, facing);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		return this.getCustomModel(ClosetRegistry.CASINGS.getKeys().get(0), ClosetRegistry.BEDDINGS.getKeys().get(0), Direction.NORTH).getQuads(state, side, rand);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand,
			@Nonnull IModelData data) {
		IClosetMaterial casing = data.getData(ClosetTileEntity.CASING);
		IClosetMaterial bedding = data.getData(ClosetTileEntity.BEDDING);
		Direction facing = data.getData(ClosetTileEntity.FACING);
		return this.getCustomModel(casing, bedding, facing).getQuads(state, side, rand);
	}

	@Override
	public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
		IClosetMaterial casing = data.getData(ClosetTileEntity.CASING);
		IClosetMaterial bedding = data.getData(ClosetTileEntity.BEDDING);
		Direction facing = data.getData(ClosetTileEntity.FACING);
		return this.getCustomModel(casing, bedding, facing).getParticleTexture();
	}

	@Override
	public IModelData getModelData(@Nonnull IEnviromentBlockReader world, @Nonnull BlockPos pos,
			@Nonnull BlockState state, @Nonnull IModelData tileData) {
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

	public IBakedModel getCustomModel(@Nonnull String casingResource, @Nonnull String beddingResource,
			@Nonnull Direction facing) {
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
					Maps.newHashMap(this.model.textures), this.model.isAmbientOcclusion(), this.model.isGui3d(),
					this.model.getAllTransforms(), Lists.newArrayList(this.model.getOverrides()));
			newModel.name = this.model.name;
			newModel.parent = this.model.parent;

			newModel.textures.put("bedding", beddingResource);
			newModel.textures.put("casing", casingResource);
			newModel.textures.put("particle", casingResource);

			customModel = newModel.bake(this.modelLoader, ModelLoader.defaultTextureGetter(),
					TRSRTransformation.getRotation(facing), this.format);
			this.cache.put(key, customModel);
		}

		return customModel;
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
	public boolean isBuiltInRenderer() {
		return this.bakedModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return this.bakedModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return this.bakedModel.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ITEM_OVERIDE;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(
			ItemCameraTransforms.TransformType cameraTransformType) {
		return Pair.of(this, this.bakedModel.handlePerspective(cameraTransformType).getRight());
	}

}
