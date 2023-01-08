package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.level.block.entity.*;

import static net.themcbrothers.uselessmod.init.Registration.BLOCK_ENTITIES;

public final class ModBlockEntityTypes {
    static void register() {
    }

    public static final RegistryObject<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine", CoffeeMachineBlockEntity::new, ModBlocks.COFFEE_MACHINE);
    public static final RegistryObject<BlockEntityType<CupBlockEntity>> CUP =
            BLOCK_ENTITIES.register("cup", CupBlockEntity::new, ModBlocks.CUP_COFFEE);
    public static final RegistryObject<BlockEntityType<CanvasBlockEntity>> CANVAS =
            BLOCK_ENTITIES.register("canvas", CanvasBlockEntity::new, ModBlocks.CANVAS);
    public static final RegistryObject<BlockEntityType<UselessBedBlockEntity>> BED =
            BLOCK_ENTITIES.register("bed", UselessBedBlockEntity::new, ModBlocks.USELESS_BED);
    public static final RegistryObject<BlockEntityType<WallClosetBlockEntity>> WALL_CLOSET =
            BLOCK_ENTITIES.register("wall_closet", WallClosetBlockEntity::new, ModBlocks.WALL_CLOSET);
    public static final RegistryObject<BlockEntityType<UselessSkullBlockEntity>> SKULL =
            BLOCK_ENTITIES.register("skull", UselessSkullBlockEntity::new,
                    blocks -> blocks.add(ModBlocks.USELESS_SKELETON_SKULL.get(), ModBlocks.USELESS_SKELETON_WALL_SKULL.get()));
}
