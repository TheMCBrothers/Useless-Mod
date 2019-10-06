package tk.themcbros.uselessmod.compat.waila;

import java.util.List;
import java.util.Locale;

import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.helper.TextUtils;
import tk.themcbros.uselessmod.tileentity.MachineTileEntity;

public class HUDHandlerEnergy implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerEnergy INSTANCE = new HUDHandlerEnergy();

	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if (!config.get(UselessPlugin.CONFIG_DISPLAY_ENERGY))
			return;
		
		tooltip.add(new StringTextComponent("Tier: " + accessor.getServerData().getString("MachineTier").toUpperCase(Locale.ROOT)));
		
		int i = accessor.getServerData().getInt("EnergyStored");
		int j = accessor.getServerData().getInt("MaxEnergyStored");
		
		if(!(i == 0 && j == 0))
			tooltip.add(TextUtils.energyWithMax(i, j));
		
		if(accessor.getServerData().contains("FluidName", Constants.NBT.TAG_STRING)) {
			String fluidName = accessor.getServerData().getString("FluidName");
			String fluid = new TranslationTextComponent(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName)).getAttributes().getTranslationKey()).getFormattedText();
			i = accessor.getServerData().getInt("FluidAmount");
			j = accessor.getServerData().getInt("MaxFluidAmount");
			tooltip.add(TextUtils.fluidWithMax(fluid, i, j));
		}
	}

	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity blockEntity) {
		data.putString("MachineTier", ((MachineTileEntity) blockEntity).getMachineTier().getName());
		blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyHandler -> {
			data.putInt("EnergyStored", energyHandler.getEnergyStored());
			data.putInt("MaxEnergyStored", energyHandler.getMaxEnergyStored());
		});
		
		blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(handler -> {
			data.putString("FluidName", handler.getFluidInTank(0).getFluid().getRegistryName().toString());
			data.putInt("FluidAmount", handler.getFluidInTank(0).getAmount());
			data.putInt("MaxFluidAmount", handler.getTankCapacity(0));
		});
	}
}
