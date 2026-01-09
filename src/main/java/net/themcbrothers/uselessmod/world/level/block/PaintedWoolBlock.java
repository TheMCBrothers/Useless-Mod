package net.themcbrothers.uselessmod.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import org.jetbrains.annotations.Nullable;

public class PaintedWoolBlock extends BaseEntityBlock {
    public static final MapCodec<PaintedWoolBlock> CODEC = simpleCodec(PaintedWoolBlock::new);
    public static final BooleanProperty PAINTED = BooleanProperty.create("painted");

    public PaintedWoolBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PAINTED, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PAINTED);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(PAINTED)) {
            level.setBlock(pos, state.setValue(PAINTED, Boolean.FALSE), UPDATE_CLIENTS);
        } else {
            level.setBlock(pos, state.setValue(PAINTED, Boolean.TRUE), UPDATE_CLIENTS);
            level.scheduleTick(pos, this, 2);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack stack = super.getCloneItemStack(level, pos, state, includeData, player);

        if (level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity paintedWool) {
            stack.applyComponents(paintedWool.collectComponents());
        }

        return stack;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return UselessBlockEntityTypes.PAINTED_WOOL.get().create(pos, state);
    }
}
