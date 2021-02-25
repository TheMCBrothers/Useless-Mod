package themcbros.uselessmod.client.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;
import themcbros.uselessmod.UselessMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public final class DynamicBucketModel implements IModelGeometry<DynamicBucketModel> {
    // minimal Z offset to prevent depth-fighting
    private static final float NORTH_Z_COVER = 7.496f / 16f;
    private static final float SOUTH_Z_COVER = 8.504f / 16f;
    private static final float NORTH_Z_FLUID = 7.498f / 16f;
    private static final float SOUTH_Z_FLUID = 8.502f / 16f;

    @Nonnull
    private final FluidStack fluid;

    private final boolean flipGas;
    private final boolean tint;
    private final boolean coverIsMask;
    private final boolean applyFluidLuminosity;

    @Deprecated
    public DynamicBucketModel(FluidStack fluid, boolean flipGas, boolean tint, boolean coverIsMask) {
        this(fluid, flipGas, tint, coverIsMask, true);
    }

    public DynamicBucketModel(@Nonnull FluidStack fluid, boolean flipGas, boolean tint, boolean coverIsMask, boolean applyFluidLuminosity) {
        this.fluid = fluid;
        this.flipGas = flipGas;
        this.tint = tint;
        this.coverIsMask = coverIsMask;
        this.applyFluidLuminosity = applyFluidLuminosity;
    }

    /**
     * Returns a new ModelDynBucket representing the given fluid, but with the same
     * other properties (flipGas, tint, coverIsMask).
     */
    public DynamicBucketModel withFluid(FluidStack newFluid) {
        return new DynamicBucketModel(newFluid, flipGas, tint, coverIsMask, applyFluidLuminosity);
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        Fluid fluid = this.fluid.getFluid();

        RenderMaterial particleLocation = owner.isTexturePresent("particle") ? owner.resolveTexture("particle") : null;
        RenderMaterial baseLocation = owner.isTexturePresent("base") ? owner.resolveTexture("base") : null;
        RenderMaterial fluidMaskLocation = owner.isTexturePresent("fluid") ? owner.resolveTexture("fluid") : null;
        RenderMaterial coverLocation = owner.isTexturePresent("cover") ? owner.resolveTexture("cover") : null;

        IModelTransform transformsFromModel = owner.getCombinedTransform();

        TextureAtlasSprite fluidSprite = fluid != Fluids.EMPTY ? spriteGetter.apply(ForgeHooksClient.getBlockMaterial(fluid.getAttributes().getStillTexture())) : null;
        TextureAtlasSprite coverSprite = (coverLocation != null && (!coverIsMask || baseLocation != null)) ? spriteGetter.apply(coverLocation) : null;

        ImmutableMap<ItemCameraTransforms.TransformType, TransformationMatrix> transformMap =
                PerspectiveMapWrapper.getTransforms(new ModelTransformComposition(transformsFromModel, modelTransform));

        TextureAtlasSprite particleSprite = particleLocation != null ? spriteGetter.apply(particleLocation) : null;

        if (particleSprite == null) particleSprite = fluidSprite;
        if (particleSprite == null && !coverIsMask) particleSprite = coverSprite;

        // if the fluid is lighter than air, will manipulate the initial state to be rotated 180deg to turn it upside down
        if (flipGas && fluid != Fluids.EMPTY && fluid.getAttributes().isLighterThanAir()) {
            modelTransform = new SimpleModelTransform(
                    modelTransform.getRotation().blockCornerToCenter().composeVanilla(
                            new TransformationMatrix(null, new Quaternion(0, 0, 1, 0), null, null)).blockCenterToCorner());
        }

        TransformationMatrix transform = modelTransform.getRotation();

        assert particleSprite != null;
        ItemMultiLayerBakedModel.Builder builder = ItemMultiLayerBakedModel.builder(owner, particleSprite, new DynamicBucketModel.ContainedFluidOverrideHandler(overrides, bakery, owner, this), transformMap);

        if (baseLocation != null) {
            // build base
            builder.addQuads(ItemLayerModel.getLayerRenderType(false), ItemLayerModel.getQuadsForSprites(ImmutableList.of(baseLocation), transform, spriteGetter));
        }

        if (fluidMaskLocation != null && fluidSprite != null) {
            TextureAtlasSprite templateSprite = spriteGetter.apply(fluidMaskLocation);
            if (templateSprite != null) {
                // build liquid layer (inside)
                int luminosity = applyFluidLuminosity ? fluid.getAttributes().getLuminosity(this.fluid) : 0;
                int color = tint ? fluid.getAttributes().getColor(this.fluid) : 0xFFFFFFFF;
                builder.addQuads(ItemLayerModel.getLayerRenderType(luminosity > 0), ItemTextureQuadConverter.convertTexture(transform, templateSprite, fluidSprite, NORTH_Z_FLUID, Direction.NORTH, color, 1, luminosity));
                builder.addQuads(ItemLayerModel.getLayerRenderType(luminosity > 0), ItemTextureQuadConverter.convertTexture(transform, templateSprite, fluidSprite, SOUTH_Z_FLUID, Direction.SOUTH, color, 1, luminosity));
            }
        }

        if (coverIsMask) {
            if (coverSprite != null) {
                TextureAtlasSprite baseSprite = spriteGetter.apply(baseLocation);
                builder.addQuads(ItemLayerModel.getLayerRenderType(false), ItemTextureQuadConverter.convertTexture(transform, coverSprite, baseSprite, NORTH_Z_COVER, Direction.NORTH, 0xFFFFFFFF, 2));
                builder.addQuads(ItemLayerModel.getLayerRenderType(false), ItemTextureQuadConverter.convertTexture(transform, coverSprite, baseSprite, SOUTH_Z_COVER, Direction.SOUTH, 0xFFFFFFFF, 2));
            }
        } else {
            if (coverSprite != null) {
                builder.addQuads(ItemLayerModel.getLayerRenderType(false), ItemTextureQuadConverter.genQuad(transform, 0, 0, 16, 16, NORTH_Z_COVER, coverSprite, Direction.NORTH, 0xFFFFFFFF, 2));
                builder.addQuads(ItemLayerModel.getLayerRenderType(false), ItemTextureQuadConverter.genQuad(transform, 0, 0, 16, 16, SOUTH_Z_COVER, coverSprite, Direction.SOUTH, 0xFFFFFFFF, 2));
            }
        }

        builder.setParticle(particleSprite);

        return builder.build();
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Set<RenderMaterial> texs = Sets.newHashSet();

        if (owner.isTexturePresent("particle")) texs.add(owner.resolveTexture("particle"));
        if (owner.isTexturePresent("base")) texs.add(owner.resolveTexture("base"));
        if (owner.isTexturePresent("fluid")) texs.add(owner.resolveTexture("fluid"));
        if (owner.isTexturePresent("cover")) texs.add(owner.resolveTexture("cover"));

        return texs;
    }

    public enum Loader implements IModelLoader<DynamicBucketModel> {
        INSTANCE;

        @Override
        public IResourceType getResourceType() {
            return VanillaResourceType.MODELS;
        }

        @Override
        public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
            // no need to clear cache since we create a new model instance
        }

        @Override
        public void onResourceManagerReload(@Nonnull IResourceManager resourceManager, @Nonnull Predicate<IResourceType> resourcePredicate) {
            // no need to clear cache since we create a new model instance
        }

        @Override
        public DynamicBucketModel read(@Nonnull JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            if (!modelContents.has("fluid"))
                throw new RuntimeException("Bucket model requires 'fluid' value.");

            ResourceLocation fluidName = new ResourceLocation(modelContents.get("fluid").getAsString());

            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);

            boolean flip = false;
            if (modelContents.has("flipGas")) {
                flip = modelContents.get("flipGas").getAsBoolean();
            }

            boolean tint = true;
            if (modelContents.has("applyTint")) {
                tint = modelContents.get("applyTint").getAsBoolean();
            }

            boolean coverIsMask = true;
            if (modelContents.has("coverIsMask")) {
                coverIsMask = modelContents.get("coverIsMask").getAsBoolean();
            }

            boolean applyFluidLuminosity = true;
            if (modelContents.has("applyFluidLuminosity")) {
                applyFluidLuminosity = modelContents.get("applyFluidLuminosity").getAsBoolean();
            }

            // create new model with correct liquid
            assert fluid != null;
            return new DynamicBucketModel(new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME), flip, tint, coverIsMask, applyFluidLuminosity);
        }
    }

    private static final class ContainedFluidOverrideHandler extends ItemOverrideList {
        private final Map<String, IBakedModel> cache = Maps.newHashMap(); // contains all the baked models since they'll never change
        private final ItemOverrideList nested;
        private final ModelBakery bakery;
        private final IModelConfiguration owner;
        private final DynamicBucketModel parent;

        private ContainedFluidOverrideHandler(ItemOverrideList nested, ModelBakery bakery, IModelConfiguration owner, DynamicBucketModel parent) {
            this.nested = nested;
            this.bakery = bakery;
            this.owner = owner;
            this.parent = parent;
        }

        @Override
        public IBakedModel getOverrideModel(@Nonnull IBakedModel originalModel, @Nonnull ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity) {
            IBakedModel overrideModel = nested.getOverrideModel(originalModel, stack, world, entity);
            if (overrideModel != originalModel) return overrideModel;
            return FluidUtil.getFluidContained(stack)
                    .map(fluidStack -> {
                        Fluid fluid = fluidStack.getFluid();
                        String addition = fluidStack.hasTag() ? fluidStack.getTag().toString() : "_0";
                        String name = fluid.getRegistryName() + addition;

                        if (!cache.containsKey(name)) {
                            DynamicBucketModel unbaked = this.parent.withFluid(fluidStack);
                            IBakedModel bakedModel = unbaked.bake(owner, bakery, ModelLoader.defaultTextureGetter(), ModelRotation.X0_Y0, this, UselessMod.rl("bucket_override"));
                            cache.put(name, bakedModel);
                            return bakedModel;
                        }

                        return cache.get(name);
                    })
                    // not a fluid item apparently
                    .orElse(originalModel); // empty bucket
        }
    }
}