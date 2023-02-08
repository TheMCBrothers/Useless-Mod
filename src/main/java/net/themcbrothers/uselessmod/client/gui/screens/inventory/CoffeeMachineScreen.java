package net.themcbrothers.uselessmod.client.gui.screens.inventory;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.minecraftforge.fluids.FluidStack;
import net.themcbrothers.lib.client.screen.widgets.EnergyBar;
import net.themcbrothers.lib.client.screen.widgets.FluidTank;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.Messages;
import net.themcbrothers.uselessmod.network.packets.StartCoffeeMachinePacket;
import net.themcbrothers.uselessmod.network.packets.UpdateMilkCoffeeMachinePacket;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

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
                new TranslatableComponent("gui.uselessmod.use_milk"), this.menu.blockEntity.useMilk()));
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.renderables.stream().filter(widget -> widget instanceof FluidTank tank && tank.isHoveredOrFocused()).forEach(widget -> {
            final FluidTank tank = (FluidTank) widget;
            renderSlotHighlight(poseStack, tank.x - this.leftPos, tank.y - this.topPos, tank.getWidth(), tank.getHeight(), this.slotColor, this.getBlitOffset());
        });
        super.renderLabels(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, COFFEE_MACHINE_LOCATION);

        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, xPos, yPos, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(poseStack, xPos - 16, yPos + 10, 0, 166, 23, 64);

        double d = this.menu.getScaledCookTime(42);
        this.blit(poseStack, xPos + 67, yPos + 39, 176, 0, (int) d, 6);
    }

    @Override
    protected void renderTooltip(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.renderables.stream().filter(widget -> widget instanceof AbstractWidget abstractWidget && abstractWidget.isMouseOver(mouseX, mouseY))
                .map(widget -> (AbstractWidget) widget).forEach(abstractWidget -> abstractWidget.renderToolTip(poseStack, mouseX, mouseY));

        super.renderTooltip(poseStack, mouseX, mouseY);
    }

    private static void renderSlotHighlight(@NotNull PoseStack poseStack, int x, int y, int width, int height, int color, int offset) {
        RenderSystem.disableDepthTest();
        RenderSystem.colorMask(true, true, true, false);
        fillGradient(poseStack, x, y, x + width, y + height, color, color, offset);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.enableDepthTest();
    }

    @Nullable
    public FluidStack getHoveredFluid() {
        for (Widget widget : this.renderables) {
            if (widget instanceof FluidTank tank && tank.isHoveredOrFocused()) {
                return tank.getFluid();
            }
        }
        return null;
    }

    private class StartStopButton extends ExtendedButton {
        private static final Component MSG_START = new TranslatableComponent("gui.uselessmod.start");
        private static final Component MSG_STOP = new TranslatableComponent("gui.uselessmod.stop");
        private final boolean start;

        public StartStopButton(int xPos, int yPos, int width, int height, boolean start) {
            super(xPos, yPos, width, height, TextComponent.EMPTY, button -> {
            });
            this.start = start;
        }

        @Override
        public void onPress() {
            Messages.INSTANCE.sendToServer(new StartCoffeeMachinePacket(this.start));
        }

        @Override
        public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            boolean isRunning = CoffeeMachineScreen.this.menu.isRunning();
            boolean validRecipe = CoffeeMachineScreen.this.menu.isRecipeValid();
            this.active = this.start ? !isRunning && validRecipe : isRunning;
            this.visible = this.start != isRunning;
            super.render(matrixStack, mouseX, mouseY, partialTicks);
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
        public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
            this.checked = CoffeeMachineScreen.this.menu.blockEntity.useMilk();
            super.render(poseStack, mouseX, mouseY, partialTicks);
        }

        @Override
        public void renderButton(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            blit(poseStack, this.x, this.y, this.isFocused() ? 10.0F : 0.0F, this.checked ? 10.0F : 0.0F, 10, 10, 32, 32);
        }

        @Override
        public void renderToolTip(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
            List<Component> tooltip = Collections.singletonList(this.getMessage());
            CoffeeMachineScreen.this.renderTooltip(poseStack, Lists.transform(tooltip, Component::getVisualOrderText), mouseX, mouseY);
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
        public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        }
    }
}
