package tk.themcbros.uselessmod.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class DescriptionItem extends Item {

    public DescriptionItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        String toTranslate = this.getTranslationKey() + ".desc";
        if (I18n.hasKey(toTranslate)) {
            ITextComponent desc = new TranslationTextComponent("tooltip.uselessmod.press_shift");
            int state = GLFW.glfwGetKey(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT);
            if (state == GLFW.GLFW_PRESS)
                desc = new TranslationTextComponent(toTranslate);
            tooltip.add(desc);
        }
    }
}
