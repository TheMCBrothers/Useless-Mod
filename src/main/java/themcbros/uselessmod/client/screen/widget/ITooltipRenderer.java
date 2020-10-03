package themcbros.uselessmod.client.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.IReorderingProcessor;

import java.util.List;

/**
 * @author TheMCBrothers
 */
public interface ITooltipRenderer {
    void renderTooltip(MatrixStack matrixStack, List<IReorderingProcessor> tooltip, int mouseX, int mouseY);
}
