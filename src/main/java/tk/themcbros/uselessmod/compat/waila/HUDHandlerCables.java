package tk.themcbros.uselessmod.compat.waila;

import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;
import tk.themcbros.uselessmod.tileentity.FluidPipeTileEntity;

import java.util.List;

public class HUDHandlerCables implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerCables INSTANCE = new HUDHandlerCables();

	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if (!config.get(UselessPlugin.CONFIG_CABLES))
			return;

		if(accessor.getServerData().contains("Info", Constants.NBT.TAG_STRING)) {
			tooltip.add(new StringTextComponent(accessor.getServerData().getString("Info")));
		}
	}

	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity blockEntity) {
		if (blockEntity instanceof EnergyCableTileEntity)
			data.putString("Info", ((EnergyCableTileEntity) blockEntity).getNetworkInfos());
		if (blockEntity instanceof FluidPipeTileEntity)
			data.putString("Info", ((FluidPipeTileEntity) blockEntity).getNetworkInfos());
	}
}
