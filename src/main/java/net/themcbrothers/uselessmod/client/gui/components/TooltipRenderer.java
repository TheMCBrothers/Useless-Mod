package net.themcbrothers.uselessmod.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public interface TooltipRenderer {
    void renderTooltip(PoseStack poseStack, List<FormattedCharSequence> tooltip, int mouseX, int mouseY);
}
