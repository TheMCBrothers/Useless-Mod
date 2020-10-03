package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.tileentity.IWrenchableTileEntity;
import themcbros.uselessmod.tileentity.MachineSupplierTileEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public class MachineSupplierBlock extends Block {

    public MachineSupplierBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.MACHINE_SUPPLIER.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        BlockState blockState = this.getBlockState(worldIn, pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity instanceof IWrenchableTileEntity) {
            if (((IWrenchableTileEntity) tileEntity).tryWrench(state, player, handIn, hit))
                return ActionResultType.SUCCESS;
        }

        if (tileEntity instanceof MachineSupplierTileEntity) {
            BlockState blockState1 = ((MachineSupplierTileEntity) tileEntity).getMimic();
            if (blockState1 == null) {
                ItemStack stack = player.getHeldItem(handIn);
                if (stack.getItem() instanceof BlockItem) {
                    BlockState blockState2 = ((BlockItem) stack.getItem()).getBlock()
                            .getStateForPlacement(new BlockItemUseContext(new ItemUseContext(player, handIn, hit)));
                    boolean valid = blockState2 != null && !blockState2.hasTileEntity();
                    if (valid) {
                        ((MachineSupplierTileEntity) tileEntity).setMimic(blockState2);
                        stack.shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                return ActionResultType.FAIL;
            }
        }

        return blockState.onBlockActivated(worldIn, player, handIn, hit);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        TileEntity tileEntity = worldIn.getTileEntity(currentPos);
        if (tileEntity instanceof MachineSupplierTileEntity) {
            MachineSupplierTileEntity machineSupplier = (MachineSupplierTileEntity) tileEntity;
            BlockState mimic = machineSupplier.getMimic();
            if (mimic != null) {
                BlockState newMimic = mimic.updatePostPlacement(facing, facingState, worldIn, currentPos, facingPos);
                if (!Objects.equals(mimic, newMimic)) {
                    machineSupplier.setMimic(newMimic);
                }
            }
        }
        return stateIn;
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MachineSupplierTileEntity) {
            MachineSupplierTileEntity machineSupplier = (MachineSupplierTileEntity) tileEntity;
            BlockState mimic = machineSupplier.getMimic();
            if (mimic != null) {
                BlockState newMimic = mimic.rotate(world, pos, direction);
                if (!Objects.equals(mimic, newMimic)) {
                    machineSupplier.setMimic(newMimic);
                }
            }
        }
        return state;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getBlockState(worldIn, pos).getShape(worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getBlockState(worldIn, pos).getCollisionShape(worldIn, pos, context);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getBlockState(worldIn, pos).getRenderShape(worldIn, pos);
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getBlockState(reader, pos).getRaytraceShape(reader, pos, context);
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getBlockState(worldIn, pos).getRayTraceShape(worldIn, pos);
    }

    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return this.getBlockState(world, pos).getSlipperiness(world, pos, entity);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return this.getBlockState(world, pos).getLightValue(world, pos);
    }

    @Override
    public float getExplosionResistance(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion) {
        return this.getBlockState(world, pos).getExplosionResistance(world, pos, explosion);
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return this.getBlockState(world, pos).getSoundType(world, pos, entity);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return this.getBlockState(world, pos).isLadder(world, pos, entity);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return this.getBlockState(world, pos).canHarvestBlock(world, pos, player);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        this.getBlockState(worldIn, pos).onEntityCollision(worldIn, pos, entityIn);
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, @Nullable EntityType<?> entityType) {
        if (world instanceof IWorldReader)
            return this.getBlockState(world, pos).canCreatureSpawn((IWorldReader) world, pos, type, entityType);
        return super.canCreatureSpawn(state, world, pos, type, entityType);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return this.getBlockState(world, pos).canConnectRedstone(world, pos, side);
    }

    @Override
    public boolean addLandingEffects(BlockState state1, ServerWorld worldserver, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
        return this.getBlockState(worldserver, pos).addLandingEffects(worldserver, pos, state2, entity, numberOfParticles);
    }

    @Override
    public boolean addRunningEffects(BlockState state, World world, BlockPos pos, Entity entity) {
        return this.getBlockState(world, pos).addRunningEffects(world, pos, entity);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidState) {
        return this.getBlockState(world, pos).shouldDisplayFluidOverlay(world, pos, fluidState);
    }

    @Override
    public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
        return this.getBlockState(world, pos).isConduitFrame(world, pos, conduit);
    }

    @Override
    public boolean isPortalFrame(BlockState state, IBlockReader world, BlockPos pos) {
        return this.getBlockState(world, pos).isPortalFrame(world, pos);
    }

    @Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return this.getBlockState(world, pos).getAiPathNodeType(world, pos, entity);
    }

    private BlockState getBlockState(IBlockReader world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MachineSupplierTileEntity) {
            BlockState state = ((MachineSupplierTileEntity) tileEntity).getMimic();
            if (state != null)
                return state;
        }
        return Blocks.COBBLESTONE.getDefaultState();
    }
}
