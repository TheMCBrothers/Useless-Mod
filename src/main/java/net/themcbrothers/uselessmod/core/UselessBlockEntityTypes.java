package net.themcbrothers.uselessmod.core;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.themcbrothers.uselessmod.world.level.block.entity.*;

import java.util.function.Supplier;

import static net.themcbrothers.uselessmod.core.Registration.BLOCK_ENTITIES;

public final class UselessBlockEntityTypes {
    static void register() {
    }

    public static final Supplier<BlockEntityType<MachineSupplierBlockEntity>> MACHINE_SUPPLIER =
            BLOCK_ENTITIES.register("machine_supplier", MachineSupplierBlockEntity::new, UselessBlocks.MACHINE_SUPPLIER);
    public static final Supplier<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine", CoffeeMachineBlockEntity::new, UselessBlocks.COFFEE_MACHINE);
    public static final Supplier<BlockEntityType<CupBlockEntity>> CUP =
            BLOCK_ENTITIES.register("cup", CupBlockEntity::new, UselessBlocks.CUP_COFFEE);
    public static final Supplier<BlockEntityType<PaintedWoolBlockEntity>> PAINTED_WOOL =
            BLOCK_ENTITIES.register("painted_wool", PaintedWoolBlockEntity::new, UselessBlocks.PAINTED_WOOL);
    public static final Supplier<BlockEntityType<PaintBucketBlockEntity>> PAINT_BUCKET =
            BLOCK_ENTITIES.register("paint_bucket", PaintBucketBlockEntity::new, UselessBlocks.PAINT_BUCKET);
    public static final Supplier<BlockEntityType<UselessBedBlockEntity>> BED =
            BLOCK_ENTITIES.register("bed", UselessBedBlockEntity::new, UselessBlocks.USELESS_BED);
    public static final Supplier<BlockEntityType<WallClosetBlockEntity>> WALL_CLOSET =
            BLOCK_ENTITIES.register("wall_closet", WallClosetBlockEntity::new, UselessBlocks.WALL_CLOSET);
    public static final Supplier<BlockEntityType<UselessSkullBlockEntity>> SKULL =
            BLOCK_ENTITIES.register("skull", UselessSkullBlockEntity::new,
                    blocks -> blocks.add(UselessBlocks.USELESS_SKELETON_SKULL.get(), UselessBlocks.USELESS_SKELETON_WALL_SKULL.get()));
    public static final Supplier<BlockEntityType<UselessSignBlockEntity>> SIGN =
            BLOCK_ENTITIES.register("sign", UselessSignBlockEntity::new,
                    blocks -> blocks.add(UselessBlocks.USELESS_OAK_WALL_SIGN.get(), UselessBlocks.USELESS_OAK_SIGN.get()));
    public static final Supplier<BlockEntityType<UselessHangingSignBlockEntity>> HANGING_SIGN =
            BLOCK_ENTITIES.register("hanging_sign", UselessHangingSignBlockEntity::new,
                    blocks -> blocks.add(UselessBlocks.USELESS_OAK_WALL_HANGING_SIGN.get(), UselessBlocks.USELESS_OAK_HANGING_SIGN.get()));
    public static final Supplier<BlockEntityType<LightSwitchBlockEntity>> LIGHT_SWITCH =
            BLOCK_ENTITIES.register("light_switch", LightSwitchBlockEntity::new,
                    blocks -> blocks.add(UselessBlocks.LIGHT_SWITCH.get(), UselessBlocks.LIGHT_SWITCH_BLOCK.get()));
}
