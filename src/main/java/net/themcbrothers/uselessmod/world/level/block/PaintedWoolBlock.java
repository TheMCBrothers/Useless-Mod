package net.themcbrothers.uselessmod.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.HitResult;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack stack) {
        level.getBlockEntity(pos, ModBlockEntityTypes.PAINTED_WOOL.get()).ifPresent(paintedWool -> {
            CompoundTag tag = BlockItem.getBlockEntityData(stack);
            if (tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC)) {
                paintedWool.setColor(tag.getInt("Color"));
            }
        });
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        final ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);

        if (level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity paintedWool) {
            BlockItem.setBlockEntityData(stack, paintedWool.getType(), paintedWool.getUpdateTag());
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompoundTag tag = BlockItem.getBlockEntityData(stack);
        if (tag != null) {
            int color = tag.getInt("Color");
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            tooltip.add(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.PAINTED_WOOL.get().create(pos, state);
    }
}
