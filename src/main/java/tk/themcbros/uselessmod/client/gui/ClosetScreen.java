package tk.themcbros.uselessmod.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.ClosetContainer;

public class ClosetScreen extends ContainerScreen<ClosetContainer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/closet.png");

	public ClosetScreen(ClosetContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
	}

	@Override
	public void render(int mouseX, int mouseY, float f) {
		this.renderBackground();
		super.render(mouseX, mouseY, f);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F,
				(float) (this.ySize - 96 + 2), 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
	}

}
