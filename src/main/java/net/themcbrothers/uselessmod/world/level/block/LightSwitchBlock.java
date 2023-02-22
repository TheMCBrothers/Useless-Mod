package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.LightSwitchBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Definition of the Light Switch (can be placed on the floor, walls and ceiling)
 */
public class LightSwitchBlock extends FaceAttachedHorizontalDirectionalBlock implements EntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private final Map<BlockState, VoxelShape> shapesCache;

    public LightSwitchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.WALL).setValue(POWERED, Boolean.FALSE));
        this.shapesCache = this.getShapeForEachState(LightSwitchBlock::calculateShape);
    }

    private static VoxelShape calculateShape(BlockState state) {
        return switch (state.getValue(FACE)) {
            case CEILING -> box(4, 15, 4, 12, 16, 12);
            case FLOOR -> box(4, 0, 4, 12, 1, 12);
            case WALL -> switch (state.getValue(FACING)) {
                case NORTH -> box(4, 4, 15, 12, 12, 16);
                case SOUTH -> box(4, 4, 0, 12, 12, 1);
                case WEST -> box(15, 4, 4, 16, 12, 12);
                case EAST -> box(0, 4, 4, 1, 12, 12);
                default -> Shapes.block();
            };
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE, POWERED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state);
    }

    /**
     * Called by BlockItem after this block has been placed.
     */
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        LightSwitchBlock.setPlacedBy(level, pos, stack);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof LightSwitchBlockEntity blockEntity && blockEntity.switchLights()) {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.FAIL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.LIGHT_SWITCH.get().create(pos, state);
    }

    static void setPlacedBy(Level level, BlockPos pos, ItemStack stack) {
        CompoundTag tag = BlockItem.getBlockEntityData(stack);

        if (tag != null && tag.contains("Lights", Tag.TAG_LONG_ARRAY)) {
            List<BlockPos> lights = Arrays.stream(tag.getLongArray("Lights")).mapToObj(BlockPos::of).toList();

            if (level.getBlockEntity(pos) instanceof LightSwitchBlockEntity blockEntity) {
                blockEntity.setBlockPositions(lights);
            }
        }
    }
}
