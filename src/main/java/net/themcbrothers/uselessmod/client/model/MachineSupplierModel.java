package net.themcbrothers.uselessmod.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
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
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class MachineSupplierModel implements IDynamicBakedModel {
    private static final ItemOverrides OVERRIDE = new ItemOverrideHandler();

    private final BakedModel baseModel;

    private MachineSupplierModel(BakedModel baseModel) {
        this.baseModel = baseModel;
    }

    @NotNull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull IModelData extraData) {
        RenderType type = MinecraftForgeClient.getRenderType();

        BlockState mimic = extraData.getData(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        if (mimic == null || mimic.is(ModBlocks.MACHINE_SUPPLIER.get())) {
            return this.baseModel.getQuads(mimic, side, rand, EmptyModelData.INSTANCE);
        }

        if (type == null || ItemBlockRenderTypes.canRenderInLayer(mimic, type)) {
            BakedModel model = getMimicModel(mimic);
            return model.getQuads(mimic, side, rand, EmptyModelData.INSTANCE);
        }

        return Collections.emptyList();
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@NotNull IModelData data) {
        BlockState mimic = data.getData(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        BakedModel model = getMimicModel(mimic);
        return model.getParticleIcon(EmptyModelData.INSTANCE);
    }

    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack poseStack) {
        return this.baseModel.handlePerspective(cameraTransformType, poseStack);
    }

    private BakedModel getMimicModel(@Nullable BlockState mimic) {
        if (mimic == null || mimic.isAir()) {
            return this.baseModel;
        }

        return Minecraft.getInstance().getBlockRenderer().getBlockModel(mimic);
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
        return getMimicModel(null).getParticleIcon(EmptyModelData.INSTANCE);
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

    private static class Geometry implements IModelGeometry<Geometry> {
        @Override
        public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
            BlockModel baseModel = ((BlockModel) Objects.requireNonNull(owner.getOwnerModel())).parent;

            if (baseModel == null) {
                throw new NullPointerException("Expected model parent model " + modelLocation);
            }

            BakedModel bakedModel = baseModel.bake(bakery, baseModel, spriteGetter, modelTransform, modelLocation, owner.isSideLit());
            return new MachineSupplierModel(bakedModel);
        }

        @Override
        public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            return Collections.emptyList();
        }
    }

    public static class Loader implements IModelLoader<Geometry> {
        public static final Loader INSTANCE = new Loader();

        @Override
        public Geometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            return new Geometry();
        }

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager) {
        }
    }
}
