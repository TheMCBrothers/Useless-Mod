package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.themcbrothers.uselessmod.world.level.block.entity.*;

import java.util.function.Supplier;

import static net.themcbrothers.uselessmod.init.Registration.BLOCK_ENTITIES;

public final class ModBlockEntityTypes {
    static void register() {
    }

    public static final Supplier<BlockEntityType<MachineSupplierBlockEntity>> MACHINE_SUPPLIER =
            BLOCK_ENTITIES.register("machine_supplier", MachineSupplierBlockEntity::new, ModBlocks.MACHINE_SUPPLIER);
    public static final Supplier<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine", CoffeeMachineBlockEntity::new, ModBlocks.COFFEE_MACHINE);
    public static final Supplier<BlockEntityType<CupBlockEntity>> CUP =
            BLOCK_ENTITIES.register("cup", CupBlockEntity::new, ModBlocks.CUP_COFFEE);
    public static final Supplier<BlockEntityType<PaintedWoolBlockEntity>> PAINTED_WOOL =
            BLOCK_ENTITIES.register("painted_wool", PaintedWoolBlockEntity::new, ModBlocks.PAINTED_WOOL);
    public static final Supplier<BlockEntityType<PaintBucketBlockEntity>> PAINT_BUCKET =
            BLOCK_ENTITIES.register("paint_bucket", PaintBucketBlockEntity::new, ModBlocks.PAINT_BUCKET);
    public static final Supplier<BlockEntityType<UselessBedBlockEntity>> BED =
            BLOCK_ENTITIES.register("bed", UselessBedBlockEntity::new, ModBlocks.USELESS_BED);
    public static final Supplier<BlockEntityType<WallClosetBlockEntity>> WALL_CLOSET =
            BLOCK_ENTITIES.register("wall_closet", WallClosetBlockEntity::new, ModBlocks.WALL_CLOSET);
    public static final Supplier<BlockEntityType<UselessSkullBlockEntity>> SKULL =
            BLOCK_ENTITIES.register("skull", UselessSkullBlockEntity::new,
                    blocks -> blocks.add(ModBlocks.USELESS_SKELETON_SKULL.get(), ModBlocks.USELESS_SKELETON_WALL_SKULL.get()));
    public static final Supplier<BlockEntityType<UselessSignBlockEntity>> SIGN =
            BLOCK_ENTITIES.register("sign", UselessSignBlockEntity::new,
                    blocks -> blocks.add(ModBlocks.USELESS_OAK_WALL_SIGN.get(), ModBlocks.USELESS_OAK_SIGN.get()));
    public static final Supplier<BlockEntityType<UselessHangingSignBlockEntity>> HANGING_SIGN =
            BLOCK_ENTITIES.register("hanging_sign", UselessHangingSignBlockEntity::new,
                    blocks -> blocks.add(ModBlocks.USELESS_OAK_WALL_HANGING_SIGN.get(), ModBlocks.USELESS_OAK_HANGING_SIGN.get()));
    public static final Supplier<BlockEntityType<LightSwitchBlockEntity>> LIGHT_SWITCH =
            BLOCK_ENTITIES.register("light_switch", LightSwitchBlockEntity::new,
                    blocks -> blocks.add(ModBlocks.LIGHT_SWITCH.get(), ModBlocks.LIGHT_SWITCH_BLOCK.get()));
}
