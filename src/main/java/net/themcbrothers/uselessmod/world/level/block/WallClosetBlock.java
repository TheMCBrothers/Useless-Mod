package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.WallClosetBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

@SuppressWarnings("deprecation")
public class WallClosetBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE_NORTH = Block.box(1, 1, 9, 15, 15, 16);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 1, 1, 7, 15, 15);
    private static final VoxelShape SHAPE_SOUTH = Block.box(1, 1, 0, 15, 15, 7);
    private static final VoxelShape SHAPE_WEST = Block.box(9, 1, 1, 16, 15, 15);

    public WallClosetBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HORIZONTAL_FACING, Direction.NORTH)
                .setValue(OPEN, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stacks) {
        ForgeRegistries.BLOCKS.getKeys().stream()
                .filter(rl -> rl.getPath().endsWith("_planks"))
                .forEach(blockRegistryName -> {
                    final CompoundTag tag = new CompoundTag();
                    tag.putString("Material", blockRegistryName.toString());
                    final ItemStack stack = new ItemStack(this);
                    BlockItem.setBlockEntityData(stack, ModBlockEntityTypes.WALL_CLOSET.get(), tag);
                    stacks.add(stack);
                });
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> hoverText, TooltipFlag tooltipFlag) {
        CompoundTag tag = BlockItem.getBlockEntityData(stack);
        if (tag != null) {
            final ResourceLocation key = ResourceLocation.tryParse(tag.getString("Material"));
            Block block = ForgeRegistries.BLOCKS.getValue(key);
            if (block != null) {
                hoverText.add(new TranslatableComponent(block.getDescriptionId()).withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallCloset) {
            final CompoundTag tag = new CompoundTag();
            tag.putString("Material", String.valueOf(wallCloset.getMaterial().getRegistryName()));
            final ItemStack stack = new ItemStack(this);
            BlockItem.setBlockEntityData(stack, ModBlockEntityTypes.WALL_CLOSET.get(), tag);
            return stack;
        }

        return super.getCloneItemStack(state, target, level, pos, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(HORIZONTAL_FACING)) {
            default -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
                player.openMenu(wallClosetBlockEntity);
                // TODO: open wall closet stat
                PiglinAi.angerNearbyPiglins(player, true);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof Container container) {
                Containers.dropContents(level, pos, container);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, OPEN, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.WALL_CLOSET.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
            wallClosetBlockEntity.recheckOpen();
        }
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
            return wallClosetBlockEntity.getMaterial().getSoundType(state, level, pos, entity);
        }
        return super.getSoundType(state, level, pos, entity);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack stack) {
        if (level.getBlockEntity(pos) instanceof WallClosetBlockEntity wallClosetBlockEntity) {
            if (stack.hasCustomHoverName()) {
                wallClosetBlockEntity.setCustomName(stack.getHoverName());
            }

            CompoundTag tag = BlockItem.getBlockEntityData(stack);
            if (tag != null) {
                wallClosetBlockEntity.parseMaterial(tag.getString("Material"));
            }
        }
    }
}
