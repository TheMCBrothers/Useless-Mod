package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.ItemAccessResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.core.UselessFluids;

import java.util.Objects;
import java.util.function.Consumer;

public class BucketWithPaintItem extends BucketItem {
    public BucketWithPaintItem(Fluid content, Properties properties) {
        super(content, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        Integer color = stack.get(UselessDataComponents.COLOR.get());

        if (color != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            tooltipAdder.accept(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }

    public static class PaintFluidBucketWrapper extends ItemAccessResourceHandler<FluidResource> {
        public PaintFluidBucketWrapper(ItemAccess itemAccess) {
            super(itemAccess, 1);
        }

        @Override
        protected FluidResource getResourceFrom(ItemResource accessResource, int index) {
            return FluidResource.of(UselessFluids.PAINT.get(), accessResource.getComponentsPatch());
        }

        @Override
        protected int getAmountFrom(ItemResource accessResource, int index) {
            var resource = getResourceFrom(accessResource, index);
            return resource.isEmpty() ? 0 : FluidType.BUCKET_VOLUME;
        }

        @Override
        protected ItemResource update(ItemResource accessResource, int index, FluidResource newResource, int newAmount) {
            if (newAmount == 0) {
                return ItemResource.of(Items.BUCKET);
            } else if (newAmount != FluidType.BUCKET_VOLUME) {
                return ItemResource.EMPTY;
            } else {
                var newStack = newResource.toStack(newAmount);
                return ItemResource.of(newStack.getFluidType().getBucket(newStack));
            }
        }

        @Override
        protected int getCapacity(int index, FluidResource resource) {
            Objects.checkIndex(index, size());
            return FluidType.BUCKET_VOLUME;
        }
    }
}
