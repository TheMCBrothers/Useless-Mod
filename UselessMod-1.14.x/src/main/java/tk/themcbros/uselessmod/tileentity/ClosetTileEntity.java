package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.container.ClosetContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class ClosetTileEntity extends TileEntity implements ISidedInventory, INamedContainerProvider {

	private IClosetMaterial casingId = IClosetMaterial.NULL;
	private IClosetMaterial beddingId = IClosetMaterial.NULL;
	private Direction facing = Direction.NORTH;
	
	public static ModelProperty<IClosetMaterial> CASING = new ModelProperty<IClosetMaterial>();
	public static ModelProperty<IClosetMaterial> BEDDING = new ModelProperty<IClosetMaterial>();
	public static ModelProperty<Direction> FACING = new ModelProperty<Direction>();
	public static ModelProperty<Boolean> OPEN = new ModelProperty<Boolean>();
	
	private NonNullList<ItemStack> closetContents = NonNullList.withSize(15, ItemStack.EMPTY);
	
	private static final int[] SLOTS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
	
	public ClosetTileEntity() {
		super(ModTileEntities.CLOSET);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		
		this.closetContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.closetContents);
		this.casingId = ClosetRegistry.CASINGS.get(compound.getString("casingId"));
		this.beddingId = ClosetRegistry.BEDDINGS.get(compound.getString("beddingId"));
		this.facing = Direction.byName(compound.getString("facing"));
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, this.closetContents);
		compound.putString("casingId", this.casingId != null ? this.casingId.getSaveId() : "missing");
		compound.putString("beddingId", this.beddingId != null ? this.beddingId.getSaveId() : "missing");
		compound.putString("facing", facing.getName() != null ? facing.getName() : "north");
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
			this.world.markForRerender(this.getPos());
		}
	}
	
	public void setBeddingId(IClosetMaterial newId) {
		this.beddingId = newId;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.markForRerender(this.getPos());
		}
	}
	
	public void setFacing(Direction newFacing) {
		this.facing = newFacing;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.markForRerender(this.getPos());
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
	
	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder().withProperty(CASING).withProperty(BEDDING).withInitial(FACING, Direction.NORTH).withInitial(OPEN, false).build();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return ItemStackHelper.getAndSplit(closetContents, arg0, arg1);
	}

	@Override
	public int getSizeInventory() {
		return 15;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return this.closetContents.get(arg0);
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
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int arg0) {
		return ItemStackHelper.getAndRemove(closetContents, arg0);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.closetContents.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
	}

	@Override
	public void clear() {
		this.closetContents.clear();
	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, Direction arg2) {
		return true;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, Direction arg2) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOTS;
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new ClosetContainer(id, playerInventory, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		 return new TranslationTextComponent("container.uselessmod.closet");
	}

}
