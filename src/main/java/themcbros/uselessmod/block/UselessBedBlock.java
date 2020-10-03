package themcbros.uselessmod.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.client.renderer.tilentity.ItemStackTileEntityRenderer;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.UselessBedTileEntity;

public class UselessBedBlock extends BedBlock implements IBlockItemProvider {

    private final DyeColor color;

    public UselessBedBlock(DyeColor color, Properties properties) {
        super(color, properties);
        this.color = color;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new UselessBedTileEntity(this.color);
    }

    @Override
    public BlockItem provideBlockItem(Block block, Item.Properties properties) {
        properties.setISTER(() -> () -> new ItemStackTileEntityRenderer(() -> TileEntityInit.USELESS_BED.get().create()));
        return new BlockItem(block, properties.maxStackSize(1));
    }

}
