package net.themcbrothers.uselessmod.client.gui.components;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.energy.EnergyProvider;
import net.themcbrothers.uselessmod.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class EnergyBar extends AbstractWidget {
    public static final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/energy_bar.png");

    private final EnergyProvider handler;
    private final TooltipRenderer tooltipRenderer;

    public EnergyBar(int x, int y, int width, int height, EnergyProvider handler, TooltipRenderer tooltipRenderer) {
        super(x, y, width, height, TextComponent.EMPTY);
        this.handler = handler;
        this.tooltipRenderer = tooltipRenderer;
    }

    @Override
    public void renderButton(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.blit(poseStack, this.x - 1, this.y - 1, 0, 0, this.width + 2, this.height + 2);
        int i = this.getScaledHeight();
        this.blit(poseStack, this.x, this.y + this.height - i, this.width + 2, 0, this.width, i);
    }

    @Override
    public void renderToolTip(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        List<Component> tooltip = Collections.singletonList(TextUtils.energyWithMax(this.handler.getEnergyStored(), this.handler.getMaxEnergyStored()));
        this.tooltipRenderer.renderTooltip(poseStack, Lists.transform(tooltip, Component::getVisualOrderText), mouseX, mouseY);
    }

    private int getScaledHeight() {
        float i = (float) this.handler.getEnergyStored();
        float j = (float) this.handler.getMaxEnergyStored();
        float h = (float) this.height;
        return i != 0 && j != 0 ? (int) (i / j * h) : 0;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {
    }
}
