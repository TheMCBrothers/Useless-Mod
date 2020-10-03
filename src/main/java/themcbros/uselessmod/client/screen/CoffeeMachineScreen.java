package themcbros.uselessmod.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.client.screen.widget.EnergyBar;
import themcbros.uselessmod.client.screen.widget.FluidTank;
import themcbros.uselessmod.container.CoffeeMachineContainer;
import themcbros.uselessmod.network.Messages;
import themcbros.uselessmod.network.packets.StartCoffeeMachinePacket;

import javax.annotation.Nullable;

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
        this.buttons.add(new FluidTank(this.guiLeft + 12, this.guiTop + 18, 8, 48, this.container.getFluidHandler(), this::renderTooltip));
        this.addButton(new StartStopButton(this.guiLeft + 61, this.guiTop + 50, 32, 20, StringTextComponent.EMPTY, button -> {
            boolean start = ((StartStopButton) button).start;
            Messages.INSTANCE.sendToServer(new StartCoffeeMachinePacket(start));
        }));
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

        double d = this.container.getScaledCookTime(42);
        this.blit(matrixStack, i + 67, j + 39, 176, 0, (int) d, 6);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        String s = this.title.getString();
        this.font.drawString(matrixStack, s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        for (Widget widget : this.buttons) {
            if (widget.isHovered()) {
                widget.renderToolTip(matrixStack, mouseX, mouseY);
            }
        }
    }

    @Nullable
    public FluidStack getHoveredFluid() {
        for (Widget widget : this.buttons) {
            if (widget instanceof FluidTank && widget.isHovered()) {
                return ((FluidTank) widget).getFluid();
            }
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

}
