package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WallClosetBlockEntity extends BaseContainerBlockEntity {
    public static final ModelProperty<Block> MATERIAL_PROPERTY = new ModelProperty<>();

    private Block material = Blocks.AIR;
    private NonNullList<ItemStack> items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            WallClosetBlockEntity.this.playSound(SoundEvents.BARREL_OPEN);
            WallClosetBlockEntity.this.updateBlockState(state, true);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            WallClosetBlockEntity.this.playSound(SoundEvents.BARREL_CLOSE);
            WallClosetBlockEntity.this.updateBlockState(state, false);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int count1, int count2) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu) {
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                return container == WallClosetBlockEntity.this;
            } else {
                return false;
            }
        }
    };

    public WallClosetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.WALL_CLOSET.get(), pos, state);
    }

    @NotNull
    @Override
    public ModelData getModelData() {
        return ModelData.builder().with(MATERIAL_PROPERTY, this.material).build();
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        return this.saveWithoutMetadata(lookupProvider);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        ContainerHelper.saveAllItems(tag, this.items, lookupProvider);
        tag.putString("Material", String.valueOf(BuiltInRegistries.BLOCK.getKey(this.material)));
    }

    @Override
    public void load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.load(tag, lookupProvider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, lookupProvider);
        final ResourceLocation key = ResourceLocation.tryParse(tag.getString("Material"));
        if (key != null) {
            material = BuiltInRegistries.BLOCK.containsKey(key) ? Objects.requireNonNull(BuiltInRegistries.BLOCK.get(key)) : Blocks.AIR;
        }
    }

    public void parseMaterial(String registryName) {
        final ResourceLocation key = ResourceLocation.tryParse(registryName);
        if (key != null) {
            setMaterial(BuiltInRegistries.BLOCK.containsKey(key) ? Objects.requireNonNull(BuiltInRegistries.BLOCK.get(key)) : Blocks.AIR);
        }
    }

    public void setMaterial(Block material) {
        this.material = material;
        //noinspection DataFlowIssue
        this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState());
        this.requestModelDataUpdate();
        this.setChanged();
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            //noinspection DataFlowIssue
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            //noinspection DataFlowIssue
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            //noinspection DataFlowIssue
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected Component getDefaultName() {
        return UselessMod.translate("container", "wall_closet");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new ChestMenu(MenuType.GENERIC_9x2, id, inventory, this, 2);
    }

    @Override
    public int getContainerSize() {
        return 18;
    }

    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack itemstack = ContainerHelper.removeItem(this.items, index, count);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    private void updateBlockState(BlockState state, boolean isOpen) {
        //noinspection DataFlowIssue
        this.getLevel().setBlock(this.getBlockPos(), state.setValue(BlockStateProperties.OPEN, isOpen), 3);
    }

    private void playSound(SoundEvent soundEvent) {
        double x = (double) this.worldPosition.getX() + 0.5D;
        double y = (double) this.worldPosition.getY() + 0.5D;
        double z = (double) this.worldPosition.getZ() + 0.5D;
        //noinspection DataFlowIssue
        this.getLevel().playSound(null, x, y, z, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    public Block getMaterial() {
        return this.material;
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        super.applyComponents(components);
        this.setMaterial(components.getOrDefault(UselessDataComponents.WALL_CLOSET_MATERIAL.get(), Blocks.AIR));
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        super.collectComponents(builder);
        builder.set(UselessDataComponents.WALL_CLOSET_MATERIAL.get(), this.getMaterial());
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        super.removeComponentsFromTag(tag);
        tag.remove("Material");
    }
}
