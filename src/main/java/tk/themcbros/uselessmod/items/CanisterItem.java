package tk.themcbros.uselessmod.items;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.caps.CapabilityProviderEnergy;
import tk.themcbros.uselessmod.caps.ItemFluidHandler;
import tk.themcbros.uselessmod.fluids.IFluidContainerItem;
import tk.themcbros.uselessmod.helper.NBTHelper;
import tk.themcbros.uselessmod.helper.TextUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class CanisterItem extends Item implements IFluidContainerItem {

	private static final String TAG_FLUID = "Fluid";

	public CanisterItem(Properties properties) {
		super(properties);
		this.addPropertyOverride(UselessMod.getId("fluid_level"), (stack, world, entity) -> getFluid(stack).getAmount());
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return this.getFluid(stack).isEmpty() ? 64 : 1;
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName(@Nonnull ItemStack stack) {
		FluidStack fluid = this.getFluid(stack);
		ITextComponent fluidText = fluid.isEmpty() ? TextUtils.translate("misc", "empty") : fluid.getDisplayName();
		return new TranslationTextComponent(this.getTranslationKey(), fluidText);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new CapabilityProviderEnergy<>(new ItemFluidHandler(this, stack), CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
	}

	private boolean canFillFluidType(@Nonnull FluidStack fluid) {
		if (fluid.getFluid() == Fluids.WATER || fluid.getFluid() == Fluids.LAVA || fluid.getFluid().getRegistryName().equals(new ResourceLocation("milk"))) {
			return true;
		}
		return fluid.getFluid().getAttributes().getBucket(fluid) != null;
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		final String key = NBTHelper.getString(container, TAG_FLUID);
		final Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(key));
		if (fluid == null) return FluidStack.EMPTY;
		return new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME);
	}

	private void setFluid(ItemStack container, @Nonnull FluidStack fluidStack) {
		NBTHelper.setString(container, TAG_FLUID, fluidStack.getFluid().getRegistryName().toString());
	}

	@Override
	public int getCapacity(ItemStack container) {
		return FluidAttributes.BUCKET_VOLUME;
	}

	@Override
	public int fill(@Nonnull ItemStack container, FluidStack resource, IFluidHandler.FluidAction action) {
		if (container.getCount() != 1 || resource.getAmount() < FluidAttributes.BUCKET_VOLUME || !getFluid(container).isEmpty() || !canFillFluidType(resource)) {
			return 0;
		}

		if (action.execute()) {
			setFluid(container, resource);
		}

		return FluidAttributes.BUCKET_VOLUME;
	}

	@Override
	public FluidStack drain(@Nonnull ItemStack container, FluidStack resource, IFluidHandler.FluidAction action) {
		if (container.getCount() != 1 || resource.getAmount() < FluidAttributes.BUCKET_VOLUME)
		{
			return FluidStack.EMPTY;
		}

		FluidStack fluidStack = getFluid(container);
		if (!fluidStack.isEmpty() && fluidStack.isFluidEqual(resource))
		{
			if (action.execute())
			{
				setFluid(container, FluidStack.EMPTY);
			}
			return fluidStack;
		}

		return FluidStack.EMPTY;
	}

	@Override
	public FluidStack drain(@Nonnull ItemStack container, int maxDrain, IFluidHandler.FluidAction action) {
		if (container.getCount() != 1 || maxDrain < FluidAttributes.BUCKET_VOLUME) {
			return FluidStack.EMPTY;
		}

		FluidStack fluidStack = getFluid(container);
		if (!fluidStack.isEmpty()) {
			if (action.execute()) {
				setFluid(container, FluidStack.EMPTY);
			}
			return fluidStack;
		}

		return FluidStack.EMPTY;
	}

	@Nonnull
	private ItemStack getStack(@Nullable Fluid fluid) {
		ItemStack result = new ItemStack(this);
		if (fluid != null) {
			ResourceLocation fluidId = Objects.requireNonNull(fluid.getRegistryName());
			NBTHelper.setString(result, TAG_FLUID, fluidId.toString());
		}
		return result;
	}

	@Override
	public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
		if (isInGroup(group)) {
			items.add(getStack(null));
			ForgeRegistries.FLUIDS.getValues().stream()
					.filter(f -> f.isSource(f.getDefaultState()))
					.map(this::getStack)
					.forEach(items::add);
		}
	}
}
