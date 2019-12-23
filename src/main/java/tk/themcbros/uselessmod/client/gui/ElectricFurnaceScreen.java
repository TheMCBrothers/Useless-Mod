package tk.themcbros.uselessmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.ElectricFurnaceContainer;
import tk.themcbros.uselessmod.helper.TextUtils;

import java.awt.*;

public class ElectricFurnaceScreen extends ContainerScreen<ElectricFurnaceContainer> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/electric_furnace.png");

	private final Rectangle energyBar = new Rectangle(152, 6, 16, 45);
	
	public ElectricFurnaceScreen(ElectricFurnaceContainer container, PlayerInventory playerInv, ITextComponent title) {
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
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);

		// Draw Energy Bar
		int k = this.container.getEnergyStoredScaled();
		this.blit(i + this.energyBar.x, j + this.energyBar.y + this.energyBar.height - k, 177, 18, this.energyBar.width, k);
		
		// Draw Progress Bar
		int l = this.container.getCookTimeScaled();
		this.blit(i + 79, j + 34, 176, 0, l + 1, 16);
	}
	
	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);
		if (isPointInRegion(energyBar.x, energyBar.y, energyBar.width, energyBar.height, mouseX, mouseY)) {
			int energy = this.container.getEnergyStored();
			int maxEnergy = this.container.getMaxEnergyStored();
			ITextComponent text = TextUtils.energyWithMax(energy, maxEnergy);
			this.renderTooltip(text.getFormattedText(), mouseX, mouseY);
		}
	}

}
