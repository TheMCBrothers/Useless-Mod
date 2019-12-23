package tk.themcbros.uselessmod.client.models.block;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.energy.ConnectionType;
import tk.themcbros.uselessmod.tileentity.PowerControlTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PowerControlBlockModel/* implements IDynamicBakedModel {

	private VertexFormat format;

	private static TextureAtlasSprite inputMask;
	private static TextureAtlasSprite outputMask;

	private static TextureAtlasSprite getInputMask() {
		if (inputMask == null) {
			inputMask = Minecraft.getInstance().getTextureMap().getAtlasSprite(UselessMod.MOD_ID + ":block/power_control_input");
		}
		return inputMask;
	}

	private static TextureAtlasSprite getOutputMask() {
		if (outputMask == null) {
			outputMask = Minecraft.getInstance().getTextureMap().getAtlasSprite(UselessMod.MOD_ID + ":block/power_control_output");
		}
		return outputMask;
	}

	private static TextureAtlasSprite getDefaultTexture() {
		return Minecraft.getInstance().getTextureMap().getAtlasSprite(UselessMod.MOD_ID + ":block/power_control_none");
	}

	public PowerControlBlockModel(VertexFormat format) {
		this.format = format;
	}

	private void putVertex(UnpackedBakedQuad.Builder builder, Vec3d normal,
						   double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float color) {
		for (int e = 0; e < format.getElementCount(); e++) {
			switch (format.getElement(e).getUsage()) {

				case POSITION:
					builder.put(e, (float)x, (float)y, (float)z, 1.0f);
					break;
				case COLOR:
					builder.put(e, color, color, color, 1.0f);
					break;
				case UV:
					if (format.getElement(e).getIndex() == 0) {
						u = sprite.getInterpolatedU(u);
						v = sprite.getInterpolatedV(v);
						builder.put(e, u, v, 0f, 1f);
						break;
					}
				case NORMAL:
					builder.put(e, (float) normal.x, (float) normal.y, (float) normal.z, 0f);
					break;
				default:
					builder.put(e);
					break;
			}
		}
	}

	private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite, float hilight) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);
		putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, hilight);
		putVertex(builder, normal, v2.x, v2.y, v2.z, 0, 16, sprite, hilight);
		putVertex(builder, normal, v3.x, v3.y, v3.z, 16, 16, sprite, hilight);
		putVertex(builder, normal, v4.x, v4.y, v4.z, 16, 0, sprite, hilight);
		return builder.build();
	}

	private static Vec3d v(double x, double y, double z) {
		return new Vec3d(x, y, z);
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
		if (side != null) {
			return Collections.emptyList();
		}

		ConnectionType north = extraData.getData(PowerControlTileEntity.NORTH);
		ConnectionType south = extraData.getData(PowerControlTileEntity.SOUTH);
		ConnectionType east = extraData.getData(PowerControlTileEntity.EAST);
		ConnectionType west = extraData.getData(PowerControlTileEntity.WEST);
		ConnectionType up = extraData.getData(PowerControlTileEntity.UP);
		ConnectionType down = extraData.getData(PowerControlTileEntity.DOWN);

		List<BakedQuad> quads = new ArrayList<>();

		float hilight = 1.0f;
		float o = .25f;

		// Up
		quads.add(createQuad(v(0, 1, 0), v(0, 1, 1), v(1, 1, 1), v(1, 1, 0), getDefaultTexture(), hilight));
		if (up == ConnectionType.INPUT)
			quads.add(createQuad(v(o, 1.02, o), v(o, 1.02, 1-o), v(1-o, 1.02, 1-o), v(1-o, 1.02, o), getInputMask(), hilight));
		if (up == ConnectionType.OUTPUT)
			quads.add(createQuad(v(o, 1.02, o), v(o, 1.02, 1-o), v(1-o, 1.02, 1-o), v(1-o, 1.02, o), getOutputMask(), hilight));

		// Down
		quads.add(createQuad(v(0, 0, 0), v(1, 0, 0), v(1, 0, 1), v(0, 0, 1), getDefaultTexture(), hilight));
		if (down == ConnectionType.INPUT)
			quads.add(createQuad(v(o, -.02, o), v(1-o, -.02, o), v(1-o, -.02, 1-o), v(o, -.02, 1-o), getInputMask(), hilight));
		if (down == ConnectionType.OUTPUT)
			quads.add(createQuad(v(o, -.02, o), v(1-o, -.02, o), v(1-o, -.02, 1-o), v(o, -.02, 1-o), getOutputMask(), hilight));

		// East
		quads.add(createQuad(v(1, 1, 1), v(1, 0, 1), v(1, 0, 0), v(1, 1, 0), getDefaultTexture(), hilight));
		if (east == ConnectionType.INPUT)
			quads.add(createQuad(v(1.02, 1-o, 1-o), v(1.02, o, 1-o), v(1.02, o, o), v(1.02, 1-o, o), getInputMask(), hilight));
		if (east == ConnectionType.OUTPUT)
			quads.add(createQuad(v(1.02, 1-o, 1-o), v(1.02, o, 1-o), v(1.02, o, o), v(1.02, 1-o, o), getOutputMask(), hilight));

		// West
		quads.add(createQuad(v(0, 1, 0), v(0, 0, 0), v(0, 0, 1), v(0, 1, 1), getDefaultTexture(), hilight));
		if (west == ConnectionType.INPUT)
			quads.add(createQuad(v(-.02, 1-o, o), v(-.02, o, o), v(-.02, o, 1-o), v(-.02, 1-o, 1-o), getInputMask(), hilight));
		if (west == ConnectionType.OUTPUT)
			quads.add(createQuad(v(-.02, 1-o, o), v(-.02, o, o), v(-.02, o, 1-o), v(-.02, 1-o, 1-o), getOutputMask(), hilight));

		// NORTH
		quads.add(createQuad(v(1, 1, 0), v(1, 0, 0), v(0, 0, 0), v(0, 1, 0), getDefaultTexture(), hilight));
		if (north == ConnectionType.INPUT)
			quads.add(createQuad(v(1-o, 1-o, -.02), v(1-o, o, -.02), v(o, o, -.02), v(o, 1-o, -.02), getInputMask(), hilight));
		if (north == ConnectionType.OUTPUT)
			quads.add(createQuad(v(1-o, 1-o, -.02), v(1-o, o, -.02), v(o, o, -.02), v(o, 1-o, -.02), getOutputMask(), hilight));

		// South
		quads.add(createQuad(v(0, 1, 1), v(0, 0, 1), v(1, 0, 1), v(1, 1, 1), getDefaultTexture(), hilight));
		if (south == ConnectionType.INPUT)
			quads.add(createQuad(v(o, 1-o, 1.02), v(o, o, 1.02), v(1-o, o, 1.02), v(1-o, 1-o, 1.02), getInputMask(), hilight));
		if (south == ConnectionType.OUTPUT)
			quads.add(createQuad(v(o, 1-o, 1.02), v(o, o, 1.02), v(1-o, o, 1.02), v(1-o, 1-o, 1.02), getOutputMask(), hilight));

		return quads;
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
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Nonnull
	@Override
	public TextureAtlasSprite getParticleTexture() {
		return getDefaultTexture();
	}

	@Nonnull
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Nonnull
	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.EMPTY;
	}
}
*/{}