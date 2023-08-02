package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.UselessFluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class BucketWithPaintItem extends BucketItem {
    public BucketWithPaintItem(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> hoverText, TooltipFlag tooltipFlag) {
        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC)) {
            int color = tag.getInt("Color");
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            hoverText.add(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new PaintFluidBucketWrapper(stack);
    }

    private static class PaintFluidBucketWrapper extends FluidBucketWrapper {
        public PaintFluidBucketWrapper(@NotNull ItemStack container) {
            super(container);
        }

        @Override
        public @NotNull FluidStack getFluid() {
            FluidStack fluidStack = new FluidStack(UselessFluids.PAINT.get(), FluidType.BUCKET_VOLUME);
            CompoundTag tag = this.container.getTag();

            if (tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC)) {
                fluidStack.getOrCreateTag().putInt("Color", tag.getInt("Color"));
            }

            return fluidStack;
        }
    }
}
