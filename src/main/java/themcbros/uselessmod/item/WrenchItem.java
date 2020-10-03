package themcbros.uselessmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import themcbros.uselessmod.UselessMod;

public class WrenchItem extends Item {
    public WrenchItem() {
        super(new Properties().group(UselessMod.GROUP).maxStackSize(1));
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IWorldReader world, BlockPos pos, PlayerEntity player) {
        return true;
    }
}
