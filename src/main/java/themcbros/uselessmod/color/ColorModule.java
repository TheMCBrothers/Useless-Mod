package themcbros.uselessmod.color;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.item.PaintBrushItem;
import themcbros.uselessmod.item.PaintBucketItem;

/**
 * @author TheMCBrothers
 */
public class ColorModule {

    public static final ResourceLocation PAINT_STILL = UselessMod.rl("block/paint_still");
    public static final ResourceLocation PAINT_FLOW = UselessMod.rl("block/paint_flow");

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, UselessMod.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UselessMod.MOD_ID);

    public static final RegistryObject<ColorFluid> PAINT = FLUIDS.register("paint",
            () -> new ColorFluid(FluidAttributes.builder(PAINT_STILL, PAINT_FLOW)));

    public static final RegistryObject<PaintBrushItem> PAINT_BRUSH = ITEMS.register("paint_brush",
            () -> new PaintBrushItem(new Item.Properties().maxStackSize(1).group(UselessMod.GROUP)));
    public static final RegistryObject<PaintBucketItem> BUCKET_PAINT = ITEMS.register("bucket_paint",
            () -> new PaintBucketItem(new Item.Properties().maxStackSize(1).group(UselessMod.GROUP)));

    public static void init() { /* init does nothing */ }

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        ITEMS.register(eventBus);
    }

    public static void clientSetup() {
        RenderTypeLookup.setRenderLayer(ColorModule.PAINT.get(), RenderType.getTranslucent());
    }

    public static void itemColors(final ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        itemColors.register((itemStack, tintIndex) -> {
            if (tintIndex == 1)
                return itemStack.getCapability(CapabilityColor.COLOR).map(IColorHandler::getColor).orElse(-1);
            return -1;
        }, ColorModule.PAINT_BRUSH.get());
    }

}
