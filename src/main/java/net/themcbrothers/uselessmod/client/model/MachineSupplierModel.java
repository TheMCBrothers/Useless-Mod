package net.themcbrothers.uselessmod.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlas;
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
import net.minecraft.world.level.block.Blocks;
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
    private static final ResourceLocation TEXTURE = new ResourceLocation("block/cobblestone");

    @NotNull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull IModelData extraData) {
        RenderType type = MinecraftForgeClient.getRenderType();

        BlockState mimic = extraData.getData(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        if (mimic == null || mimic.is(ModBlocks.MACHINE_SUPPLIER.get())) {
            mimic = Blocks.COBBLESTONE.defaultBlockState();
        }

        if (type == null || ItemBlockRenderTypes.canRenderInLayer(mimic, type)) {
            BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(mimic);
            return model.getQuads(mimic, side, rand, EmptyModelData.INSTANCE);
        }

        return Collections.emptyList();
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@NotNull IModelData data) {
        BlockState mimic = data.getData(MachineSupplierBlockEntity.MIMIC_PROPERTY);
        if (mimic != null) {
            BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(mimic);
            return model.getParticleIcon(EmptyModelData.INSTANCE);
        }
        return this.getParticleIcon();
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
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(TEXTURE);
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
            if (tag != null && tag.contains("Mimic", Tag.TAG_COMPOUND)) {
                BlockState mimic = NbtUtils.readBlockState(tag.getCompound("Mimic"));
                return Minecraft.getInstance().getBlockRenderer().getBlockModel(mimic);
            }
            return model;
        }
    }

    private static class Geometry implements IModelGeometry<Geometry> {
        @Override
        public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
            return new MachineSupplierModel();
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