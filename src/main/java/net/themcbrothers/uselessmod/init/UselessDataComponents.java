package net.themcbrothers.uselessmod.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

import java.util.List;

public final class UselessDataComponents {
    static void register() {
    }

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> COLOR = Registration.DATA_COMPONENT_TYPES.register("color",
            () -> DataComponentType.<Integer>builder()
                    .persistent(ExtraCodecs.NON_NEGATIVE_INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<BlockPos>>> LIGHTS = Registration.DATA_COMPONENT_TYPES.register("lights",
            () -> DataComponentType.<List<BlockPos>>builder()
                    .persistent(BlockPos.CODEC.listOf())
                    .networkSynchronized(BlockPos.STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)))
                    .build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockState>> MIMIC = Registration.DATA_COMPONENT_TYPES.register("mimic",
            () -> DataComponentType.<BlockState>builder()
                    .persistent(BlockState.CODEC)
                    .build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CoffeeMachineBlockEntity.Contents>> COFFEE_MACHINE_CONTENTS = Registration.DATA_COMPONENT_TYPES.register("coffee_machine_contents",
            () -> DataComponentType.<CoffeeMachineBlockEntity.Contents>builder()
                    .persistent(CoffeeMachineBlockEntity.Contents.CODEC)
                    .networkSynchronized(CoffeeMachineBlockEntity.Contents.STREAM_CODEC)
                    .build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CoffeeType>> COFFEE_TYPE = Registration.DATA_COMPONENT_TYPES.register("coffee_type",
            () -> DataComponentType.<CoffeeType>builder()
                    .persistent(CoffeeType.CODEC)
                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Block>> WALL_CLOSET_MATERIAL = Registration.DATA_COMPONENT_TYPES.register("wall_closet_material",
            () -> DataComponentType.<Block>builder()
                    .persistent(BuiltInRegistries.BLOCK.byNameCodec())
                    .build());
}
