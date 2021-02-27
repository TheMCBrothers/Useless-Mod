package themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.api.energy.IEnergyProvider;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ContainerInit;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.recipe.CoffeeRecipe;
import themcbros.uselessmod.recipe.RecipeValidator;
import themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CoffeeMachineContainer extends Container implements IEnergyProvider {

    @Nonnull
    public final CoffeeMachineTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private final IIntArray machineData;
    private final World world;

    public CoffeeMachineContainer(int id, PlayerInventory playerInventory, CoffeeMachineTileEntity tileEntity) {
        super(ContainerInit.COFFEE_MACHINE.get(), id);
        this.tileEntity = tileEntity;
        this.machineData = tileEntity.getMachineData();
        this.canInteractWithCallable = IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos());
        this.world = tileEntity.getWorld();

        this.init(playerInventory);
    }

    public CoffeeMachineContainer(final int windowId, final PlayerInventory inv, final PacketBuffer data) {
        super(ContainerInit.COFFEE_MACHINE.get(), windowId);
        this.tileEntity = getTileEntity(inv, data);
        this.machineData = this.tileEntity.getMachineData();
        this.canInteractWithCallable = IWorldPosCallable.of(Objects.requireNonNull(this.tileEntity.getWorld()), this.tileEntity.getPos());
        this.world = this.tileEntity.getWorld();

        this.init(inv);
    }

    private static CoffeeMachineTileEntity getTileEntity(final PlayerInventory inv, final PacketBuffer data) {
        Objects.requireNonNull(inv, "inv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        TileEntity tileEntity = inv.player.world.getTileEntity(data.readBlockPos());
        if (tileEntity instanceof CoffeeMachineTileEntity) {
            return (CoffeeMachineTileEntity) tileEntity;
        }
        throw new IllegalStateException("TileEntity is not correct! " + tileEntity);
    }

    private void init(PlayerInventory playerInventory) {
        this.addSlot(new CupSlot(tileEntity, 0, 62, 16));
        this.addSlot(new CoffeeBeanSlot(tileEntity, 1, 80, 16));
        this.addSlot(new Slot(tileEntity, 2, 98, 16));
        this.addSlot(new OutputSlot(tileEntity, 3, 98, 52));
        this.addSlot(new FluidItemSlot(tileEntity, 4, -10, 16));
        this.addSlot(new OutputSlot(tileEntity, 5, -10, 52));
        this.addSlot(new EnergyItemSlot(tileEntity, 6, 134, 34));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.trackIntArray(this.machineData);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        int machineSlotCount = 7;
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 3) {
                if (!this.mergeItemStack(itemstack1, machineSlotCount, 36 + machineSlotCount, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= machineSlotCount) {
                if (this.isCup(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isBean(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isExtra(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFluidItem(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isEnergyItem(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 6, 7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= machineSlotCount && index < 27 + machineSlotCount) {
                    if (!this.mergeItemStack(itemstack1, 27 + machineSlotCount, 36 + machineSlotCount, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 27 + machineSlotCount && index < machineSlotCount + 36 && !this.mergeItemStack(itemstack1, machineSlotCount, 27 + machineSlotCount, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, machineSlotCount, 36 + machineSlotCount, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private boolean isCup(ItemStack stack) {
        for (CoffeeRecipe recipe : RecipeValidator.getCoffeeRecipes(this.world)) {
            if (recipe.getCupIngredient().test(stack))
                return true;
        }
        return false;
    }

    private boolean isBean(ItemStack stack) {
        for (CoffeeRecipe recipe : RecipeValidator.getCoffeeRecipes(this.world)) {
            if (recipe.getBeanIngredient().test(stack))
                return true;
        }
        return false;
    }

    private boolean isExtra(ItemStack stack) {
        for (CoffeeRecipe recipe : RecipeValidator.getCoffeeRecipes(this.world)) {
            if (recipe.getExtraIngredient().test(stack))
                return true;
        }
        return false;
    }

    private boolean isFluidItem(ItemStack stack) {
        return stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
                .map(fluidHandlerItem -> {
                    for (int i = 0; i < fluidHandlerItem.getTanks(); i++) {
                        FluidStack fluidStack = fluidHandlerItem.getFluidInTank(i);
                        if (fluidStack.getFluid().isIn(FluidTags.WATER) || fluidStack.getFluid().isIn(Tags.Fluids.MILK))
                            return true;
                    }
                    return false;
                }).orElse(false);
    }

    private boolean isEnergyItem(ItemStack stack) {
        return stack.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::canExtract).orElse(false);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(this.canInteractWithCallable, playerIn, BlockInit.COFFEE_MACHINE.get());
    }

    public long getEnergyStored() {
        return this.machineData.get(0);
    }

    public long getMaxEnergyStored() {
        return this.machineData.get(1);
    }

    public boolean isValidRecipe() {
        return this.machineData.get(6) == 1;
    }

    public int getCookTime() {
        return this.machineData.get(7);
    }

    public int getCookTimeTotal() {
        return this.machineData.get(8);
    }

    public double getScaledCookTime(int width) {
        double d0 = this.getCookTime();
        double d1 = this.getCookTimeTotal();
        return d0 != 0 && d1 != 0 ? d0 / d1 * width : 0;
    }

    public IFluidHandler getWaterHandler() {
        return this.tileEntity.tankHandler.getWaterTank();
    }

    public IFluidHandler getMilkHandler() {
        return this.tileEntity.tankHandler.getMilkTank();
    }

    public boolean isRunning() {
        return this.machineData.get(5) == 1;
    }

    private FluidStack getFluid() {
        Fluid fluid = Registry.FLUID.getByValue(this.machineData.get(4));
        return new FluidStack(fluid, this.machineData.get(2));
    }

    private static class OutputSlot extends Slot {

        public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }
    }

    private class CupSlot extends Slot {

        public CupSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return CoffeeMachineContainer.this.isCup(stack);
        }
    }

    private class CoffeeBeanSlot extends Slot {

        public CoffeeBeanSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return CoffeeMachineContainer.this.isBean(stack);
        }
    }

    private class FluidItemSlot extends Slot {

        public FluidItemSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return CoffeeMachineContainer.this.isFluidItem(stack);
        }
    }

    private class EnergyItemSlot extends Slot {

        public EnergyItemSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return CoffeeMachineContainer.this.isEnergyItem(stack);
        }
    }

    private class CoffeeTankHandler implements IFluidHandler {
        @Override
        public int getTanks() {
            return 1;
        }

        @Nonnull
        @Override
        public FluidStack getFluidInTank(int tank) {
            return CoffeeMachineContainer.this.getFluid();
        }

        @Override
        public int getTankCapacity(int tank) {
            return CoffeeMachineContainer.this.machineData.get(3);
        }

        @Override
        public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
            return true;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0;
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            return FluidStack.EMPTY;
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return FluidStack.EMPTY;
        }
    }

}
