package tk.themcbros.uselessmod.client.models.block;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

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
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.lists.ModBeddings;
import tk.themcbros.uselessmod.tileentity.ClosetTileEntity;

public class ClosetModel implements IBakedModel {
	
	public static ClosetItemOverride ITEM_OVERRIDE = new ClosetItemOverride();

	private ModelLoader modelLoader;
	private BlockModel model;
	private IBakedModel bakedModel;
	
	private final VertexFormat format;
	private final Map<Triple<String, String, Direction>, IBakedModel> cache = Maps.newHashMap();
	
	public ClosetModel(ModelLoader modelLoader, BlockModel model, IBakedModel bakedModel, VertexFormat format) {
		this.modelLoader = modelLoader;
		this.model = model;
		this.bakedModel = bakedModel;
		this.format = format;
	}

	public IBakedModel getCustomModel(IClosetMaterial casing, IClosetMaterial bedding, Direction facing, Boolean open) {
		String casingTex = casing.getTexture();
		String beddingTex = bedding.getTexture();

		return this.getCustomModel(casingTex, beddingTex, facing, open);
	}
	
	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		return this.getCustomModel(ModBeddings.OAK, ModBeddings.WHITE, Direction.NORTH, false).getQuads(state, side, rand);
	}
	
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand,
			@Nonnull IModelData extraData) {
		IClosetMaterial casing = extraData.getData(ClosetTileEntity.CASING);
		IClosetMaterial bedding = extraData.getData(ClosetTileEntity.BEDDING);
		Direction facing = extraData.getData(ClosetTileEntity.FACING);
		Boolean open = extraData.getData(ClosetTileEntity.OPEN);
		return this.getCustomModel(casing, bedding, facing, open).getQuads(state, side, rand);
	}
	
	@Override
	public IModelData getModelData(IEnviromentBlockReader world, BlockPos pos, BlockState state, IModelData tileData) {
		IClosetMaterial casing = ModBeddings.OAK;
		IClosetMaterial bedding = ModBeddings.WHITE;
		Direction facing = Direction.NORTH;
		Boolean open = false;

		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof ClosetTileEntity) {
			casing = ((ClosetTileEntity) tile).getCasingId();
			bedding = ((ClosetTileEntity) tile).getBeddingId();
		}
		
		if(state.has(BlockStateProperties.OPEN))
			open = state.get(BlockStateProperties.OPEN);
		if(state.has(BlockStateProperties.HORIZONTAL_FACING))
			facing = state.get(BlockStateProperties.HORIZONTAL_FACING);

		tileData.setData(ClosetTileEntity.CASING, casing);
		tileData.setData(ClosetTileEntity.BEDDING, bedding);
		tileData.setData(ClosetTileEntity.FACING, facing);
		tileData.setData(ClosetTileEntity.OPEN, open);
		return tileData;
	}

	
	public IBakedModel getCustomModel(@Nonnull String casingResource, @Nonnull String beddingResource,
			@Nonnull Direction facing, Boolean open) {
		IBakedModel customModel = this.bakedModel;

		Triple<String, String, Direction> key = ImmutableTriple.of(casingResource, beddingResource, facing);

		IBakedModel possibleModel = this.cache.get(key);

		if (possibleModel != null) {
			customModel = possibleModel;
		} else if (this.model != null) {
			List<BlockPart> elements = Lists.newArrayList(); // We have to duplicate this so we can edit it below.
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
	public ItemOverrideList getOverrides() {
		return ITEM_OVERRIDE;
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		return Pair.of(this, this.bakedModel.handlePerspective(cameraTransformType).getRight());
	}

}
