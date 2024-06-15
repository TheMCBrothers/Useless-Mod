package net.themcbrothers.uselessmod.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

import java.util.List;

public final class UselessDataComponents {
    static void register() {
    }

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> COLOR = Registration.DATA_COMPONENT_TYPES.register("color",
            () -> DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT)
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

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Holder<Block>>> WALL_CLOSET_MATERIAL = Registration.DATA_COMPONENT_TYPES.register("wall_closet_material",
            () -> DataComponentType.<Holder<Block>>builder()
                    .persistent(BuiltInRegistries.BLOCK.holderByNameCodec())
                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FluidStack>> FLUID_CONTENTS = Registration.DATA_COMPONENT_TYPES.register("fluid_contents",
            () -> DataComponentType.<FluidStack>builder()
                    .persistent(FluidStack.CODEC)
                    .networkSynchronized(FluidStack.STREAM_CODEC)
                    .build());
}
