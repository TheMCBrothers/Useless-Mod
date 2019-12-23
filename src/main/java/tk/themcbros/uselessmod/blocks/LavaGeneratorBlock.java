package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
import tk.themcbros.uselessmod.tileentity.LavaGeneratorTileEntity;

import javax.annotation.Nonnull;

public class LavaGeneratorBlock extends MachineBlock {

	public LavaGeneratorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new LavaGeneratorTileEntity();
	}

	@Override
	public void openContainer(World world, BlockPos pos, PlayerEntity player) {
		// Do nothing... method not called
	}
	
	@Override
	public ActionResultType func_225533_a_(BlockState state, @Nonnull World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
										   BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof LavaGeneratorTileEntity && player instanceof ServerPlayerEntity) {
				if(FluidUtil.getFluidHandler(worldIn, pos, hit.getFace()).map(handler -> tryEmptyContainer(player, handIn, handler)).orElse(false)) {
					player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1f, 1f);
				} else { // If not success open GUI
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity);
				}
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	private boolean tryEmptyContainer(@Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull IFluidHandler handler) {
		ItemStack heldItem = player.getHeldItem(hand);
        if (!heldItem.isEmpty()) {
		return player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                .map(playerInventory -> {

                    FluidActionResult fluidActionResult = FluidUtil.tryEmptyContainerAndStow(heldItem, handler, playerInventory, Integer.MAX_VALUE, player, true);

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
