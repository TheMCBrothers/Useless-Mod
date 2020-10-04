package themcbros.uselessmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import themcbros.uselessmod.client.renderer.tilentity.ISTERProvider;

import javax.annotation.Nullable;
import java.util.List;

public class UselessShieldItem extends ShieldItem {

    public UselessShieldItem(Properties properties) {
        super(properties.setISTER(ISTERProvider::useless));
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
