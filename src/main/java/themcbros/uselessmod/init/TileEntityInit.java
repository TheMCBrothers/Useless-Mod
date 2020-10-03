package themcbros.uselessmod.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.tileentity.*;

public class TileEntityInit {

    public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, UselessMod.MOD_ID);

    public static final RegistryObject<TileEntityType<UselessBedTileEntity>> USELESS_BED = REGISTER.register("useless_bed",
            () -> TileEntityType.Builder.create(UselessBedTileEntity::new, BlockInit.USELESS_BED.get()).build(null));
    public static final RegistryObject<TileEntityType<UselessSignTileEntity>> USELESS_SIGN = REGISTER.register("useless_sign",
            () -> TileEntityType.Builder.create(UselessSignTileEntity::new, BlockInit.USELESS_SIGN.get(), BlockInit.USELESS_WALL_SIGN.get()).build(null));

    public static final RegistryObject<TileEntityType<UselessGeneratorTileEntity>> USELESS_GENERATOR = REGISTER.register("useless_generator",
            () -> TileEntityType.Builder.create(UselessGeneratorTileEntity::new, BlockInit.USELESS_GENERATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<CoffeeMachineTileEntity>> COFFEE_MACHINE = REGISTER.register("coffee_machine",
            () -> TileEntityType.Builder.create(CoffeeMachineTileEntity::new, BlockInit.COFFEE_MACHINE.get()).build(null));
    public static final RegistryObject<TileEntityType<MachineSupplierTileEntity>> MACHINE_SUPPLIER = REGISTER.register("machine_supplier",
            () -> TileEntityType.Builder.create(MachineSupplierTileEntity::new, BlockInit.COFFEE_MACHINE_SUPPLIER.get()).build(null));
    public static final RegistryObject<TileEntityType<CoffeeCupTileEntity>> COFFEE_CUP = REGISTER.register("coffee_cup",
            () -> TileEntityType.Builder.create(CoffeeCupTileEntity::new, BlockInit.CUP.get(), BlockInit.COFFEE_CUP.get()).build(null));

    public static final RegistryObject<TileEntityType<WallClosetTileEntity>> WALL_CLOSET = REGISTER.register("wall_closet",
            () -> TileEntityType.Builder.create(WallClosetTileEntity::new, BlockInit.USELESS_GENERATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<UselessChestTileEntity>> USELESS_CHEST = REGISTER.register("useless_chest",
            () -> TileEntityType.Builder.create(UselessChestTileEntity::new, BlockInit.USELESS_CHEST.get()).build(null));

    public static final RegistryObject<TileEntityType<PaintBucketTileEntity>> PAINT_BUCKET = REGISTER.register("paint_bucket",
            () -> TileEntityType.Builder.create(PaintBucketTileEntity::new, BlockInit.PAINT_BUCKET.get()).build(null));
    public static final RegistryObject<TileEntityType<CanvasTileEntity>> CANVAS = REGISTER.register("canvas",
            () -> TileEntityType.Builder.create(CanvasTileEntity::new, BlockInit.CANVAS.get()).build(null));

    public static final RegistryObject<TileEntityType<UselessSkullTileEntity>> USELESS_SKULL = REGISTER.register("useless_skull",
            () -> TileEntityType.Builder.create(UselessSkullTileEntity::new).build(null));

}
