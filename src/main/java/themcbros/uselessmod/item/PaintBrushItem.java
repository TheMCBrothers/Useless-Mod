package themcbros.uselessmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.color.DummyColorHandler;
import themcbros.uselessmod.color.ItemColorHandler;
import themcbros.uselessmod.util.Styles;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author TheMCBrothers
 */
public class PaintBrushItem extends Item {

    public static final int MAX_USES = 10;

    public PaintBrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        ItemStack stack = context.getItem();
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            return tileEntity.getCapability(CapabilityColor.COLOR, context.getFace()).map(colorHandler -> colorHandler.onClick(stack))
                    .orElse(ActionResultType.FAIL);
        }
        return ActionResultType.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("Uses", Constants.NBT.TAG_ANY_NUMERIC)) {
            tooltip.add(new StringTextComponent("Uses: " + tag.getInt("Uses")).setStyle(Styles.TOOLTIP));
        }
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            stack.getOrCreateTag().putInt("Color", 0x00FFFF);
            stack.getOrCreateTag().putInt("Uses", MAX_USES);
            items.add(stack);
        }
    }

    public int getUses(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("Uses", Constants.NBT.TAG_ANY_NUMERIC))
            return tag.getInt("Uses");
        return 0;
    }

    public void setUses(ItemStack stack, int uses) {
        stack.getOrCreateTag().putInt("Uses", uses);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getCapability(CapabilityColor.COLOR).map(IColorHandler::hasColor).orElse(false);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return stack.getCapability(CapabilityColor.COLOR).map(IColorHandler::getColor).orElse(-1);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        double d = (double) this.getUses(stack) / MAX_USES;
        return 1.0d - d;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ItemColorHandler(stack);
    }

    public void consumeColor(ItemStack stack) {
        int i = this.getUses(stack);
        this.setUses(stack, i - 1);

        if (this.getUses(stack) <= 0) {
            stack.setTag(null);
        }
    }
}
