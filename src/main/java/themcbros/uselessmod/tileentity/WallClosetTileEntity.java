package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.helpers.TextUtils;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.TileEntityInit;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;
import themcbros.uselessmod.api.wall_closet.ClosetMaterialInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class WallClosetTileEntity extends LockableLootTileEntity {

    private NonNullList<ItemStack> wallClosetStacks = NonNullList.withSize(18, ItemStack.EMPTY);
    private int numPlayersUsing = 0;
    private Supplier<ClosetMaterial> material = ClosetMaterialInit.OAK_PLANKS;

    public static final ModelProperty<ClosetMaterial> CLOSET_MATERIAL = new ModelProperty<>();
    public static final ModelProperty<Direction> FACING = new ModelProperty<>();

    public WallClosetTileEntity() {
        super(TileEntityInit.WALL_CLOSET.get());
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder().withProperty(CLOSET_MATERIAL).withInitial(FACING, Direction.NORTH).build();
    }

    public ClosetMaterial getMaterial() {
        return material.get();
    }

    public void setClosetMaterial(Supplier<ClosetMaterial> material) {
        this.material = material;
        this.requestModelDataUpdate();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.wallClosetStacks);
        compound.putString("Material", String.valueOf(this.material.get().getRegistryName()));
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        ItemStackHelper.loadAllItems(compound, this.wallClosetStacks);
        ClosetMaterial material = UselessRegistries.CLOSET_MATERIALS.getValue(ResourceLocation.tryCreate(compound.getString("Material")));
        if (material == null) this.material = ClosetMaterialInit.OAK_PLANKS;
        else this.material = () -> material;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = new CompoundNBT();
        this.write(tag);
        return tag;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        if (net.getDirection() == PacketDirection.CLIENTBOUND) {
            assert this.world != null;
            this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
        }
    }

    private void scheduleTick() {
        assert this.world != null;
        this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
    }

    public void closetTick() {
        int posX = this.pos.getX();
        int posY = this.pos.getY();
        int posZ = this.pos.getZ();
        assert this.world != null;
        this.numPlayersUsing = ChestTileEntity.calculatePlayersUsing(this.world, this, posX, posY, posZ);
        if (this.numPlayersUsing > 0) {
            this.scheduleTick();
        } else {
            BlockState blockState = this.getBlockState();
            if (blockState.getBlock() != BlockInit.WALL_CLOSET.get()) {
                this.remove();
                return;
            }

            boolean isOpen = blockState.get(BlockStateProperties.OPEN);
            if (isOpen) {
                this.playSound(SoundEvents.BLOCK_BARREL_CLOSE);
                this.setOpenProperty(blockState, false);
            }
        }

    }

    private void setOpenProperty(BlockState state, boolean open) {
        assert this.world != null;
        this.world.setBlockState(this.getPos(), state.with(BlockStateProperties.OPEN, open), 3);
    }

    private void playSound(SoundEvent soundEvent) {
        double x = (double) this.pos.getX() + 0.5D;
        double y = (double) this.pos.getY() + 0.5D;
        double z = (double) this.pos.getZ() + 0.5D;
        assert this.world != null;
        this.world.playSound(null, x, y, z, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return TextUtils.translate("container", "wall_closet");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ChestContainer(ContainerType.GENERIC_9X2, id, player, this, 2);
    }

    @Override
    public int getSizeInventory() {
        return 18;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.wallClosetStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.wallClosetStacks = stacks;
    }

    @Override
    public void openInventory(PlayerEntity playerEntity) {
        if (!playerEntity.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            BlockState blockState = this.getBlockState();
            boolean isOpen = blockState.get(BlockStateProperties.OPEN);
            if (!isOpen) {
                this.playSound(SoundEvents.BLOCK_BARREL_OPEN);
                this.setOpenProperty(blockState, true);
            }

            this.scheduleTick();
        }
    }

    @Override
    public void closeInventory(PlayerEntity playerEntity) {
        if (!playerEntity.isSpectator()) {
            --numPlayersUsing;
        }
    }
}
