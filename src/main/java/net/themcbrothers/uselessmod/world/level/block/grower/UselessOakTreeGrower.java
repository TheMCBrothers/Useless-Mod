package net.themcbrothers.uselessmod.world.level.block.grower;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

import static net.themcbrothers.uselessmod.world.worldgen.UselessTreeFeatures.*;

public class UselessOakTreeGrower {
    public static final TreeGrower USELESS_OAK_TREE_GROWER = new TreeGrower("useless_oak", 0.1F, Optional.empty(), Optional.empty(), Optional.of(USELESS_OAK), Optional.of(FANCY_USELESS_OAK), Optional.of(USELESS_OAK_BEES_005), Optional.of(FANCY_USELESS_OAK_BEES_005));
}
