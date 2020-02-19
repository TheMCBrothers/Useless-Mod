package tk.themcbros.uselessmod.compat.waila;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import mcp.mobius.waila.overlay.tooltiprenderers.TooltipRendererProgressBar;
import mcp.mobius.waila.overlay.tooltiprenderers.TooltipRendererSpacer;
import mcp.mobius.waila.overlay.tooltiprenderers.TooltipRendererStack;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.blocks.LampBlock;
import tk.themcbros.uselessmod.blocks.LightSwitchBlock;
import tk.themcbros.uselessmod.blocks.LightSwitchBlockBlock;
import tk.themcbros.uselessmod.blocks.UselessCropsBlock;
import tk.themcbros.uselessmod.tileentity.*;

@WailaPlugin(UselessMod.MOD_ID)
public class UselessPlugin implements IWailaPlugin {
	
	static final ResourceLocation RENDER_ITEM = new ResourceLocation(UselessMod.MOD_ID, "item");
    static final ResourceLocation RENDER_SPACER = new ResourceLocation(UselessMod.MOD_ID, "spacer");
	static final ResourceLocation RENDER_CRUSHER_PROGRESS = new ResourceLocation(UselessMod.MOD_ID, "crusher_progress");

    static final ResourceLocation CONFIG_DISPLAY_CRUSHER = new ResourceLocation(UselessMod.MOD_ID, "display_crusher_contents");
    static final ResourceLocation CONFIG_DISPLAY_ENERGY = new ResourceLocation(UselessMod.MOD_ID, "display_machine_energy");
    static final ResourceLocation CONFIG_DISPLAY_CLOSET_MATERIAL = new ResourceLocation(UselessMod.MOD_ID, "display_closet_material");
    static final ResourceLocation CONFIG_LAMP = new ResourceLocation(UselessMod.MOD_ID, "lamp");
    static final ResourceLocation CONFIG_LANTERN = new ResourceLocation(UselessMod.MOD_ID, "lantern");
    static final ResourceLocation CONFIG_LIGHT_SWITCH = new ResourceLocation(UselessMod.MOD_ID, "light_switch");
    static final ResourceLocation CONFIG_CABLES = new ResourceLocation(UselessMod.MOD_ID, "cables");

	@Override
	public void register(IRegistrar registrar) {
		UselessMod.LOGGER.debug("Hwyla registrar");

        registrar.addConfig(CONFIG_DISPLAY_CRUSHER, true);
        registrar.addConfig(CONFIG_DISPLAY_ENERGY, true);
        registrar.addConfig(CONFIG_DISPLAY_CLOSET_MATERIAL, true);
        registrar.addConfig(CONFIG_LAMP, true);
        registrar.addConfig(CONFIG_LANTERN, true);
        registrar.addConfig(CONFIG_LIGHT_SWITCH, true);
        registrar.addConfig(CONFIG_CABLES, false);
        
        registrar.registerTooltipRenderer(RENDER_ITEM, new TooltipRendererStack());
        registrar.registerTooltipRenderer(RENDER_SPACER, new TooltipRendererSpacer());
        registrar.registerTooltipRenderer(RENDER_CRUSHER_PROGRESS, new TooltipRendererProgressBar());
        
        registrar.registerStackProvider(HUDHandlerModFluids.INSTANCE, FlowingFluidBlock.class);
        registrar.registerStackProvider(HUDHandlerUselessMod.INSTANCE, UselessCropsBlock.class);
        registrar.registerComponentProvider(HUDHandlerUselessMod.INSTANCE, TooltipPosition.BODY, LampBlock.class);
        registrar.registerComponentProvider(HUDHandlerUselessMod.INSTANCE, TooltipPosition.BODY, LanternBlock.class);
        registrar.registerComponentProvider(HUDHandlerUselessMod.INSTANCE, TooltipPosition.BODY, LightSwitchBlock.class);
        registrar.registerComponentProvider(HUDHandlerUselessMod.INSTANCE, TooltipPosition.BODY, LightSwitchBlockBlock.class);
        registrar.registerComponentProvider(HUDHandlerCrusher.INSTANCE, TooltipPosition.BODY, CrusherTileEntity.class);
        registrar.registerBlockDataProvider(HUDHandlerCrusher.INSTANCE, CrusherTileEntity.class);
        registrar.registerComponentProvider(HUDHandlerCloset.INSTANCE, TooltipPosition.BODY, ClosetTileEntity.class);
        registrar.registerBlockDataProvider(HUDHandlerCloset.INSTANCE, ClosetTileEntity.class);
        registrar.registerComponentProvider(HUDHandlerEnergy.INSTANCE, TooltipPosition.BODY, MachineTileEntity.class);
        registrar.registerBlockDataProvider(HUDHandlerEnergy.INSTANCE, MachineTileEntity.class);
        registrar.registerComponentProvider(HUDHandlerCables.INSTANCE, TooltipPosition.BODY, EnergyCableTileEntity.class);
        registrar.registerComponentProvider(HUDHandlerCables.INSTANCE, TooltipPosition.BODY, FluidPipeTileEntity.class);
        registrar.registerBlockDataProvider(HUDHandlerCables.INSTANCE, EnergyCableTileEntity.class);
        registrar.registerBlockDataProvider(HUDHandlerCables.INSTANCE, FluidPipeTileEntity.class);
	}

}