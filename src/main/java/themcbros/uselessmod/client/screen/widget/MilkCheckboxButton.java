package themcbros.uselessmod.client.screen.widget;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import themcbros.uselessmod.UselessMod;

import java.util.Collections;
import java.util.List;

public class MilkCheckboxButton extends AbstractButton {

    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/checkbox.png");
    private boolean checked;
    private final ITooltipRenderer tooltipRenderer;
    private final IPressable onPress;

    public MilkCheckboxButton(int x, int y, ITextComponent title, boolean checked, ITooltipRenderer tooltipRenderer, IPressable onPress) {
        super(x, y, 10, 10, title);
        this.checked = checked;
        this.tooltipRenderer = tooltipRenderer;
        this.onPress = onPress;
    }

    public void onPress() {
        this.checked = !this.checked;
        this.onPress.onPress(this);
    }

    public boolean isChecked() {
        return checked;
    }

    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(TEXTURE);
        RenderSystem.enableDepthTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        blit(matrixStack, this.x, this.y, this.isFocused() ? 10.0F : 0.0F, this.checked ? 10.0F : 0.0F, 10, 10, 32, 32);
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        List<ITextComponent> tooltip = Collections.singletonList(this.getMessage());
        this.tooltipRenderer.renderTooltip(matrixStack, Lists.transform(tooltip, ITextComponent::func_241878_f), mouseX, mouseY);
    }

    public interface IPressable {
        void onPress(MilkCheckboxButton button);
    }

}
