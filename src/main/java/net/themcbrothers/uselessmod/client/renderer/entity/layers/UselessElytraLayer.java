package net.themcbrothers.uselessmod.client.renderer.entity.layers;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.UselessElytraItem;

import javax.annotation.Nonnull;

public class UselessElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
    private static final ResourceLocation WINGS_LOCATION = UselessMod.rl("textures/entity/useless_elytra.png");

    public UselessElytraLayer(RenderLayerParent<T, M> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent, entityModelSet);
    }

    @Override
    public boolean shouldRender(@Nonnull ItemStack stack, @Nonnull T entity) {
        return stack.getItem() instanceof UselessElytraItem;
    }

    @Nonnull
    @Override
    public ResourceLocation getElytraTexture(@Nonnull ItemStack stack, @Nonnull T entity) {
        return WINGS_LOCATION;
    }
}
