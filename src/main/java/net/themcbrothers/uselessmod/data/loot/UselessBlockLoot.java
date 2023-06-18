package net.themcbrothers.uselessmod.data.loot;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.init.Registration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.themcbrothers.uselessmod.init.ModBlocks.*;

public class UselessBlockLoot extends BlockLootSubProvider {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(USELESS_SKELETON_SKULL.get()).map(ItemLike::asItem).collect(Collectors.toSet());

    protected UselessBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_USELESS.get()));
        this.add(SUPER_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_SUPER_USELESS.get()));
        this.add(DEEPSLATE_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_USELESS.get()));
        this.add(DEEPSLATE_SUPER_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_SUPER_USELESS.get()));
        this.add(NETHER_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_USELESS.get()));
        this.add(NETHER_SUPER_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_SUPER_USELESS.get()));
        this.add(END_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_USELESS.get()));
        this.add(END_SUPER_USELESS_ORE.get(), (block) -> createOreDrop(block, ModItems.RAW_SUPER_USELESS.get()));
        this.dropSelf(USELESS_BLOCK.get());
        this.dropSelf(SUPER_USELESS_BLOCK.get());
        this.dropSelf(RAW_USELESS_BLOCK.get());
        this.dropSelf(RAW_SUPER_USELESS_BLOCK.get());
        this.dropSelf(USELESS_OAK_LOG.get());
        this.dropSelf(USELESS_OAK_WOOD.get());
        this.dropSelf(STRIPPED_USELESS_OAK_LOG.get());
        this.dropSelf(STRIPPED_USELESS_OAK_WOOD.get());
        this.dropSelf(RED_ROSE.get());
        this.dropSelf(BLUE_ROSE.get());
        this.dropSelf(USELESS_ROSE.get());
        this.dropPottedContents(POTTED_RED_ROSE.get());
        this.dropPottedContents(POTTED_BLUE_ROSE.get());
        this.dropPottedContents(POTTED_USELESS_ROSE.get());
        this.dropPottedContents(POTTED_USELESS_OAK_SAPLING.get());
        LootItemCondition.Builder condition1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(USELESS_WHEAT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.add(USELESS_WHEAT.get(), createCropDrops(USELESS_WHEAT.get(), ModItems.USELESS_WHEAT.get(), ModItems.USELESS_WHEAT_SEEDS.get(), condition1));
        LootItemCondition.Builder condition2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_USELESS_WHEAT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.add(WILD_USELESS_WHEAT.get(), createCropDrops(USELESS_WHEAT.get(), ModItems.USELESS_WHEAT.get(), ModItems.USELESS_WHEAT_SEEDS.get(), condition2));
        LootItemCondition.Builder condition3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(COFFEE_BEANS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.add(COFFEE_BEANS.get(), createCropDrops(COFFEE_BEANS.get(), ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_SEEDS.get(), condition3));
        LootItemCondition.Builder condition4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(WILD_COFFEE_BEANS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.add(WILD_COFFEE_BEANS.get(), createCropDrops(COFFEE_BEANS.get(), ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_SEEDS.get(), condition4));
        this.dropSelf(USELESS_OAK_SAPLING.get());
        this.add(USELESS_OAK_LEAVES.get(), (block) -> createUselessLeavesDrop(block, USELESS_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(USELESS_OAK_PLANKS.get());
        this.dropSelf(USELESS_OAK_STAIRS.get());
        this.add(USELESS_OAK_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(USELESS_OAK_FENCE.get());
        this.dropSelf(USELESS_OAK_FENCE_GATE.get());
        this.add(USELESS_OAK_DOOR.get(), this::createDoorTable);
        this.dropSelf(USELESS_OAK_TRAPDOOR.get());
        this.dropSelf(USELESS_OAK_PRESSURE_PLATE.get());
        this.dropSelf(USELESS_OAK_BUTTON.get());
        this.dropSelf(USELESS_OAK_SIGN.get());
        this.dropSelf(USELESS_OAK_HANGING_SIGN.get());
        this.dropSelf(USELESS_BARS.get());
        this.dropSelf(SUPER_USELESS_BARS.get());
        this.add(USELESS_DOOR.get(), this::createDoorTable);
        this.add(SUPER_USELESS_DOOR.get(), this::createDoorTable);
        this.dropSelf(USELESS_TRAPDOOR.get());
        this.dropSelf(SUPER_USELESS_TRAPDOOR.get());
        this.dropSelf(PAINT_BUCKET.get());
        this.add(PAINTED_WOOL.get(), this::createCopyColorDrop);
        this.dropSelf(USELESS_WOOL.get());
        this.dropSelf(USELESS_CARPET.get());
        this.add(USELESS_BED.get(), (block) -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        // Rails
        this.dropSelf(USELESS_RAIL.get());
        this.dropSelf(USELESS_POWERED_RAIL.get());
        this.dropSelf(USELESS_DETECTOR_RAIL.get());
        this.dropSelf(USELESS_ACTIVATOR_RAIL.get());
        // misc
        this.add(MACHINE_SUPPLIER.get(), this::mimicDrop);
        this.dropSelf(COFFEE_MACHINE.get());
        this.dropSelf(CUP.get());
        this.add(CUP_COFFEE.get(), this::copyCoffeeDrop);
        this.add(USELESS_SKELETON_SKULL.get(), block -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block))));
        this.add(WALL_CLOSET.get(), this::wallClosetDrop);
        this.add(LIGHT_SWITCH.get(), this::createCopyLightsDrop);
        this.add(LIGHT_SWITCH_BLOCK.get(), this::createCopyLightsDrop);
        this.dropOther(LANTERN.get(), Items.LANTERN);
        this.dropSelf(WHITE_LAMP.get());
        this.dropSelf(ORANGE_LAMP.get());
        this.dropSelf(MAGENTA_LAMP.get());
        this.dropSelf(LIGHT_BLUE_LAMP.get());
        this.dropSelf(YELLOW_LAMP.get());
        this.dropSelf(LIME_LAMP.get());
        this.dropSelf(PINK_LAMP.get());
        this.dropSelf(GRAY_LAMP.get());
        this.dropSelf(LIGHT_GRAY_LAMP.get());
        this.dropSelf(CYAN_LAMP.get());
        this.dropSelf(PURPLE_LAMP.get());
        this.dropSelf(BLUE_LAMP.get());
        this.dropSelf(BROWN_LAMP.get());
        this.dropSelf(GREEN_LAMP.get());
        this.dropSelf(RED_LAMP.get());
        this.dropSelf(BLACK_LAMP.get());
    }

    private LootTable.Builder createCopyLightsDrop(ItemLike itemLike) {
        return LootTable.lootTable().withPool(applyExplosionCondition(itemLike, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike))
                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Lights", "BlockEntityTag.Lights"))));
    }

    private LootTable.Builder createCopyColorDrop(ItemLike itemLike) {
        return LootTable.lootTable().withPool(applyExplosionCondition(itemLike, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike))
                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Color", "BlockEntityTag.Color"))));
    }

    private LootTable.Builder mimicDrop(ItemLike itemLike) {
        return LootTable.lootTable().withPool(applyExplosionCondition(itemLike, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike))
                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Mimic", "BlockEntityTag.Mimic"))));
    }

    private LootTable.Builder copyCoffeeDrop(ItemLike itemLike) {
        return LootTable.lootTable().withPool(applyExplosionCondition(itemLike, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike))
                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Coffee", "Coffee"))));
    }

    private LootTable.Builder wallClosetDrop(Block block) {
        return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("Material", "BlockEntityTag.Material")
                                .copy("id", "BlockEntityTag.id")))));
    }

    private LootTable.Builder createUselessLeavesDrop(Block leavesBlock, Block saplingBlock, float... chances) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(leavesBlock)
                                .when(HAS_SHEARS_OR_SILK_TOUCH)
                                .otherwise(applyExplosionCondition(leavesBlock, LootItem.lootTableItem(saplingBlock))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                        .add(applyExplosionDecay(leavesBlock, LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                        .add(applyExplosionCondition(leavesBlock, LootItem.lootTableItem(Items.APPLE))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
