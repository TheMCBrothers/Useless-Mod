package tk.themcbros.uselessmod.items;

import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.machine.Upgrade;
import tk.themcbros.uselessmod.tileentity.MachineTileEntity;

public class TierUpgradeItem extends UpgradeItem {

	private final MachineTier tier;

	public TierUpgradeItem(Properties properties, Upgrade upgrade, MachineTier tier) {
		super(properties, upgrade);
		this.tier = tier;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		final World world = context.getWorld();
		final BlockPos pos = context.getPos();
		final TileEntity tileEntity = world.getTileEntity(pos);

		if (tileEntity instanceof MachineTileEntity) {
			MachineTileEntity machine = (MachineTileEntity) tileEntity;
			MachineTier machineTier = machine.getMachineTier();
			if (machineTier.getIndex() == tier.getIndex() - 1) {
				machine.setMachineTier(tier);
				if (context.getPlayer() != null) {
					ITextComponent text = new StringTextComponent("Upgraded machine to tier ").applyTextStyle(TextFormatting.GREEN);
					text = text.appendSibling(new StringTextComponent(tier.getName()).applyTextStyle(TextFormatting.DARK_GREEN));
					context.getPlayer().sendStatusMessage(text, true);
					context.getItem().shrink(1);
				}
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
}
