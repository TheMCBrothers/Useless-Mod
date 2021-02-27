package themcbros.uselessmod.datagen;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class UselessBlockLootTables extends BlockLootTables {
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private final Map<ResourceLocation, LootTable.Builder> lootTables = Maps.newHashMap();

    @Override
    protected void addTables() {
        this.registerDropSelfLootTable(BlockInit.BLUE_ROSE.get());
        this.registerLootTable(BlockInit.CANVAS.get(), UselessBlockLootTables::droppingCanvas);
        ILootCondition.IBuilder lootConditionBuilder = BlockStateProperty.builder(BlockInit.COFFEE_CROP.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 7));
        this.registerLootTable(BlockInit.COFFEE_CROP.get(), droppingAndBonusWhen(BlockInit.COFFEE_CROP.get(), ItemInit.COFFEE_BEANS.get(), ItemInit.COFFEE_SEEDS.get(), lootConditionBuilder));
        this.registerLootTable(BlockInit.COFFEE_CUP.get(), UselessBlockLootTables::droppingCoffeeCup);
        this.registerLootTable(BlockInit.COFFEE_MACHINE.get(), UselessBlockLootTables::droppingCoffeeMachine);
        this.registerDropSelfLootTable(BlockInit.CUP.get());
        // todo ender seeds
        this.registerLootTable(BlockInit.PAINT_BUCKET.get(), UselessBlockLootTables::droppingPaintBucket);
        this.registerFlowerPot(BlockInit.POTTED_BLUE_ROSE.get());
        this.registerFlowerPot(BlockInit.POTTED_RED_ROSE.get());
        this.registerFlowerPot(BlockInit.POTTED_USELESS_ROSE.get());
        this.registerFlowerPot(BlockInit.POTTED_USELESS_SAPLING.get());
        this.registerDropSelfLootTable(BlockInit.RED_ROSE.get());
        this.registerDropSelfLootTable(BlockInit.STRIPPED_USELESS_LOG.get());
        this.registerDropSelfLootTable(BlockInit.STRIPPED_USELESS_WOOD.get());
        this.registerDropSelfLootTable(BlockInit.SUPER_USELESS_BLOCK.get());
        this.registerDropSelfLootTable(BlockInit.SUPER_USELESS_ORE.get());
        this.registerDropSelfLootTable(BlockInit.SUPER_USELESS_ORE_END.get());
        this.registerDropSelfLootTable(BlockInit.SUPER_USELESS_ORE_NETHER.get());
        this.registerLootTable(BlockInit.USELESS_BED.get(), bed -> droppingWhen(bed, BedBlock.PART, BedPart.HEAD));
        this.registerDropSelfLootTable(BlockInit.USELESS_BLOCK.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_BUTTON.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_CARPET.get());
        this.registerLootTable(BlockInit.USELESS_CHEST.get(), BlockLootTables::droppingWithName);
        this.registerDropSelfLootTable(BlockInit.USELESS_FENCE.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_FENCE_GATE.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_GENERATOR.get());
        this.registerLootTable(BlockInit.USELESS_LEAVES.get(), leaves -> droppingWithChancesSticksAndApples(leaves, BlockInit.USELESS_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
        this.registerDropSelfLootTable(BlockInit.USELESS_LOG.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_ORE.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_ORE_END.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_ORE_NETHER.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_PLANKS.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_PRESSURE_PLATE.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_RAIL.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_ACTIVATOR_RAIL.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_DETECTOR_RAIL.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_CROSSOVER_RAIL.get());
        this.registerDropSelfLootTable(BlockInit.POWERED_USELESS_RAIL.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_ROSE.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_SAPLING.get());
        this.registerDropSelfLootTable(BlockInit.USELESS_SIGN.get());
        this.registerLootTable(BlockInit.USELESS_SLAB.get(), BlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(BlockInit.USELESS_STAIRS.get());
        ILootCondition.IBuilder lootConditionBuilder1 = BlockStateProperty.builder(BlockInit.USELESS_WHEAT.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 7));
        this.registerLootTable(BlockInit.USELESS_WHEAT.get(), droppingAndBonusWhen(BlockInit.USELESS_WHEAT.get(), ItemInit.USELESS_WHEAT.get(), ItemInit.USELESS_WHEAT_SEEDS.get(), lootConditionBuilder1));
        this.registerDropSelfLootTable(BlockInit.USELESS_WOOD.get());
        this.registerLootTable(BlockInit.WALL_CLOSET.get(), UselessBlockLootTables::droppingWallCloset);
        this.registerLootTable(BlockInit.WOODEN_USELESS_DOOR.get(), BlockLootTables::registerDoor);
        this.registerDropSelfLootTable(BlockInit.WOODEN_USELESS_TRAPDOOR.get());
    }

    private static LootTable.Builder droppingCanvas(Block canvas) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(canvas, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(canvas).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("Color", "BlockEntityTag.Color")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    private static LootTable.Builder droppingCoffeeCup(Block cup) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(cup, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(cup).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("CoffeeType", "CoffeeType")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    private static LootTable.Builder droppingCoffeeMachine(Block coffeeMachine) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(coffeeMachine, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(coffeeMachine).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("EnergyStored", "BlockEntityTag.EnergyStored").replaceOperation("Fluid", "BlockEntityTag.Fluid").replaceOperation("Milk", "BlockEntityTag.Milk").replaceOperation("Items", "BlockEntityTag.Items")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    private static LootTable.Builder droppingPaintBucket(Block paintBucket) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(paintBucket, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(paintBucket).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("Color", "BlockEntityTag.Color")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    private static LootTable.Builder droppingWallCloset(Block wallCloset) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(wallCloset, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(wallCloset).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("Material", "Material")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        this.addTables();
        Set<ResourceLocation> set = Sets.newHashSet();

        for(Block block : getKnownBlocks()) {
            ResourceLocation resourceLocation = block.getLootTable();
            if (resourceLocation != LootTables.EMPTY && set.add(resourceLocation)) {
                LootTable.Builder builder = this.lootTables.remove(resourceLocation);
                if (builder == null) {
                    throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourceLocation, ForgeRegistries.BLOCKS.getKey(block)));
                }

                consumer.accept(resourceLocation, builder);
            }
        }

        if (!this.lootTables.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.lootTables.keySet());
        }
    }
}
