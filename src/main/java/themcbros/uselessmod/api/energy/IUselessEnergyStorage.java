package themcbros.uselessmod.api.energy;

import net.minecraftforge.energy.IEnergyStorage;

/**
 * @author TheMCBrothers
 */
public interface IUselessEnergyStorage extends IEnergyStorage {

    /**
     * @param extract TRUE or FALSE
     * @return Transfer for extraction or insertion
     */
    int getMaxTransfer(boolean extract);

}
