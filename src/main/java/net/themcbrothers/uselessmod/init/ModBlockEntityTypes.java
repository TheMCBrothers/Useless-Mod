package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.level.block.entity.CanvasBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.UselessBedBlockEntity;

import static net.themcbrothers.uselessmod.init.Registration.BLOCK_ENTITIES;

public final class ModBlockEntityTypes {
    static void register() {
    }

    public static final RegistryObject<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine", CoffeeMachineBlockEntity::new, ModBlocks.COFFEE_MACHINE);
    public static final RegistryObject<BlockEntityType<CanvasBlockEntity>> CANVAS =
            BLOCK_ENTITIES.register("canvas", CanvasBlockEntity::new, ModBlocks.CANVAS);
    public static final RegistryObject<BlockEntityType<UselessBedBlockEntity>> BED =
            BLOCK_ENTITIES.register("bed", UselessBedBlockEntity::new, ModBlocks.USELESS_BED);
}
