package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.core.UselessFluids;
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
        Integer color = stack.get(UselessDataComponents.COLOR.get());

        if (color != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            hoverText.add(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }

    public static class PaintFluidBucketWrapper extends FluidBucketWrapper {
        public PaintFluidBucketWrapper(@NotNull ItemStack container) {
            super(container);
        }

        @Override
        public @NotNull FluidStack getFluid() {
            FluidStack fluidStack = new FluidStack(UselessFluids.PAINT.get(), FluidType.BUCKET_VOLUME);
            fluidStack.applyComponents(this.container.getComponents());
            return fluidStack;
        }
    }
}
