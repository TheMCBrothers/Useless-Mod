package themcbros.uselessmod.color;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.item.PaintBrushItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemColorHandler implements IColorHandler, ICapabilityProvider {

    private final ItemStack container;
    private final LazyOptional<IColorHandler> holder = LazyOptional.of(() -> this);

    public ItemColorHandler(ItemStack container) {
        this.container = container;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityColor.COLOR.orEmpty(cap, holder);
    }

    @Override
    public int getColor() {
        CompoundNBT tag = this.container.getTag();
        if (tag != null && tag.contains("Color", Constants.NBT.TAG_ANY_NUMERIC))
            return tag.getInt("Color");
        return -1;
    }

    @Override
    public void setColor(int color) {
        this.container.getOrCreateTag().putInt("Color", color);
        this.container.getOrCreateTag().putInt("Uses", PaintBrushItem.MAX_USES);
    }
}
