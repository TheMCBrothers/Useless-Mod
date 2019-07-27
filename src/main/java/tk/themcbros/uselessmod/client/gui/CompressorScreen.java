package tk.themcbros.uselessmod.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.CompressorContainer;

public class CompressorScreen extends ContainerScreen<CompressorContainer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/compressor.png");

	public CompressorScreen(CompressorContainer container, PlayerInventory playerInv, ITextComponent title) {
		super(container, playerInv, title);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float f) {
		this.renderBackground();
		super.render(mouseX, mouseY, f);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F,
				(float) (this.ySize - 96 + 2), 4210752);
		String s1 = ((CompressorContainer) this.container).getEnergyStored() + " / " + ((CompressorContainer) this.container).getMaxEnergyStored() + " FE";
		this.font.drawString(s1, (float) (this.xSize - this.font.getStringWidth(s1) - 8.0F), (float) (this.ySize - 96 + 2), 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);

		int k = ((CompressorContainer) this.container).getEnergyStoredScaled();
		this.blit(i + 146, j + 9 + 60 - k, 193, 31, 15, k);
		
		int l = ((CompressorContainer) this.container).getCompressTimeScaled();
		this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
	}

}
