package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CupCoffeeBlock extends CupBlock implements EntityBlock {
    public CupCoffeeBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.CUP.get().create(pos, state);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return Optional.ofNullable(level.getBlockEntity(pos))
                .map(blockEntity -> blockEntity instanceof CupBlockEntity cup ? cup : null)
                .map(CupBlockEntity::getCoffeeType)
                .flatMap(coffeeType -> coffeeType)
                .map(CoffeeUtils::createCoffeeStack)
                .orElse(super.getCloneItemStack(state, target, level, pos, player));
    }
}
