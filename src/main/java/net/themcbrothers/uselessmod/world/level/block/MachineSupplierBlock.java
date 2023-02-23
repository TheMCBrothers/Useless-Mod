package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.themcbrothers.lib.wrench.Wrench;
import net.themcbrothers.lib.wrench.WrenchUtils;
import net.themcbrothers.lib.wrench.WrenchableBlock;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class MachineSupplierBlock extends BaseEntityBlock implements WrenchableBlock {
    public MachineSupplierBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.MACHINE_SUPPLIER.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (this.tryWrench(state, level, pos, player, hand, hit)) {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
            ItemStack stack = player.getItemInHand(hand);
            if (blockEntity.getMimic() == null && stack.getItem() instanceof BlockItem blockItem) {
                BlockState mimic = blockItem.getBlock().getStateForPlacement(new BlockPlaceContext(level, player, hand, stack, hit));
                if (mimic != null && !mimic.hasBlockEntity()) {
                    blockEntity.setMimic(mimic);

                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean tryWrench(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);

        if (!stack.isEmpty()) {
            Wrench wrench = WrenchUtils.getWrench(stack);
            if (wrench != null && wrench.canUseWrench(stack, player, pos)) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                BlockState mimic;

                if (player.isSecondaryUseActive()) {
                    WrenchUtils.dismantleBlock(state, level, pos, blockEntity, null, null);
                    return true;
                } else if (blockEntity instanceof MachineSupplierBlockEntity machineSupplier
                        && (mimic = machineSupplier.getMimic()) != null) {
                    Block.popResourceFromFace(level, pos, hitResult.getDirection(), new ItemStack(mimic.getBlock()));
                    machineSupplier.setMimic(null);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        CompoundTag tag = BlockItem.getBlockEntityData(stack);
        if (tag != null && tag.contains("Mimic", Tag.TAG_COMPOUND) &&
                level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
            blockEntity.setMimic(NbtUtils.readBlockState(tag.getCompound("Mimic")));
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos1) {
        if (level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
            BlockState mimic = blockEntity.getMimic();
            if (mimic != null) {
                BlockState newMimic = mimic.updateShape(direction, state1, level, pos, pos1);

                if (newMimic.isAir()) {
                    level.destroyBlock(pos, false);
                } else if (!Objects.equals(mimic, newMimic)) {
                    blockEntity.setMimic(newMimic);
                }
            }
        }

        return state;
    }

    private BlockState getMimic(BlockGetter level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
            BlockState state = blockEntity.getMimic();
            if (state != null) {
                return state;
            }
        }
        return Blocks.COBBLESTONE.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getMimic(level, pos).getShape(level, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getMimic(level, pos).getCollisionShape(level, pos, context);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getMimic(level, pos).getVisualShape(level, pos, context);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).getOcclusionShape(level, pos);
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).getBlockSupportShape(level, pos);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).getLightEmission(level, pos);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return this.getMimic(level, pos).getSoundType(level, pos, entity);
    }

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return this.getMimic(level, pos).getFriction(level, pos, entity);
    }

    @Override
    public float getExplosionResistance(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return this.getMimic(level, pos).getExplosionResistance(level, pos, explosion);
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction side) {
        return this.getMimic(level, pos).getSignal(level, pos, side);
    }

    @Override
    public int getDirectSignal(BlockState p_60559_, BlockGetter level, BlockPos pos, Direction side) {
        return this.getMimic(level, pos).getDirectSignal(level, pos, side);
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return this.getMimic(level, pos).getAnalogOutputSignal(level, pos);
    }

    @Override
    public boolean isConduitFrame(BlockState state, LevelReader level, BlockPos pos, BlockPos conduit) {
        return this.getMimic(level, pos).isConduitFrame(level, pos, conduit);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return this.getMimic(level, pos).isLadder(level, pos, entity);
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).isPortalFrame(level, pos);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return this.getMimic(level, pos).canRedstoneConnectTo(level, pos, direction);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction facing, IPlantable plantable) {
        return this.getMimic(level, pos).canSustainPlant(level, pos, facing, plantable);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        return this.getMimic(level, pos).shouldDisplayFluidOverlay(level, pos, fluidState);
    }

    @Override
    public boolean addRunningEffects(BlockState state, Level level, BlockPos pos, Entity entity) {
        return this.getMimic(level, pos).addRunningEffects(level, pos, entity);
    }

    @Override
    public boolean addLandingEffects(BlockState state1, ServerLevel level, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
        return this.getMimic(level, pos).addLandingEffects(level, pos, state2, entity, numberOfParticles);
    }

    @Nullable
    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity) {
        return this.getMimic(level, pos).getBlockPathType(level, pos, entity);
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).getShadeBrightness(level, pos);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getMimic(level, pos).propagatesSkylightDown(level, pos);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
        BlockState mimic = this.getMimic(level, pos);
        mimic.getBlock().fallOn(level, mimic, pos, entity, distance);
    }
}
