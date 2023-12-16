package net.themcbrothers.uselessmod.client.gui.screens.inventory;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.gui.widget.ExtendedButton;
import net.neoforged.neoforge.fluids.FluidStack;
import net.themcbrothers.lib.client.screen.widgets.EnergyBar;
import net.themcbrothers.lib.client.screen.widgets.FluidTank;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.Messages;
import net.themcbrothers.uselessmod.network.packets.StartCoffeeMachinePacket;
import net.themcbrothers.uselessmod.network.packets.UpdateMilkCoffeeMachinePacket;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoffeeMachineScreen extends AbstractContainerScreen<CoffeeMachineMenu> {
    private static final ResourceLocation COFFEE_MACHINE_LOCATION = UselessMod.rl("textures/gui/container/coffee_machine.png");

    public CoffeeMachineScreen(CoffeeMachineMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;

        this.addRenderableOnly(new FluidTank(this.leftPos + 12, this.topPos + 18, 8, 48, this.menu.getWaterTank(), this));
        this.addRenderableOnly(new FluidTank(this.leftPos + 30, this.topPos + 18, 8, 48, this.menu.getMilkTank(), this));
        this.addRenderableWidget(new EnergyBar(this.leftPos + 156, this.topPos + 18, EnergyBar.Size._8x48, this.menu, this));
        this.addRenderableWidget(new StartStopButton(this.leftPos + 61, this.topPos + 50, 32, 20, true));
        this.addRenderableWidget(new StartStopButton(this.leftPos + 61, this.topPos + 50, 32, 20, false));
        this.addRenderableWidget(new MilkCheckboxButton(this.leftPos + 29, this.topPos + 5,
                UselessMod.translate("gui", "use_milk"), this.menu.blockEntity.useMilk()));
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        this.renderables.stream().filter(widget -> widget instanceof FluidTank tank && tank.isHoveredOrFocused()).forEach(widget -> {
            final FluidTank tank = (FluidTank) widget;
            renderSlotHighlight(guiGraphics, tank.getX() - this.leftPos, tank.getY() - this.topPos, tank.getWidth(), tank.getHeight(), 0, this.slotColor);
        });
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int xPos = this.leftPos;
        int yPos = this.topPos;

        // Background
        guiGraphics.blit(COFFEE_MACHINE_LOCATION, xPos, yPos, 0, 0, this.imageWidth, this.imageHeight);
        guiGraphics.blit(COFFEE_MACHINE_LOCATION, xPos - 16, yPos + 10, 0, 166, 23, 64);

        // Progress
        double d = this.menu.getScaledCookTime(42);
        guiGraphics.blit(COFFEE_MACHINE_LOCATION, xPos + 67, yPos + 39, 176, 0, (int) d, 6);
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        for (Renderable renderable : this.renderables) {
            if (renderable instanceof FluidTank tank && tank.isHoveredOrFocused()) {
                tank.renderToolTip(guiGraphics, mouseX, mouseY);
            } else if (renderable instanceof EnergyBar energyBar && energyBar.isHoveredOrFocused()) {
                energyBar.renderToolTip(guiGraphics, mouseX, mouseY);
            } else if (renderable instanceof MilkCheckboxButton checkbox && checkbox.isHoveredOrFocused()) {
                checkbox.renderToolTip(guiGraphics, mouseX, mouseY);
            }
        }

        super.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    public static void renderSlotHighlight(GuiGraphics guiGraphics, int x, int y, int width, int height, int offset, int color) {
        guiGraphics.fillGradient(RenderType.guiOverlay(), x, y, x + width, y + height, color, color, offset);
    }

    @Nullable
    public Pair<Rect2i, FluidStack> getHoveredFluid() {
        for (Renderable widget : this.renderables) {
            if (widget instanceof FluidTank tank && tank.isHoveredOrFocused()) {
                return Pair.of(new Rect2i(
                                this.getGuiLeft() + tank.getX(),
                                this.getGuiTop() + tank.getY(),
                                tank.getWidth(), tank.getHeight()),
                        tank.getFluid());
            }
        }
        return null;
    }

    private class StartStopButton extends ExtendedButton {
        private static final Component MSG_START = UselessMod.translate("gui", "start");
        private static final Component MSG_STOP = UselessMod.translate("gui", "stop");
        private final boolean start;

        public StartStopButton(int xPos, int yPos, int width, int height, boolean start) {
            super(xPos, yPos, width, height, Component.empty(), button -> {
            });
            this.start = start;
        }

        @Override
        public void onPress() {
            Messages.INSTANCE.sendToServer(new StartCoffeeMachinePacket(this.start));
        }

        @Override
        public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            boolean isRunning = CoffeeMachineScreen.this.menu.isRunning();
            boolean validRecipe = CoffeeMachineScreen.this.menu.isRecipeValid();
            this.active = this.start ? !isRunning && validRecipe : isRunning;
            this.visible = this.start != isRunning;
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public @NotNull Component getMessage() {
            return this.start ? MSG_START : MSG_STOP;
        }
    }

    public class MilkCheckboxButton extends AbstractButton {
        private final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/checkbox.png");
        private boolean checked;

        public MilkCheckboxButton(int x, int y, Component title, boolean checked) {
            super(x, y, 10, 10, title);
            this.checked = checked;
        }

        @Override
        public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            this.checked = CoffeeMachineScreen.this.menu.blockEntity.useMilk();
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
        }

        @Override
        public void renderWidget(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTicks) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            poseStack.blit(TEXTURE, this.getX(), this.getY(), this.isFocused() ? 10.0F : 0.0F, this.checked ? 10.0F : 0.0F, 10, 10, 32, 32);
        }

        public void renderToolTip(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
            guiGraphics.renderTooltip(font, this.getMessage(), mouseX, mouseY);
        }

        @Override
        public void onPress() {
            this.checked = !this.checked;
            Messages.INSTANCE.sendToServer(new UpdateMilkCoffeeMachinePacket(this.isChecked()));
        }

        public boolean isChecked() {
            return this.checked;
        }

        @Override
        protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        }
    }
}
