package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.themcbrothers.uselessmod.UselessMod;

public class UselessWoodTypes {
    public static final WoodType USELESS_OAK = WoodType.register(new WoodType(UselessMod.MOD_ID + ":useless_oak", BlockSetType.OAK));
}
