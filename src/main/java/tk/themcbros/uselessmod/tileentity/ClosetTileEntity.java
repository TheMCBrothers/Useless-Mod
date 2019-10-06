package tk.themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.container.ClosetContainer;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class ClosetTileEntity extends LockableLootTileEntity {

	private IClosetMaterial casingId = IClosetMaterial.NULL;
	private IClosetMaterial beddingId = IClosetMaterial.NULL;
	private Direction facing = Direction.NORTH;
	private Boolean open = Boolean.FALSE;
	
	public static ModelProperty<IClosetMaterial> CASING = new ModelProperty<IClosetMaterial>();
	public static ModelProperty<IClosetMaterial> BEDDING = new ModelProperty<IClosetMaterial>();
	public static ModelProperty<Direction> FACING = new ModelProperty<Direction>();
	public static ModelProperty<Boolean> OPEN = new ModelProperty<Boolean>();
	
	private NonNullList<ItemStack> closetContents = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
	private int playerCount = 0;
	
	public ClosetTileEntity() {
		super(ModTileEntities.CLOSET);
	}
	
	public void openInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			if (this.playerCount < 0) {
				this.playerCount = 0;
			}

			++this.playerCount;
			BlockState blockstate = this.getBlockState();
			boolean flag = blockstate.get(BlockStateProperties.OPEN);
			if (!flag) {
				this.playDoorSound(blockstate, SoundEvents.BLOCK_BARREL_OPEN);
				this.setDoorState(blockstate, true);
			}

			this.sheduleTick();
		}

	}

	public void sheduleTick() {
		this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
	}

	public void onScheduledTick() {
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		this.playerCount = getPlayersUsing(this.world, this, i, j, k);
		if (this.playerCount > 0) {
			this.sheduleTick();
		} else {
			BlockState blockstate = this.getBlockState();
			if (blockstate.getBlock() != ModBlocks.CLOSET) {
				this.remove();
				return;
			}

			boolean flag = blockstate.get(BlockStateProperties.OPEN);
			if (flag) {
				this.playDoorSound(blockstate, SoundEvents.BLOCK_BARREL_CLOSE);
				this.setDoorState(blockstate, false);
			}
		}

	}
	
	public static int getPlayersUsing(World worldIn, LockableTileEntity tileEntity, int x, int y, int z) {
		int i = 0;
		float f = 5.0F;
		
		for (PlayerEntity playerentity : worldIn.getEntitiesWithinAABB(PlayerEntity.class,
				new AxisAlignedBB((double) ((float) x - f), (double) ((float) y - f), (double) ((float) z - f),
						(double) ((float) (x + 1) + f), (double) ((float) (y + 1) + f),
						(double) ((float) (z + 1) + f)))) {
			if (playerentity.openContainer instanceof ClosetContainer) {
				IInventory iinventory = ((ClosetContainer) playerentity.openContainer).getClosetInventory();
				if (iinventory == tileEntity)
					++i;

			}
		}
		
		return i;
	}

	public void closeInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.playerCount;
		}

	}

	private void setDoorState(BlockState p_213963_1_, boolean p_213963_2_) {
		this.world.setBlockState(this.getPos(),
				p_213963_1_.with(BlockStateProperties.OPEN, Boolean.valueOf(p_213963_2_)), 3);
	}

	private void playDoorSound(BlockState state, SoundEvent sound) {
		Vec3i vec3i = state.get(BlockStateProperties.HORIZONTAL_FACING).getDirectionVec();
		double d0 = (double) this.pos.getX() + 0.5D + (double) vec3i.getX() / 2.0D;
		double d1 = (double) this.pos.getY() + 0.5D + (double) vec3i.getY() / 2.0D;
		double d2 = (double) this.pos.getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
		this.world.playSound((PlayerEntity) null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F,
				this.world.rand.nextFloat() * 0.1F + 0.9F);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		
		this.closetContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.closetContents);
		this.casingId = ClosetRegistry.CASINGS.get(compound.getString("casingId"));
		this.beddingId = ClosetRegistry.BEDDINGS.get(compound.getString("beddingId"));
		this.facing = Direction.byName(compound.getString("facing"));
		this.open = compound.getBoolean("open");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, this.closetContents);
		compound.putString("casingId", this.casingId != null ? this.casingId.getSaveId() : "missing");
		compound.putString("beddingId", this.beddingId != null ? this.beddingId.getSaveId() : "missing");
		compound.putString("facing", facing.getName() != null ? facing.getName() : "north");
		compound.putBoolean("open", this.open);
		return compound;
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
	}
	
	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}
	
	public void setCasingId(IClosetMaterial newId) {
		this.casingId = newId;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}
	
	public void setBeddingId(IClosetMaterial newId) {
		this.beddingId = newId;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}
	
	public void setFacing(Direction newFacing) {
		this.facing = newFacing;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}
	
	public void setOpen(Boolean newOpen) {
		this.open = newOpen;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}
	
	public IClosetMaterial getCasingId() {
		return casingId;
	}
	
	public IClosetMaterial getBeddingId() {
		return beddingId;
	}
	
	public Direction getFacing() {
		return facing;
	}
	
	public Boolean isOpen() {
		return open;
	}
	
	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder().withProperty(CASING).withProperty(BEDDING).withInitial(FACING, Direction.NORTH).withInitial(OPEN, Boolean.FALSE).build();
	}

	@Override
	public int getSizeInventory() {
		return 15;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack itemstack : this.closetContents) {
	         if (!itemstack.isEmpty()) {
	            return false;
	         }
	      }

	      return true;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.uselessmod.closet");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new ClosetContainer(id, player, this);
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.closetContents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.closetContents = stacks;
	}

}
