package themcbros.uselessmod.item;

import net.minecraft.item.BlockItem;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.BlockInit;

public class CupBlockItem extends BlockItem {
    public CupBlockItem() {
        super(BlockInit.CUP.get(), new Properties().group(UselessMod.GROUP));
    }

    @Override
    public String getTranslationKey() {
        return this.getDefaultTranslationKey();
    }
}
