package net.themcbrothers.uselessmod.client.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.BlockGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.entity.WallClosetBlockEntity;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class WallClosetModel implements IDynamicBakedModel {
    private static final ItemOverrides OVERRIDE = new ItemOverrideHandler();

    private final ModelBaker modelBakery;
    private final BlockModel model;
    private final BakedModel bakedModel;
    private final ModelState modelTransform;
    private final Function<Material, TextureAtlasSprite> spriteGetter;

    private final Map<String, BakedModel> cache = Maps.newHashMap();

    public WallClosetModel(ModelBaker modelBakery, BlockModel model, ModelState modelTransform, Function<Material, TextureAtlasSprite> spriteGetter) {
        this.modelBakery = modelBakery;
        this.model = model;
        this.bakedModel = model.bake(modelBakery, model, spriteGetter, modelTransform, UselessMod.rl("closet"), true);
        this.modelTransform = modelTransform;
        this.spriteGetter = spriteGetter;
    }

    private BakedModel getCustomModel(Block material, Direction facing) {
        BakedModel customModel;
        String key = ForgeRegistries.BLOCKS.getKey(material) + ";" + facing.getName();
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

            Material renderMaterial = new Material(InventoryMenu.BLOCK_ATLAS, Minecraft.getInstance()
                    .getBlockRenderer().getBlockModel(material.defaultBlockState()).getParticleIcon(ModelData.EMPTY).contents().name());
            newModel.textureMap.put("planks", Either.left(renderMaterial));
            newModel.textureMap.put("particle", Either.left(renderMaterial));

            customModel = newModel.bake(this.modelBakery, newModel, this.spriteGetter,
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
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        return this.getCustomModel(getMaterial(extraData.get(WallClosetBlockEntity.MATERIAL_PROPERTY)), getFacing(state))
                .getQuads(state, side, rand, extraData, renderType);
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@NotNull ModelData data) {
        //noinspection deprecation
        return this.getCustomModel(getMaterial(data.get(WallClosetBlockEntity.MATERIAL_PROPERTY)), Direction.NORTH).getParticleIcon();
    }

    @NotNull
    @Override
    public ModelData getModelData(@NotNull BlockAndTintGetter level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull ModelData modelData) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
            return modelData.derive().with(WallClosetBlockEntity.MATERIAL_PROPERTY, wallClosetBlockEntity.getMaterial()).build();
        }

        return modelData;
    }

    @Override
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        return ChunkRenderTypeSet.of(RenderType.cutout());
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
        return this.bakedModel.getParticleIcon(ModelData.EMPTY);
    }

    @Override
    public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        return this.bakedModel.applyTransform(transformType, poseStack, applyLeftHandTransform);
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
                CompoundTag tag = BlockItem.getBlockEntityData(stack);
                if (tag != null && tag.contains("Material", Tag.TAG_STRING)) {
                    final ResourceLocation key = ResourceLocation.tryParse(tag.getString("Material"));
                    final Block material = ForgeRegistries.BLOCKS.containsKey(key) ? ForgeRegistries.BLOCKS.getValue(key) : Blocks.OAK_PLANKS;
                    return wallClosetModel.getCustomModel(Objects.requireNonNull(material), Direction.NORTH);
                }
            }
            return model;
        }
    }

    private static class Geometry implements IUnbakedGeometry<Geometry> {
        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
            BlockModel ownerModel = ((BlockGeometryBakingContext) context).owner;
            if (ownerModel == null)
                throw new RuntimeException("Wall Closet owner model is null");
            BlockModel blockModel = ownerModel.parent;
            if (blockModel == null)
                throw new RuntimeException("Wall Closet parent model is null");
            return new WallClosetModel(baker, blockModel, modelTransform, spriteGetter);
        }
    }

    public static class Loader implements IGeometryLoader<Geometry> {
        public static final Loader INSTANCE = new Loader();

        private Loader() {
        }

        @Override
        public Geometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
            if (!jsonObject.has("parent"))
                throw new RuntimeException("Wall Closet model requires 'parent' value.");

            return new Geometry();
        }
    }
}
