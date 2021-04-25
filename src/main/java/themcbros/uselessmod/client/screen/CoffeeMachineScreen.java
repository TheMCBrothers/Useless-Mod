package themcbros.uselessmod.client.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.client.screen.widget.EnergyBar;
import themcbros.uselessmod.client.screen.widget.FluidTank;
import themcbros.uselessmod.client.screen.widget.ITooltipRenderer;
import themcbros.uselessmod.container.CoffeeMachineContainer;
import themcbros.uselessmod.network.Messages;
import themcbros.uselessmod.network.packets.StartCoffeeMachinePacket;
import themcbros.uselessmod.network.packets.UpdateMilkCoffeeMachinePacket;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TheMCBrothers
 */
public class CoffeeMachineScreen extends ContainerScreen<CoffeeMachineContainer> {

    private static final ResourceLocation GUI_TEXTURE = UselessMod.rl("textures/gui/container/coffee_machine.png");

    public CoffeeMachineScreen(CoffeeMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.buttons.add(new EnergyBar(this.guiLeft + 156, this.guiTop + 18, 8, 48, this.container, this::renderTooltip));
        this.buttons.add(new FluidTank(this.guiLeft + 12, this.guiTop + 18, 8, 48, this.container.getWaterHandler(), this::renderTooltip));
        this.buttons.add(new FluidTank(this.guiLeft + 30, this.guiTop + 18, 8, 48, this.container.getMilkHandler(), this::renderTooltip));
        this.addButton(new StartStopButton(this.guiLeft + 61, this.guiTop + 50, 32, 20, StringTextComponent.EMPTY, button -> {
            boolean start = ((StartStopButton) button).start;
            Messages.INSTANCE.sendToServer(new StartCoffeeMachinePacket(start));
        }));
        this.addButton(new MilkCheckboxButton(this.guiLeft + 29, this.guiTop + 5,
                new TranslationTextComponent("container.uselessmod.coffee_machine.use_milk"),
                this.container.tileEntity.useMilk(), this::renderTooltip));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
        this.blit(matrixStack, i - 16, j + 10, 0, 166, 23, 64);

        double d = this.container.getScaledCookTime(42);
        this.blit(matrixStack, i + 67, j + 39, 176, 0, (int) d, 6);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        for (Widget widget : this.buttons.stream().filter(widget -> widget instanceof FluidTank && widget.isHovered()).collect(Collectors.toList())) {
            RenderSystem.disableDepthTest();
            int tankX = widget.x - this.guiLeft;
            int tankY = widget.y - this.guiTop;
            RenderSystem.colorMask(true, true, true, false);
            int slotColor = this.slotColor;
            this.fillGradient(matrixStack, tankX, tankY, tankX + widget.getWidth(), tankY + widget.getHeight(), slotColor, slotColor);
            RenderSystem.colorMask(true, true, true, true);
            RenderSystem.enableDepthTest();
        }
        String s = this.title.getString();
        this.font.drawString(matrixStack, s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        for (Widget widget : this.buttons.stream().filter(Widget::isHovered).collect(Collectors.toList())) {
            widget.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }

    @Nullable
    public FluidStack getHoveredFluid() {
        for (Widget widget : this.buttons.stream().filter(widget -> widget instanceof FluidTank && widget.isHovered()).collect(Collectors.toList())) {
            assert widget instanceof FluidTank;
            return ((FluidTank) widget).getFluid();
        }
        return null;
    }

    @MethodsReturnNonnullByDefault
    private class StartStopButton extends ExtendedButton {

        private boolean start = false;

        public StartStopButton(int xPos, int yPos, int width, int height, ITextComponent displayString, IPressable handler) {
            super(xPos, yPos, width, height, displayString, handler);
        }

        @Override
        public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            boolean flag = !CoffeeMachineScreen.this.container.isRunning();
            boolean flag2 = CoffeeMachineScreen.this.container.isValidRecipe();
            if (this.start != flag)
                this.start = flag;
            if (this.active != flag2)
                this.active = flag2;
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }

        @Override
        public ITextComponent getMessage() {
            return start ? new StringTextComponent("Start") : new StringTextComponent("Stop");
        }
    }

    public class MilkCheckboxButton extends AbstractButton {

        private final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/checkbox.png");
        private boolean checked;
        private final ITooltipRenderer tooltipRenderer;

        public MilkCheckboxButton(int x, int y, ITextComponent title, boolean checked, ITooltipRenderer tooltipRenderer) {
            super(x, y, 10, 10, title);
            this.checked = checked;
            this.tooltipRenderer = tooltipRenderer;
        }

        public void onPress() {
            this.checked = !this.checked;
            Messages.INSTANCE.sendToServer(new UpdateMilkCoffeeMachinePacket(this.isChecked()));
        }

        public boolean isChecked() {
            return checked;
        }

        @Override
        public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            this.checked = CoffeeMachineScreen.this.container.tileEntity.useMilk();
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }

        @Override
        public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.getTextureManager().bindTexture(TEXTURE);
            RenderSystem.enableDepthTest();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            blit(matrixStack, this.x, this.y, this.isFocused() ? 10.0F : 0.0F, this.checked ? 10.0F : 0.0F, 10, 10, 32, 32);
            this.renderBg(matrixStack, minecraft, mouseX, mouseY);
        }

        @Override
        public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
            List<ITextComponent> tooltip = Collections.singletonList(this.getMessage());
            this.tooltipRenderer.renderTooltip(matrixStack, Lists.transform(tooltip, ITextComponent::func_241878_f), mouseX, mouseY);
        }
    }

}
