package tk.themcbros.uselessmod.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import tk.themcbros.uselessmod.tileentity.MagmaCrucibleTileEntity;

public class MagmaCrucibleBlock extends MachineBlock {

	public MagmaCrucibleBlock(Properties builder) {
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new MagmaCrucibleTileEntity();
	}

	@Override
	public void openContainer(World world, BlockPos pos, PlayerEntity player) {
		
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof MagmaCrucibleTileEntity && player instanceof ServerPlayerEntity) {
//				if(player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(playerInv -> {
//					return FluidUtil.tryEmptyContainerAndStow(player.getHeldItem(handIn), ((MagmaCrucibleTileEntity) tileEntity).getTank(), playerInv, Integer.MAX_VALUE, player, true).isSuccess();
//				}).orElse(false)) {
				if(FluidUtil.getFluidHandler(worldIn, pos, hit.getFace()).map(handler -> { return tryEmptyTank(player, handIn, handler); }).orElse(false)) {
					worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1f, 1f, false);
//					player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1f, 1f);
				} else { // If not success open GUI
//					((ServerPlayerEntity) player).openContainer((INamedContainerProvider) tileEntity);
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity);
				}
			}
		}
		return true;
	}
	
	private boolean tryEmptyTank(@Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull IFluidHandler handler) {
		ItemStack heldItem = player.getHeldItem(hand);
        if (!heldItem.isEmpty()) {
		return player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                .map(playerInventory -> {

                    FluidActionResult fluidActionResult = FluidUtil.tryFillContainerAndStow(heldItem, handler, playerInventory, Integer.MAX_VALUE, player, true);

                    if (fluidActionResult.isSuccess())
                    {
                        player.setHeldItem(hand, fluidActionResult.getResult());
                        return true;
                    }
                    return false;
                })
                .orElse(false);
        }
        return false;
	}

}
