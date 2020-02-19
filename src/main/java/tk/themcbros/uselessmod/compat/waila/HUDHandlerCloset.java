package tk.themcbros.uselessmod.compat.waila;

import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.tileentity.ClosetTileEntity;

import java.util.List;

public class HUDHandlerCloset implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerCloset INSTANCE = new HUDHandlerCloset();
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if(config.get(UselessPlugin.CONFIG_DISPLAY_CLOSET_MATERIAL) && accessor.getBlock() == ModBlocks.CLOSET) {
			CompoundNBT tag = accessor.getServerData();
			IClosetMaterial casingId = ClosetRegistry.CASINGS.get(tag.getString("casingId"));
			IClosetMaterial beddingId = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId"));
			tooltip.add(casingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
			tooltip.add(beddingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
		}
	}
	
	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
		if(tileEntity instanceof ClosetTileEntity) {
			ClosetTileEntity closet = (ClosetTileEntity) tileEntity;
			CompoundNBT closetTags = closet.write(new CompoundNBT());
			data.putString("casingId", closetTags.getString("casingId"));
			data.putString("beddingId", closetTags.getString("beddingId"));
		}
	}

}