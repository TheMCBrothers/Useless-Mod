package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UselessLanternBlock extends LanternBlock {
    public UselessLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(Blocks.LANTERN);
    }
}
