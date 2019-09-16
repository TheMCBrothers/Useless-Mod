package tk.themcbros.uselessmod.helper;

import java.awt.Rectangle;

public class UselessGuiHelper {

	public static boolean isInBounds(int guiLeft, int guiTop, int mouseX, int mouseY, Rectangle rect) {
		return mouseX >= rect.x + guiLeft && mouseX <= rect.x + rect.width + guiLeft && mouseY >= rect.y + guiTop
				&& mouseY <= rect.y + rect.height + guiTop;
	}

}
