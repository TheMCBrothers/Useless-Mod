package tk.themcbros.uselessmod.client.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.GlowstoneGeneratorContainer;
import tk.themcbros.uselessmod.helper.UselessGuiHelper;

public class GlowstoneGeneratorScreen extends ContainerScreen<GlowstoneGeneratorContainer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/glowstone_generator.png");

	private final Rectangle energyBar = new Rectangle(145, 16, 16, 45);
	
	public GlowstoneGeneratorScreen(GlowstoneGeneratorContainer container, PlayerInventory playerInv, ITextComponent title) {
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
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);

		int k = ((GlowstoneGeneratorContainer) this.container).getEnergyStoredScaled();
		this.blit(i + this.energyBar.x, j + this.energyBar.y + this.energyBar.height - k, 177, 18, this.energyBar.width, k);
		
		int l = ((GlowstoneGeneratorContainer) this.container).getCookTimeScaled();
		this.blit(i + 108, j + 32, 176, 0, l + 1, 16);
	}
	
	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);
		if (UselessGuiHelper.isInBounds(this.guiLeft, this.guiTop, mouseX, mouseY, this.energyBar)) {
			int energy = this.container.getEnergyStored();
			int maxEnergy = this.container.getMaxEnergyStored();
			List<String> tooltip = new ArrayList<String>();
			tooltip.add(energy + " / " + maxEnergy + " RF");
			this.renderTooltip(tooltip, mouseX, mouseY,
					(font == null ? this.font : font));
		}
	}

}
