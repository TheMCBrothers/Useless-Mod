package themcbros.uselessmod.client.renderer.entity.layer;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.item.UselessElytraItem;

public class UselessElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
    public static final ResourceLocation USELESS_ELYTRA = UselessMod.rl("textures/entity/useless_elytra.png");
    public static final ResourceLocation SUPER_USELESS_ELYTRA = UselessMod.rl("textures/entity/super_useless_elytra.png");

    public UselessElytraLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public boolean shouldRender(ItemStack stack, T entity) {
        return stack.getItem() instanceof UselessElytraItem;
    }

    @Override
    public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
        return stack.getItem() == ItemInit.USELESS_ELYTRA.get() ? USELESS_ELYTRA :
                stack.getItem() == ItemInit.SUPER_USELESS_ELYTRA.get() ? SUPER_USELESS_ELYTRA :
                super.getElytraTexture(stack, entity);
    }
}
