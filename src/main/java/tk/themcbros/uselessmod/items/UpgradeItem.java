package tk.themcbros.uselessmod.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.machine.Upgrade;

import javax.annotation.Nullable;
import java.util.List;

public class UpgradeItem extends Item {
	
	private final Upgrade upgrade;

	public UpgradeItem(Properties properties, Upgrade upgrade) {
		super(properties);
		this.upgrade = upgrade;
	}
	
	public Upgrade getUpgrade() {
		return upgrade;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if(this.upgrade.hasTranslationKey())
			tooltip.add(new TranslationTextComponent(this.upgrade.getTranslationKey()));
	}
}
