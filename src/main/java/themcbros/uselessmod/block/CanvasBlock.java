package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.CanvasTileEntity;

import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class CanvasBlock extends Block {

    public CanvasBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.CANVAS.get().create();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        CompoundNBT tag = stack.getChildTag("BlockEntityTag");
        if (tag != null && tag.contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CanvasTileEntity) {
                ((CanvasTileEntity) tileEntity).setColor(tag.getInt("Color"));
            }
        }
    }

    // TODO: replace net.minecraft.item.IDyeableArmorItem with something else
    public static class CanvasBlockItem extends BlockItem implements IDyeableArmorItem {
        public CanvasBlockItem(Block blockIn, Properties builder) {
            super(blockIn, builder);
        }

        @Override
        public boolean hasColor(ItemStack stack) {
            return stack.getChildTag("BlockEntityTag") != null &&
                    stack.getOrCreateChildTag("BlockEntityTag").contains("Color", Constants.NBT.TAG_ANY_NUMERIC);
        }

        @Override
        public void removeColor(ItemStack stack) {
            stack.setTag(null);
        }

        @Override
        public void setColor(ItemStack stack, int color) {
            stack.getOrCreateChildTag("BlockEntityTag").putInt("Color", color);
        }

        @Override
        public int getColor(ItemStack stack) {
            return stack.getChildTag("BlockEntityTag") != null ? stack.getOrCreateChildTag("BlockEntityTag").getInt("Color") : -1;
        }
    }
}
