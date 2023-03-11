package net.themcbrothers.uselessmod.world.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.themcbrothers.lib.energy.EnergyProvider;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModMenuTypes;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

import static net.themcbrothers.lib.util.ContainerHelper.getBlockEntity;

public class CoffeeMachineMenu extends AbstractContainerMenu implements EnergyProvider {

    private static final int INV_SLOT_START = 7;
    private static final int INV_SLOT_END = INV_SLOT_START + 27;
    private static final int USE_ROW_SLOT_START = INV_SLOT_END;
    private static final int USE_ROW_SLOT_END = USE_ROW_SLOT_START + 9;

    public final CoffeeMachineBlockEntity blockEntity;
    private final ContainerData data;
    private final ContainerLevelAccess levelAccess;
    private final Level level;

    public CoffeeMachineMenu(int id, Inventory inventory, CoffeeMachineBlockEntity coffeeMachine, ContainerData data) {
        super(ModMenuTypes.COFFEE_MACHINE.get(), id);
        this.blockEntity = coffeeMachine;
        this.data = data;
        this.levelAccess = ContainerLevelAccess.create(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos());
        this.level = coffeeMachine.getLevel();

        this.addSlot(new CupSlot(this.blockEntity, 0, 62, 16));
        this.addSlot(new CoffeeBeanSlot(this.blockEntity, 1, 80, 16));
        this.addSlot(new ExtraSlot(this.blockEntity, 2, 98, 16));
        this.addSlot(new OutputSlot(this.blockEntity, 3, 98, 52));
        this.addSlot(new FluidItemSlot(this.blockEntity, 4, -10, 16));
        this.addSlot(new OutputSlot(this.blockEntity, 5, -10, 52));
        this.addSlot(new EnergyItemSlot(this.blockEntity, 6, 134, 34));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(this.data);
    }

    public CoffeeMachineMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getBlockEntity(CoffeeMachineBlockEntity.class, inventory, data),
                new SimpleContainerData(CoffeeMachineBlockEntity.DATA_COUNT));
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (index == 3) {
                if (!this.moveItemStackTo(itemStack1, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack1, itemStack);
            } else if (index >= INV_SLOT_START) {
                if (this.isCup(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isBean(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isExtra(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFluidItem(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isEnergyItem(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 6, 7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < INV_SLOT_END) {
                    if (!this.moveItemStackTo(itemStack1, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < USE_ROW_SLOT_END && !this.moveItemStackTo(itemStack1, INV_SLOT_START, INV_SLOT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack1);
        }

        return itemStack;
    }

    private boolean isCup(ItemStack stack) {
        return this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.COFFEE.get())
                .stream().anyMatch(recipe -> recipe.getCupIngredient().test(stack));
    }

    private boolean isBean(ItemStack stack) {
        return this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.COFFEE.get())
                .stream().anyMatch(recipe -> recipe.getBeanIngredient().test(stack));
    }

    private boolean isExtra(ItemStack stack) {
        return this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.COFFEE.get())
                .stream().anyMatch(recipe -> recipe.getExtraIngredient().test(stack));
    }

    private boolean isFluidItem(ItemStack stack) {
        return stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
                .map(fluidHandlerItem -> {
                    for (int i = 0; i < fluidHandlerItem.getTanks(); i++) {
                        FluidStack fluidStack = fluidHandlerItem.getFluidInTank(i);
                        if (fluidStack.getFluid().is(FluidTags.WATER) || fluidStack.getFluid().is(Tags.Fluids.MILK))
                            return true;
                    }
                    return false;
                }).orElse(false);
    }

    private boolean isEnergyItem(ItemStack stack) {
        return stack.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::canExtract).orElse(false);
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return stillValid(this.levelAccess, player, ModBlocks.COFFEE_MACHINE.get());
    }

    public long getEnergyStored() {
        return this.data.get(0);
    }

    public long getMaxEnergyStored() {
        return this.data.get(1);
    }

    public int getCookTime() {
        return this.data.get(2);
    }

    public int getCookTimeTotal() {
        return this.data.get(3);
    }

    public boolean isRunning() {
        return this.getCookTime() > 0 && this.getCookTimeTotal() > 0;
    }

    public boolean isRecipeValid() {
        return this.data.get(4) == 1;
    }

    public double getScaledCookTime(int width) {
        double d0 = this.getCookTime();
        double d1 = this.getCookTimeTotal();
        return d0 != 0 && d1 != 0 ? d0 / d1 * width : 0;
    }

    public IFluidHandler getWaterTank() {
        return this.blockEntity.tankHandler.getWaterTank();
    }

    public IFluidHandler getMilkTank() {
        return this.blockEntity.tankHandler.getMilkTank();
    }

    private static class OutputSlot extends Slot {

        public OutputSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return false;
        }
    }

    private class CupSlot extends Slot {

        public CupSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CoffeeMachineMenu.this.isCup(stack);
        }
    }

    private class CoffeeBeanSlot extends Slot {
        public CoffeeBeanSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CoffeeMachineMenu.this.isBean(stack);
        }
    }

    private class ExtraSlot extends Slot {
        public ExtraSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CoffeeMachineMenu.this.isExtra(stack);
        }
    }

    private class FluidItemSlot extends Slot {
        public FluidItemSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CoffeeMachineMenu.this.isFluidItem(stack);
        }
    }

    private class EnergyItemSlot extends Slot {
        public EnergyItemSlot(Container container, int index, int xPosition, int yPosition) {
            super(container, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return CoffeeMachineMenu.this.isEnergyItem(stack);
        }
    }
}
