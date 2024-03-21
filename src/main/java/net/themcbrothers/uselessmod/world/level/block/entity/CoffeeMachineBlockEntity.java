package net.themcbrothers.uselessmod.world.level.block.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.themcbrothers.lib.energy.ExtendedEnergyStorage;
import net.themcbrothers.lib.network.PacketUtils;
import net.themcbrothers.lib.util.EnergyUtils;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.config.ServerConfig;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import net.themcbrothers.uselessmod.network.packets.BlockEntitySyncPacket;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CoffeeMachineBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible, SyncableBlockEntity {
    public static final int DATA_COUNT = 5;

    private static final int SYNC_WATER_TANK = 0;
    private static final int SYNC_MILK_TANK = 1;
    private static final int SYNC_USE_MILK = 2;
    private static final int SLOT_INGREDIENT_CUP = 0;
    private static final int SLOT_INGREDIENT_BEAN = 1;
    private static final int SLOT_INGREDIENT_EXTRA = 2;
    private static final int SLOT_RESULT = 3;
    private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INGREDIENT_CUP};
    private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_RESULT};
    private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_INGREDIENT_BEAN, SLOT_INGREDIENT_EXTRA};

    public final NonNullList<ItemStack> items = NonNullList.withSize(7, ItemStack.EMPTY);
    public final ExtendedEnergyStorage energyStorage = new ExtendedEnergyStorage(
            ServerConfig.COFFEE_MACHINE_ENERGY_CAPACITY.get(),
            ServerConfig.COFFEE_MACHINE_ENERGY_TRANSFER.get(), 0);
    public final CoffeeMachineTank tankHandler = new CoffeeMachineTank();
    private boolean useMilk;
    private int litTime;
    private int cookingProgress;
    private int cookingTotalTime;

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> CoffeeMachineBlockEntity.this.energyStorage.getEnergyStored();
                case 1 -> CoffeeMachineBlockEntity.this.energyStorage.getMaxEnergyStored();
                case 2 -> CoffeeMachineBlockEntity.this.cookingProgress;
                case 3 -> CoffeeMachineBlockEntity.this.cookingTotalTime;
                case 4 -> CoffeeMachineBlockEntity.this.getCurrentRecipe() != null ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
        }

        @Override
        public int getCount() {
            return DATA_COUNT;
        }
    };

    public CoffeeMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.COFFEE_MACHINE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CoffeeMachineBlockEntity coffeeMachine) {
        if (!level.isClientSide) {
            // Fluid Slot
            final ItemStack stackFluidIn = coffeeMachine.getItem(4);

            if (!stackFluidIn.isEmpty()) {
                FluidActionResult result = FluidUtil.tryEmptyContainer(stackFluidIn, coffeeMachine.tankHandler, FluidType.BUCKET_VOLUME, null, true);

                if (result.isSuccess()) {
                    ItemStack stackFluidOut = coffeeMachine.getItem(5);
                    ItemStack resultStack = result.getResult();

                    boolean isEmpty = FluidUtil.getFluidHandler(resultStack)
                            .map(fluidHandler -> fluidHandler.getFluidInTank(0).isEmpty()).orElse(true);

                    if (isEmpty) {
                        if (ItemStack.isSameItem(resultStack, stackFluidOut) && resultStack.getMaxStackSize() > 1 && stackFluidOut.getCount() <= stackFluidOut.getMaxStackSize() - resultStack.getCount()) {
                            stackFluidOut.grow(resultStack.getCount());
                            stackFluidIn.shrink(5);
                        } else if (stackFluidOut.isEmpty()) {
                            coffeeMachine.items.set(5, resultStack);

                            stackFluidIn.shrink(5);
                        }
                    } else {
                        coffeeMachine.items.set(4, resultStack);
                    }
                }
            }


            // Energy Slot
            ItemStack energySlotStack = coffeeMachine.items.get(6);
            if (!energySlotStack.isEmpty()) {
                int freeEnergySpace = coffeeMachine.energyStorage.getMaxEnergyStored() - coffeeMachine.energyStorage.getEnergyStored();
                int maxReceive = coffeeMachine.energyStorage.getMaxReceive();
                if (freeEnergySpace > 0) {
                    EnergyUtils.getEnergy(energySlotStack).ifPresent(itemEnergyStorage -> {
                        if (itemEnergyStorage.canExtract()) {
                            int extracted = itemEnergyStorage.extractEnergy(Math.min(freeEnergySpace, maxReceive), false);
                            coffeeMachine.energyStorage.growEnergy(extracted);
                        }
                    });
                }
            }

            if (coffeeMachine.energyStorage.getEnergyStored() > 0 && coffeeMachine.cookingProgress > 0) {
                if (coffeeMachine.getCurrentRecipe() != null) {
                    coffeeMachine.energyStorage.consumeEnergy(ServerConfig.COFFEE_MACHINE_ENERGY_PER_TICK.get());
                    if (coffeeMachine.cookingProgress < coffeeMachine.cookingTotalTime && coffeeMachine.getCurrentRecipe() != null) {
                        coffeeMachine.cookingProgress++;
                    } else {
                        coffeeMachine.process(level.registryAccess(), coffeeMachine.getCurrentRecipe());
                        coffeeMachine.cookingProgress = 0;
                        coffeeMachine.cookingTotalTime = 0;
                    }
                } else {
                    coffeeMachine.cookingProgress = 0;
                    coffeeMachine.cookingTotalTime = 0;
                }
            } else if (coffeeMachine.litTime > 0) {
                coffeeMachine.litTime--;
            }
        }
    }

    private boolean canProcess(RegistryAccess registryAccess, @Nullable CoffeeRecipe recipe) {
        if (!this.items.get(0).isEmpty() && !this.items.get(1).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.getResultItem(registryAccess);
            if (recipeOutput.isEmpty()) {
                return false;
            } else {
                ItemStack outSlotStack = this.items.get(3);
                if (outSlotStack.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(outSlotStack, recipeOutput)) {
                    return false;
                } else if (outSlotStack.getCount() + recipeOutput.getCount() <= this.getMaxStackSize() && outSlotStack.getCount() + recipeOutput.getCount() <= outSlotStack.getMaxStackSize()) {
                    return true;
                } else {
                    return outSlotStack.getCount() + recipeOutput.getCount() <= recipeOutput.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private void process(RegistryAccess registryAccess, @Nullable CoffeeRecipe recipe) {
        if (recipe != null && this.canProcess(registryAccess, recipe)) {
            ItemStack inputCup = this.items.get(0);
            ItemStack inputBean = this.items.get(1);
            ItemStack inputExtra = this.items.get(2);
            ItemStack recipeResult = recipe.getResultItem(registryAccess);
            ItemStack resultSlot = this.items.get(3);
            if (resultSlot.isEmpty()) {
                this.items.set(3, recipeResult.copy());
            } else if (resultSlot.getItem() == recipeResult.getItem()) {
                resultSlot.grow(recipeResult.getCount());
            }

            inputCup.shrink(1);
            inputBean.shrink(1);

            if (inputExtra.hasCraftingRemainingItem()) {
                this.items.set(2, inputExtra.getCraftingRemainingItem());
            } else {
                inputExtra.shrink(1);
            }
            FluidStack waterResource = this.tankHandler.getFluidInTank(0).copy();
            waterResource.setAmount(recipe.getWaterIngredient().getAmount(waterResource.getFluid()));
            this.tankHandler.drain(waterResource, IFluidHandler.FluidAction.EXECUTE);
            if (!recipe.getMilkIngredient().test(FluidStack.EMPTY)) {
                FluidStack milkResource = this.tankHandler.getFluidInTank(1).copy();
                milkResource.setAmount(recipe.getMilkIngredient().getAmount(milkResource.getFluid()));
                this.tankHandler.drain(milkResource, IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    @Nullable
    private CoffeeRecipe getCurrentRecipe() {
        if (this.level == null) return null;
        for (RecipeHolder<CoffeeRecipe> recipeHolder : this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.COFFEE.get())) {
            CoffeeRecipe recipe = recipeHolder.value();
            boolean flag = recipe.getCupIngredient().test(getItem(0))
                    && recipe.getBeanIngredient().test(getItem(1))
                    && recipe.getWaterIngredient().test(this.tankHandler.getFluidInTank(0));
            boolean flag2 = !this.useMilk && recipe.getMilkIngredient().test(FluidStack.EMPTY);
            if (this.useMilk) {
                flag2 = recipe.getMilkIngredient().test(this.tankHandler.getFluidInTank(1));
            }
            boolean flag3 = (recipe.getExtraIngredient() == Ingredient.EMPTY && getItem(2).isEmpty())
                    || recipe.getExtraIngredient().test(getItem(2));
            if (this.canProcess(level.registryAccess(), recipe) && flag && flag2 && flag3) return recipe;
        }
        return null;
    }

    public void startMachine(boolean start) {
        assert this.level != null;
        SoundEvent sound = SoundEvents.AXE_STRIP;
        if (start) {
            CoffeeRecipe recipe = getCurrentRecipe();
            if (recipe == null) {
                return;
            }
            this.cookingTotalTime = recipe.getCookingTime();
            this.cookingProgress = 1;
            this.litTime = recipe.getCookingTime();
        } else {
            sound = SoundEvents.HOE_TILL;
            this.litTime = 0;
            this.cookingProgress = 0;
            this.cookingTotalTime = 0;
        }
        this.setChanged();
        this.level.playSound(null, this.worldPosition, sound, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public void updateUseMilk(boolean useMilk) {
        this.useMilk = useMilk;
        this.sendSyncPacket(SYNC_USE_MILK);
        this.setChanged();
    }

    public boolean useMilk() {
        return useMilk;
    }

    @Override
    public void sendSyncPacket(int type) {
        if (this.level == null || this.level.isClientSide) {
            return;
        }

        RegistryAccess lookupProvider = this.level.registryAccess();
        CompoundTag nbt = new CompoundTag();

        if (type == SYNC_WATER_TANK) {
            nbt.put("Fluid", this.tankHandler.getWaterTank().writeToNBT(lookupProvider, new CompoundTag()));
        } else if (type == SYNC_MILK_TANK) {
            nbt.put("Milk", this.tankHandler.getMilkTank().writeToNBT(lookupProvider, new CompoundTag()));
        } else if (type == SYNC_USE_MILK) {
            nbt.putBoolean("UseMilk", this.useMilk);
        }

        PacketUtils.sendToAllTracking(new BlockEntitySyncPacket(this.worldPosition, nbt), this.level, this.worldPosition);
    }

    @Override
    public void receiveMessageFromServer(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        if (tag.contains("Fluid", Tag.TAG_COMPOUND)) {
            this.tankHandler.getWaterTank().readFromNBT(lookupProvider, tag.getCompound("Fluid"));
        }
        if (tag.contains("Milk", Tag.TAG_COMPOUND)) {
            this.tankHandler.getMilkTank().readFromNBT(lookupProvider, tag.getCompound("Milk"));
        }
        if (tag.contains("UseMilk", Tag.TAG_BYTE)) {
            this.useMilk = tag.getBoolean("UseMilk");
        }
    }

    @Override
    public void load(CompoundTag compound, HolderLookup.Provider lookupProvider) {
        super.load(compound, lookupProvider);
        ContainerHelper.loadAllItems(compound, this.items, lookupProvider);
        this.litTime = compound.getInt("BurnTime");
        this.cookingProgress = compound.getInt("CookTime");
        this.cookingTotalTime = compound.getInt("CookTimeTotal");
        this.useMilk = compound.getBoolean("UseMilk");
        this.energyStorage.setEnergyStored(compound.getInt("EnergyStored"));
        this.tankHandler.getWaterTank().readFromNBT(lookupProvider, compound.getCompound("Water"));
        this.tankHandler.getMilkTank().readFromNBT(lookupProvider, compound.getCompound("Milk"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        ContainerHelper.saveAllItems(tag, this.items, false, lookupProvider);
        tag.putInt("BurnTime", this.litTime);
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
        tag.putBoolean("UseMilk", this.useMilk);
        tag.putInt("EnergyStored", this.energyStorage.getEnergyStored());

        if (!this.tankHandler.getWaterTank().isEmpty()) {
            tag.put("Water", this.tankHandler.getWaterTank().writeToNBT(lookupProvider, new CompoundTag()));
        }

        if (!this.tankHandler.getMilkTank().isEmpty()) {
            tag.put("Milk", this.tankHandler.getMilkTank().writeToNBT(lookupProvider, new CompoundTag()));
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag, lookupProvider);
        return tag;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int[] getSlotsForFace(Direction face) {
        if (face == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return face == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction side) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemStack = this.items.get(index);
        boolean isSame = !stack.isEmpty() && ItemStack.isSameItem(stack, itemStack) && ItemStack.matches(stack, itemStack);

        this.items.set(index, stack);

        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (!isSame) {
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (Objects.requireNonNull(this.level).getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return index != SLOT_RESULT;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        for (ItemStack itemstack : this.items) {
            stackedContents.accountStack(itemstack);
        }
    }

    @Override
    protected Component getDefaultName() {
        return UselessMod.translate("container", "coffee_machine");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.items.clear();
        this.items.addAll(stacks);
        this.setChanged();
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new CoffeeMachineMenu(id, inventory, this, this.dataAccess);
    }

    @Override
    public void applyComponents(DataComponentMap components) {
        super.applyComponents(components);

        Contents contents = components.get(UselessDataComponents.COFFEE_MACHINE_CONTENTS.get());
        if (contents != null) {
            this.tankHandler.getWaterTank().setFluid(contents.water());
            this.tankHandler.getMilkTank().setFluid(contents.milk());
            this.energyStorage.setEnergyStored(contents.energy());
            this.litTime = contents.burnTime();
            this.cookingProgress = contents.cookTime();
            this.cookingTotalTime = contents.cookTimeTotal();
            this.useMilk = contents.useMilk();
        }
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        super.collectComponents(builder);

        builder.set(UselessDataComponents.COFFEE_MACHINE_CONTENTS.get(),
                new Contents(
                        this.tankHandler.getWaterTank().getFluid(),
                        this.tankHandler.getMilkTank().getFluid(),
                        this.energyStorage.getEnergyStored(),
                        this.litTime,
                        this.cookingProgress,
                        this.cookingTotalTime,
                        this.useMilk
                ));
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        super.removeComponentsFromTag(tag);

        tag.remove("EnergyStored");
        tag.remove("Water");
        tag.remove("Milk");
        tag.remove("BurnTime");
        tag.remove("CookTime");
        tag.remove("CookTimeTotal");
        tag.remove("UseMilk");
    }

    public class CoffeeMachineTank implements IFluidHandler {
        final FluidTank waterTank = new FluidTank(ServerConfig.COFFEE_MACHINE_WATER_CAPACITY.get()) {
            @Override
            protected void onContentsChanged() {
                CoffeeMachineBlockEntity.this.sendSyncPacket(SYNC_WATER_TANK);
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid().is(FluidTags.WATER);
            }
        };
        final FluidTank milkTank = new FluidTank(ServerConfig.COFFEE_MACHINE_MILK_CAPACITY.get()) {
            @Override
            protected void onContentsChanged() {
                CoffeeMachineBlockEntity.this.sendSyncPacket(SYNC_MILK_TANK);
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid().is(Tags.Fluids.MILK);
            }
        };

        @Override
        public int getTanks() {
            return 2;
        }

        @Nonnull
        @Override
        public FluidStack getFluidInTank(int tank) {
            return tank == 0 ? waterTank.getFluid() : tank == 1 ? milkTank.getFluid() : FluidStack.EMPTY;
        }

        @Override
        public int getTankCapacity(int tank) {
            return tank == 0 ? waterTank.getCapacity() : tank == 1 ? milkTank.getCapacity() : 0;
        }

        @Override
        public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
            return tank == 0 ? waterTank.isFluidValid(stack) : tank == 1 && milkTank.isFluidValid(stack);
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return resource.getFluid().is(Tags.Fluids.MILK) ? milkTank.fill(resource, action) : waterTank.fill(resource, action);
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            return resource.getFluid().is(Tags.Fluids.MILK) ? milkTank.drain(resource, action) : waterTank.drain(resource, action);
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return FluidStack.EMPTY;
        }

        public FluidTank getWaterTank() {
            return waterTank;
        }

        public FluidTank getMilkTank() {
            return milkTank;
        }
    }

    public record Contents(
            FluidStack water,
            FluidStack milk,
            int energy,
            int burnTime,
            int cookTime,
            int cookTimeTotal,
            boolean useMilk
    ) {
        public static final Codec<Contents> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ExtraCodecs.strictOptionalField(FluidStack.CODEC, "water", FluidStack.EMPTY).forGetter(Contents::water),
                        ExtraCodecs.strictOptionalField(FluidStack.CODEC, "milk", FluidStack.EMPTY).forGetter(Contents::milk),
                        ExtraCodecs.strictOptionalField(ExtraCodecs.NON_NEGATIVE_INT, "energy", 0).forGetter(Contents::energy),
                        ExtraCodecs.strictOptionalField(ExtraCodecs.NON_NEGATIVE_INT, "burn_time", 0).forGetter(Contents::burnTime),
                        ExtraCodecs.strictOptionalField(ExtraCodecs.NON_NEGATIVE_INT, "cook_time", 0).forGetter(Contents::cookTime),
                        ExtraCodecs.strictOptionalField(ExtraCodecs.NON_NEGATIVE_INT, "cook_time_total", 0).forGetter(Contents::cookTimeTotal),
                        ExtraCodecs.strictOptionalField(Codec.BOOL, "use_milk", false).forGetter(Contents::useMilk)
                ).apply(instance, Contents::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, Contents> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public Contents decode(RegistryFriendlyByteBuf buf) {
                FluidStack water = FluidStack.OPTIONAL_STREAM_CODEC.decode(buf);
                FluidStack milk = FluidStack.OPTIONAL_STREAM_CODEC.decode(buf);
                int energy = ByteBufCodecs.VAR_INT.decode(buf);
                int burnTime = ByteBufCodecs.VAR_INT.decode(buf);
                int cookTime = ByteBufCodecs.VAR_INT.decode(buf);
                int cookTimeTotal = ByteBufCodecs.VAR_INT.decode(buf);
                boolean useMilk = ByteBufCodecs.BOOL.decode(buf);
                return new Contents(water, milk, energy, burnTime, cookTime, cookTimeTotal, useMilk);
            }

            @Override
            public void encode(RegistryFriendlyByteBuf buf, Contents contents) {
                FluidStack.OPTIONAL_STREAM_CODEC.encode(buf, contents.water());
                FluidStack.OPTIONAL_STREAM_CODEC.encode(buf, contents.milk());
                ByteBufCodecs.VAR_INT.encode(buf, contents.energy());
                ByteBufCodecs.VAR_INT.encode(buf, contents.burnTime());
                ByteBufCodecs.VAR_INT.encode(buf, contents.cookTime());
                ByteBufCodecs.VAR_INT.encode(buf, contents.cookTimeTotal());
                ByteBufCodecs.BOOL.encode(buf, contents.useMilk());
            }
        };
    }
}
