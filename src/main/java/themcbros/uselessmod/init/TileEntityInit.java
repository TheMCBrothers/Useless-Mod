package themcbros.uselessmod.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.registration.deferred.TileEntityTypeDeferredRegister;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.tileentity.*;

public class TileEntityInit {

    public static final TileEntityTypeDeferredRegister REGISTER = new TileEntityTypeDeferredRegister(UselessMod.MOD_ID);

    public static final RegistryObject<TileEntityType<UselessBedTileEntity>> USELESS_BED = REGISTER.register("useless_bed",
            UselessBedTileEntity::new, BlockInit.USELESS_BED);
    public static final RegistryObject<TileEntityType<UselessSignTileEntity>> USELESS_SIGN = REGISTER.register("useless_sign",
            UselessSignTileEntity::new, blockBuilder -> blockBuilder.add(BlockInit.USELESS_SIGN.get(), BlockInit.USELESS_WALL_SIGN.get()));

    public static final RegistryObject<TileEntityType<UselessGeneratorTileEntity>> USELESS_GENERATOR = REGISTER.register("useless_generator",
            UselessGeneratorTileEntity::new, BlockInit.USELESS_GENERATOR);
    public static final RegistryObject<TileEntityType<CoffeeMachineTileEntity>> COFFEE_MACHINE = REGISTER.register("coffee_machine",
            CoffeeMachineTileEntity::new, BlockInit.COFFEE_MACHINE);
    public static final RegistryObject<TileEntityType<MachineSupplierTileEntity>> MACHINE_SUPPLIER = REGISTER.register("machine_supplier",
            MachineSupplierTileEntity::new, BlockInit.COFFEE_MACHINE_SUPPLIER);
    public static final RegistryObject<TileEntityType<CoffeeCupTileEntity>> COFFEE_CUP = REGISTER.register("coffee_cup",
            CoffeeCupTileEntity::new, blockBuilder -> blockBuilder.add(BlockInit.CUP.get(), BlockInit.COFFEE_CUP.get()));

    public static final RegistryObject<TileEntityType<WallClosetTileEntity>> WALL_CLOSET = REGISTER.register("wall_closet",
            WallClosetTileEntity::new, BlockInit.WALL_CLOSET);
    public static final RegistryObject<TileEntityType<UselessChestTileEntity>> USELESS_CHEST = REGISTER.register("useless_chest",
            UselessChestTileEntity::new, BlockInit.USELESS_CHEST);

    public static final RegistryObject<TileEntityType<PaintBucketTileEntity>> PAINT_BUCKET = REGISTER.register("paint_bucket",
            PaintBucketTileEntity::new, BlockInit.PAINT_BUCKET);
    public static final RegistryObject<TileEntityType<CanvasTileEntity>> CANVAS = REGISTER.register("canvas",
            CanvasTileEntity::new, BlockInit.CANVAS);

    public static final RegistryObject<TileEntityType<UselessSkullTileEntity>> USELESS_SKULL = REGISTER.register("useless_skull",
            UselessSkullTileEntity::new, blockBuilder ->
                    blockBuilder.add(BlockInit.USELESS_SKELETON_SKULL.get(), BlockInit.USELESS_SKELETON_WALL_SKULL.get()));

}
