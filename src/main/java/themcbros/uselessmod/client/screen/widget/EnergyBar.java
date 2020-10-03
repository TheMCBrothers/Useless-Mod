package themcbros.uselessmod.client.screen.widget;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.energy.IEnergyProvider;
import themcbros.uselessmod.helpers.TextUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author TheMCBrothers
 */
public class EnergyBar extends Widget {
    public static final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/energy_bar.png");

    private final IEnergyProvider provider;
    private final ITooltipRenderer tooltipRenderer;

    public EnergyBar(int x, int y, int width, int height, IEnergyProvider provider, ITooltipRenderer tooltipRenderer) {
        super(x, y, width, height, StringTextComponent.EMPTY);
        this.provider = provider;
        this.tooltipRenderer = tooltipRenderer;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            Minecraft minecraft = Minecraft.getInstance();
            minecraft.getTextureManager().bindTexture(TEXTURE);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.blit(matrixStack, this.x - 1, this.y - 1, 0, 0, this.width + 2, this.height + 2);
            int i = this.getScaledHeight();
            this.blit(matrixStack, this.x, this.y + this.height - i, this.width + 2, 0, this.width, i);
        }
    }

    private int getScaledHeight() {
        float i = (float) this.provider.getEnergyStored();
        float j = (float) this.provider.getMaxEnergyStored();
        float h = (float) this.height;
        return i != 0 && j != 0 ? (int) (i / j * h) : 0;
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        List<ITextComponent> tooltip = Collections.singletonList(TextUtils.energyWithMax(this.provider.getEnergyStored(), this.provider.getMaxEnergyStored()));
        this.tooltipRenderer.renderTooltip(matrixStack, Lists.transform(tooltip, ITextComponent::func_241878_f), mouseX, mouseY);
    }
}
