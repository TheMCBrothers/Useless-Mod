package themcbros.uselessmod.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.ItemInit;

import javax.annotation.Nullable;
import java.util.List;

public class UselessShieldItem extends ShieldItem {

    private static final RenderMaterial USELESS_SHIELD_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, UselessMod.rl("entity/shield_useless"));
    private static final RenderMaterial SUPER_USELESS_SHIELD_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, UselessMod.rl("entity/shield_super_useless"));

    public UselessShieldItem(Properties properties) {
        super(properties.setISTER(() -> () -> new ItemStackTileEntityRenderer() {
            private final ShieldModel shieldModel = new ShieldModel();
            @Override
            public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
                matrixStack.push();
                matrixStack.scale(1.0F, -1.0F, -1.0F);
                boolean flag = stack.getItem() == ItemInit.USELESS_SHIELD.get();
                RenderMaterial material = flag ? USELESS_SHIELD_MATERIAL : SUPER_USELESS_SHIELD_MATERIAL;
                IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(buffer, this.shieldModel.getRenderType(material.getAtlasLocation()), false, stack.hasEffect()));
                this.shieldModel.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
                this.shieldModel.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStack.pop();
            }
        }));
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag tooltipFlag) {
    }

    @Override
    public String getTranslationKey(ItemStack itemStack) {
        return this.getTranslationKey();
    }

}
