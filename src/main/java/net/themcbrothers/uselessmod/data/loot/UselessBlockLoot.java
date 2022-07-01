package net.themcbrothers.uselessmod.data.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModItems;

import java.util.Objects;
import java.util.stream.Collectors;

import static net.themcbrothers.uselessmod.init.ModBlocks.*;

public class UselessBlockLoot extends BlockLoot {
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    @Override
    protected void addTables() {
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
        this.dropSelf(USELESS_OAK_WOOD.getLog());
        this.dropSelf(USELESS_OAK_WOOD.getWood());
        this.dropSelf(USELESS_OAK_WOOD.getStrippedLog());
        this.dropSelf(USELESS_OAK_WOOD.getStrippedWood());
        this.dropSelf(RED_ROSE.get());
        this.dropSelf(BLUE_ROSE.get());
        this.dropSelf(USELESS_ROSE.get());
        this.dropPottedContents(POTTED_RED_ROSE.get());
        this.dropPottedContents(POTTED_BLUE_ROSE.get());
        this.dropPottedContents(POTTED_USELESS_ROSE.get());
        this.dropSelf(USELESS_OAK_SAPLING.get());
        this.add(USELESS_OAK_LEAVES.get(), (block) -> createOakLeavesDrops(block, USELESS_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(USELESS_OAK_WOOD.get());
        this.dropSelf(USELESS_OAK_WOOD.getStairs());
        this.add(USELESS_OAK_WOOD.getSlab(), BlockLoot::createSlabItemTable);
        this.dropSelf(USELESS_OAK_WOOD.getFence());
        this.dropSelf(USELESS_OAK_WOOD.getFenceGate());
        this.add(USELESS_OAK_WOOD.getDoor(), BlockLoot::createDoorTable);
        this.dropSelf(USELESS_OAK_WOOD.getTrapdoor());
        this.dropSelf(USELESS_OAK_WOOD.getPressurePlate());
        this.dropSelf(USELESS_OAK_WOOD.getButton());
        this.dropSelf(USELESS_OAK_WOOD.getSign());
        this.dropSelf(PAINT_BUCKET.get());
        this.dropSelf(USELESS_BARS.get());
        // Rails
        this.dropSelf(USELESS_RAIL.get());
        this.dropSelf(USELESS_POWERED_RAIL.get());
        this.dropSelf(USELESS_DETECTOR_RAIL.get());
        this.dropSelf(USELESS_ACTIVATOR_RAIL.get());
        // misc
        this.dropSelf(COFFEE_MACHINE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(b -> Objects.requireNonNull(b.getRegistryName()).getNamespace().equals(UselessMod.MOD_ID)).collect(Collectors.toList());
    }
}
