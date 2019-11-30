package tk.themcbros.uselessmod.compat.top;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.tileentity.MachineTileEntity;

public class MachineProbeProvider implements IProbeInfoProvider {

    static final MachineProbeProvider INSTANCE = new MachineProbeProvider();

    @Override
    public String getID() {
        return UselessMod.MOD_ID + ":machine_info";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData data) {
        TileEntity tileEntity = world.getTileEntity(data.getPos());
        if (tileEntity instanceof MachineTileEntity) {
            MachineTileEntity machineTileEntity = (MachineTileEntity) tileEntity;
            probeInfo.text("Tier: " + machineTileEntity.getMachineTier().name());
        }
    }
}
