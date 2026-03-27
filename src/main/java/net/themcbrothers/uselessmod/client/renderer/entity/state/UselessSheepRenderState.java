package net.themcbrothers.uselessmod.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.util.ARGB;

public class UselessSheepRenderState extends SheepRenderState {
    private static final int COLOR = ARGB.color(0xFF, 70, 139, 68);

    @Override
    public int getWoolColor() {
        return COLOR;
    }
}
