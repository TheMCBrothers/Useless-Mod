package net.themcbrothers.uselessmod.client.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.entity.WallClosetBlockEntity;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class WallClosetModel implements IDynamicBakedModel {
    private static final ItemOverrides OVERRIDE = new ItemOverrideHandler();

    private final ModelBakery modelBakery;
    private final BlockModel model;
    private final BakedModel bakedModel;
    private final ModelState modelTransform;

    private final Map<String, BakedModel> cache = Maps.newHashMap();

    public WallClosetModel(ModelBakery modelBakery, BlockModel model, ModelState modelTransform, Function<Material, TextureAtlasSprite> spriteGetter) {
        this.modelBakery = modelBakery;
        this.model = model;
        this.bakedModel = model.bake(modelBakery, model, spriteGetter, modelTransform, UselessMod.rl("closet"), true);
        this.modelTransform = modelTransform;
    }

    private BakedModel getCustomModel(Block material, Direction facing) {
        BakedModel customModel;
        String key = material.getRegistryName() + ";" + facing.getName();
        BakedModel possibleModel = this.cache.get(key);

        if (possibleModel != null) {
            customModel = possibleModel;
        } else {
            List<BlockElement> elements = Lists.newArrayList();
            for (BlockElement part : this.model.getElements()) {
                elements.add(new BlockElement(part.from, part.to, Maps.newHashMap(part.faces), part.rotation, part.shade));
            }

            BlockModel newModel = new BlockModel(this.model.getParentLocation(), elements,
                    Maps.newHashMap(this.model.textureMap), this.model.hasAmbientOcclusion,
                    this.model.getGuiLight(), this.model.getTransforms(), this.model.getOverrides());
            newModel.name = this.model.name;
            newModel.parent = this.model.parent;

            ;
            Material renderMaterial = new Material(TextureAtlas.LOCATION_BLOCKS, Minecraft.getInstance()
                    .getBlockRenderer().getBlockModel(material.defaultBlockState()).getParticleIcon(EmptyModelData.INSTANCE).getName());
            newModel.textureMap.put("planks", Either.left(renderMaterial));
            newModel.textureMap.put("particle", Either.left(renderMaterial));

            customModel = newModel.bake(this.modelBakery, newModel, ForgeModelBakery.defaultTextureGetter(),
                    this.modelTransform, UselessMod.rl("closet_overriding"), true);

            this.cache.put(key, customModel);
        }

        return customModel;
    }

    private Block getMaterial(@Nullable Block block) {
        return block == null ? Blocks.OAK_PLANKS : block;
    }

    private Direction getFacing(@Nullable BlockState state) {
        return state == null || !state.hasProperty(HORIZONTAL_FACING) ? Direction.NORTH : state.getValue(HORIZONTAL_FACING);
    }

    @NotNull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull IModelData extraData) {
        return this.getCustomModel(getMaterial(extraData.getData(WallClosetBlockEntity.MATERIAL_PROPERTY)), getFacing(state))
                .getQuads(state, side, rand, extraData);
    }

    @NotNull
    @Override
    public IModelData getModelData(@NotNull BlockAndTintGetter level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull IModelData modelData) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
            modelData.setData(WallClosetBlockEntity.MATERIAL_PROPERTY, wallClosetBlockEntity.getMaterial());
        }
        return modelData;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.bakedModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.bakedModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.bakedModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.bakedModel.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.bakedModel.getParticleIcon();
    }

    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack poseStack) {
        return this.bakedModel.handlePerspective(cameraTransformType, poseStack);
    }

    @Override
    public ItemOverrides getOverrides() {
        return OVERRIDE;
    }

    private static class ItemOverrideHandler extends ItemOverrides {
        @Nullable
        @Override
        public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
            if (model instanceof WallClosetModel wallClosetModel) {
                CompoundTag tag = stack.getTag();
                if (tag != null && tag.contains("Material", Tag.TAG_STRING)) {
                    final ResourceLocation key = ResourceLocation.tryParse(tag.getString("Material"));
                    final Block material = ForgeRegistries.BLOCKS.containsKey(key) ? ForgeRegistries.BLOCKS.getValue(key) : Blocks.OAK_PLANKS;
                    return wallClosetModel.getCustomModel(Objects.requireNonNull(material), Direction.NORTH);
                }
            }
            return model;
        }
    }

    private static class Geometry implements IModelGeometry<Geometry> {
        @Override
        public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
            BlockModel ownerModel = (BlockModel) owner.getOwnerModel();
            if (ownerModel == null)
                throw new RuntimeException("Wall Closet owner model is null");
            BlockModel blockModel = ownerModel.parent;
            if (blockModel == null)
                throw new RuntimeException("Wall Closet parent model is null");
            return new WallClosetModel(bakery, blockModel, modelTransform, spriteGetter);
        }

        @Override
        public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            return Collections.emptyList();
        }
    }

    public static class Loader implements IModelLoader<Geometry> {
        public static final Loader INSTANCE = new Loader();

        private Loader() {
        }

        @Override
        public Geometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            if (!modelContents.has("parent"))
                throw new RuntimeException("Wall Closet model requires 'parent' value.");
            return new Geometry();
        }

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager) {
        }
    }
}
