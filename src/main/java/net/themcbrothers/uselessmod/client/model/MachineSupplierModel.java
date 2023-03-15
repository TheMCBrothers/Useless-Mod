package net.themcbrothers.uselessmod.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.BlockGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class MachineSupplierModel implements IDynamicBakedModel {
    private static final ItemOverrides OVERRIDE = new ItemOverrideHandler();

    private final BakedModel baseModel;

    private MachineSupplierModel(BakedModel baseModel) {
        this.baseModel = baseModel;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        BlockState mimic = extraData.get(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        if (mimic == null || mimic.is(ModBlocks.MACHINE_SUPPLIER.get())) {
            return this.baseModel.getQuads(mimic, side, rand, ModelData.EMPTY, renderType);
        }

        if (renderType == null || this.getRenderTypes(mimic, rand, ModelData.EMPTY).contains(renderType)) {
            BakedModel model = getMimicModel(mimic);
            return model.getQuads(mimic, side, rand, ModelData.EMPTY, renderType);
        }

        return Collections.emptyList();
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@NotNull ModelData data) {
        BlockState mimic = data.get(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        BakedModel model = getMimicModel(mimic);
        return model.getParticleIcon(ModelData.EMPTY);
    }

    @Override
    public BakedModel applyTransform(ItemTransforms.TransformType transformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        return this.baseModel.applyTransform(transformType, poseStack, applyLeftHandTransform);
    }

    private BakedModel getMimicModel(@Nullable BlockState mimic) {
        if (mimic == null || mimic.isAir()) {
            return this.baseModel;
        }

        return Minecraft.getInstance().getBlockRenderer().getBlockModel(mimic);
    }

    @Override
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        BlockState mimic = data.get(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        BakedModel model = getMimicModel(mimic);
        return mimic != null ? model.getRenderTypes(mimic, rand, ModelData.EMPTY) : this.baseModel.getRenderTypes(state, rand, data);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return this.baseModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return getMimicModel(null).getParticleIcon(ModelData.EMPTY);
    }

    @Override
    public ItemOverrides getOverrides() {
        return OVERRIDE;
    }

    private static class ItemOverrideHandler extends ItemOverrides {
        @Nullable
        @Override
        public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
            CompoundTag tag = BlockItem.getBlockEntityData(stack);
            BlockState mimic = null;

            if (tag != null && tag.contains("Mimic", Tag.TAG_COMPOUND)) {
                mimic = NbtUtils.readBlockState(tag.getCompound("Mimic"));
            }

            return ((MachineSupplierModel) model).getMimicModel(mimic);
        }
    }

    private static class Geometry implements IUnbakedGeometry<Geometry> {
        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            BlockModel baseModel = ((BlockGeometryBakingContext) context).owner.parent;

            if (baseModel == null) {
                throw new NullPointerException("Expected model parent model " + modelLocation);
            }

            BakedModel bakedModel = baseModel.bake(bakery, baseModel, spriteGetter, modelState, modelLocation, context.useBlockLight());
            return new MachineSupplierModel(bakedModel);
        }

        @Override
        public Collection<Material> getMaterials(IGeometryBakingContext context, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            return Collections.emptyList();
        }
    }

    public static class Loader implements IGeometryLoader<Geometry> {
        public static final Loader INSTANCE = new Loader();

        @Override
        public Geometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
            return new Geometry();
        }
    }
}
