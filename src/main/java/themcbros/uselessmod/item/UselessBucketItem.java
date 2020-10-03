package themcbros.uselessmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.helpers.TextUtils;
import themcbros.uselessmod.util.Styles;

import javax.annotation.Nullable;
import java.util.List;

// TODO: implement bucket behavior
public class UselessBucketItem extends Item {

    private final int capacity;

    public UselessBucketItem(final int capacity, Properties properties) {
        super(properties);
        this.capacity = capacity;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(this.getEmptyBucket().copy());
            ForgeRegistries.FLUIDS.getValues().stream().filter(fluid -> fluid.isSource(fluid.getDefaultState()))
                    .forEach(fluid -> items.add(this.setFluid(this.getEmptyBucket().copy(), new FluidStack(fluid, this.capacity))));
        }
    }

    public ItemStack getEmptyBucket() {
        return new ItemStack(this);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return this.hasFluid(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        // this is wrong
        if (this.hasFluid(stack)) {
            FluidStack fluidStack = this.getFluid(stack);
            double stored = fluidStack.getAmount() - this.capacity + 1;
            double max = this.capacity + 1;
            return stored / max;
        }
        return 0.0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        FluidStack fluid = this.getFluid(stack);
        return fluid.getFluid().getAttributes().getColor(fluid);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return this.hasFluid(stack) || super.hasContainerItem(stack);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.hasFluid(itemStack) ? this.getEmptyBucket().copy() : super.getContainerItem(itemStack);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (this.hasFluid(stack))
            return this.getTranslationKey() + ".filled";
        return this.getTranslationKey();
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return this.hasFluid(stack)
                ? new TranslationTextComponent(this.getTranslationKey(stack), this.getFluid(stack).getDisplayName().getString())
                : super.getDisplayName(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (this.hasFluid(stack)) {
            FluidStack fluidStack = this.getFluid(stack);
            tooltip.add(TextUtils.fluidWithMax(fluidStack.getAmount(), this.capacity).setStyle(Styles.MODE_FLUID));
        }
    }

    public boolean hasFluid(ItemStack container) {
        return !this.getFluid(container).isEmpty();
    }

    public ItemStack setFluid(ItemStack container, FluidStack fluid) {
        CompoundNBT fluidTag = new CompoundNBT();
        fluid.writeToNBT(fluidTag);
        container.getOrCreateTag().put(FluidHandlerItemStack.FLUID_NBT_KEY, fluidTag);
        return container;
    }

    public FluidStack getFluid(ItemStack container) {
        CompoundNBT tagCompound = container.getTag();
        if (tagCompound == null || !tagCompound.contains(FluidHandlerItemStack.FLUID_NBT_KEY)) {
            return FluidStack.EMPTY;
        }
        return FluidStack.loadFluidStackFromNBT(tagCompound.getCompound(FluidHandlerItemStack.FLUID_NBT_KEY));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new FluidHandlerItemStack(stack, this.capacity);
    }
}
