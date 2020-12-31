package themcbros.uselessmod.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.wires.IWireCoil;
import blusunrize.immersiveengineering.api.wires.WireType;
import blusunrize.immersiveengineering.api.wires.utils.WirecoilUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import themcbros.uselessmod.UselessMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class WireCoilItem extends Item implements IWireCoil {
    @Nonnull
    private final WireType wireType;

    public WireCoilItem(@Nonnull WireType wireType, Properties properties) {
        super(properties);
        this.setRegistryName(UselessMod.rl("wirecoil_" + wireType.getUniqueName().toLowerCase(Locale.ROOT)));
        this.wireType = wireType;
    }

    @Override
    public WireType getWireType(ItemStack itemStack) {
        return this.wireType;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (WirecoilUtils.hasWireLink(stack)) {
            WirecoilUtils.WireLink link = WirecoilUtils.WireLink.readFromItem(stack);
            tooltip.add(new TranslationTextComponent("desc.immersiveengineering.info.attachedToDim",
                    link.cp.getX(), link.cp.getY(), link.cp.getZ(), link.dimension));
        }
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return WirecoilUtils.doCoilUse(this, context.getPlayer(), context.getWorld(), context.getPos(),
                context.getHand(), context.getFace(),
                (float) context.getHitVec().x, (float) context.getHitVec().y, (float) context.getHitVec().z);
    }
}
