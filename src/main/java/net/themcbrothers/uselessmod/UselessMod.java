package net.themcbrothers.uselessmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.setup.ClientSetup;
import net.themcbrothers.uselessmod.setup.CommonSetup;
import net.themcbrothers.uselessmod.setup.ServerSetup;

import javax.annotation.Nonnull;

@Mod(UselessMod.MOD_ID)
public class UselessMod {
    public static final String MOD_ID = "uselessmod";

    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.USELESS_INGOT);
        }
    };

    public static CommonSetup setup;

    public UselessMod() {
        ForgeMod.enableMilkFluid();
        setup = DistExecutor.unsafeRunForDist(() -> ClientSetup::new, () -> ServerSetup::new);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
