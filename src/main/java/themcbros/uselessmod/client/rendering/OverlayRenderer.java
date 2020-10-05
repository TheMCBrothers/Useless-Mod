package themcbros.uselessmod.client.rendering;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.api.energy.IUselessEnergyStorage;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.helpers.TextUtils;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.item.UselessItemItem;
import themcbros.uselessmod.util.Styles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TheMCBrothers
 */
@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class OverlayRenderer {

    public static final OverlayRenderer INSTANCE = new OverlayRenderer();

    private float mana = 0;
    private float manaInfluence = 0;
    private float playerMana = 0;

    public void setMana(float mana, float manaInfluence, float playerMana) {
        this.mana = mana;
        this.manaInfluence = manaInfluence;
        this.playerMana = playerMana;
    }

    private int getScaledMana() {
        float i = this.playerMana;
        float j = 25F; // MAX MANA. Maybe transfer to config?
        return i != 0 ? (int) (i * 79 / j) : 0;
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(final RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
            return;

        OverlayRenderer renderer = OverlayRenderer.INSTANCE;
        Minecraft mc = Minecraft.getInstance();

        if (mc.playerController == null || mc.player == null)
            return;

        MatrixStack matrixStack = event.getMatrixStack();

        if (renderer.playerMana > 0 && mc.playerController.gameIsSurvivalOrAdventure()) {
            boolean flag = mc.player.areEyesInFluid(FluidTags.WATER) || mc.player.getAir() < mc.player.getMaxAir();
            int posX = event.getWindow().getScaledWidth() / 2 + 10;
            int posY = event.getWindow().getScaledHeight() - (flag ? 58 : 48);

            if (Config.CLIENT_CONFIG.renderManaBar.get()) {
                mc.getTextureManager().bindTexture(UselessMod.rl("textures/gui/mana.png"));
                mc.ingameGUI.blit(matrixStack, posX, posY, 0, 0, 81, 8);
                mc.ingameGUI.blit(matrixStack, posX + 1, posY + 1, 0, 8, renderer.getScaledMana(), 6);
            }

            if (mc.player.getHeldItem(Hand.MAIN_HAND).getItem() == ItemInit.USELESS_ITEM.get()) {
                FontRenderer fontRenderer = mc.fontRenderer;

                int x = 200;
                int y = 10;
                x = fontRenderer.drawString(matrixStack, "Mana ", x, y, 0xFFFFFFFF);
                x = fontRenderer.drawString(matrixStack, Float.toString(renderer.mana), x, y, 0xFFFF0000);
                x = fontRenderer.drawString(matrixStack, " Influence ", x, y, 0xFFFFFFFF);
                fontRenderer.drawString(matrixStack, Float.toString(renderer.manaInfluence), x, y, 0xFFFF0000);
                y += 10;
                x = 200;
                x = fontRenderer.drawString(matrixStack, "Player ", x, y, 0xFFFFFFFF);
                fontRenderer.drawString(matrixStack, Float.toString(renderer.playerMana), x, y, 0xFFFF0000);
            }
        }

        ItemStack stack = mc.player.getHeldItemMainhand();
        if (stack.getItem() == ItemInit.USELESS_ITEM.get()) {
            if (mc.objectMouseOver != null && mc.world != null) {

                Screen tooltipScreen = new Screen(StringTextComponent.EMPTY) {
                    @Override
                    public void init(Minecraft mc, int width, int height) {
                        this.minecraft = mc;
                        this.itemRenderer = mc.getItemRenderer();
                        this.font = mc.fontRenderer;
                        this.width = width;
                        this.height = height;
                    }
                };
                tooltipScreen.init(mc, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight());

                if (mc.objectMouseOver instanceof BlockRayTraceResult) {
                    BlockRayTraceResult hit = (BlockRayTraceResult) mc.objectMouseOver;
                    TileEntity tileEntity = mc.world.getTileEntity(hit.getPos());
                    if (tileEntity != null) {
                        if (ItemInit.USELESS_ITEM.get().getMode(stack) == UselessItemItem.Mode.ENERGY) {
                            if (tileEntity.getCapability(CapabilityUselessEnergy.USELESS_ENERGY, null).isPresent()) {
                                IUselessEnergyStorage energyStorage = tileEntity.getCapability(CapabilityUselessEnergy.USELESS_ENERGY, null).orElseThrow(IllegalStateException::new);
                                List<ITextComponent> tooltip = new ArrayList<>();
                                tooltip.add(new StringTextComponent("--- Useless Energy Storage ---").setStyle(Styles.USELESS_ENERGY));
                                tooltip.add(new StringTextComponent("Energy Stored: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getEnergyStored()).setStyle(Styles.USELESS_ENERGY)));
                                tooltip.add(new StringTextComponent("Capacity: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getMaxEnergyStored()).setStyle(Styles.USELESS_ENERGY)));
                                tooltip.add(new StringTextComponent("Max Extract: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getMaxTransfer(true)).setStyle(Styles.USELESS_ENERGY)));
                                tooltip.add(new StringTextComponent("Max Receive: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getMaxTransfer(false)).setStyle(Styles.USELESS_ENERGY)));
                                tooltipScreen.renderTooltip(matrixStack, convert(tooltip), tooltipScreen.width / 2 + 10, tooltipScreen.height / 2 - 16);
                            } else if (tileEntity.getCapability(CapabilityEnergy.ENERGY, null).isPresent()) {
                                IEnergyStorage energyStorage = tileEntity.getCapability(CapabilityEnergy.ENERGY, null).orElseThrow(IllegalStateException::new);
                                List<ITextComponent> tooltip = new ArrayList<>();
                                tooltip.add(new StringTextComponent("--- Energy Storage ---").setStyle(Styles.FORGE_ENERGY));
                                tooltip.add(new StringTextComponent("Energy Stored: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getEnergyStored()).setStyle(Styles.FORGE_ENERGY)));
                                tooltip.add(new StringTextComponent("Capacity: ").setStyle(Styles.TOOLTIP)
                                        .append(TextUtils.energy(energyStorage.getMaxEnergyStored()).setStyle(Styles.FORGE_ENERGY)));
                                tooltipScreen.renderTooltip(matrixStack, convert(tooltip), tooltipScreen.width / 2 + 10, tooltipScreen.height / 2 - 16);
                            }
                        } else if (ItemInit.USELESS_ITEM.get().getMode(stack) == UselessItemItem.Mode.FLUID) {
                            if (tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).isPresent()) {
                                IFluidHandler fluidHandler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).orElseThrow(IllegalStateException::new);
                                List<ITextComponent> tooltip = new ArrayList<>();
                                tooltip.add(new StringTextComponent("--- Fluid Handler ---").setStyle(Styles.MODE_FLUID));
                                for (int i = 0; i < fluidHandler.getTanks(); i++) {
                                    FluidStack fluidStack = fluidHandler.getFluidInTank(i);
                                    tooltip.add(new StringTextComponent((i + 1) + ") Fluid: ").setStyle(Styles.TOOLTIP)
                                            .append(fluidStack.getDisplayName()));
                                    tooltip.add(new StringTextComponent("Amount: ").setStyle(Styles.TOOLTIP)
                                            .append(TextUtils.fluidAmount(fluidStack.getAmount()).setStyle(Styles.MODE_FLUID)));
                                    tooltip.add(new StringTextComponent("Capacity: ").setStyle(Styles.TOOLTIP)
                                            .append(TextUtils.fluidAmount(fluidHandler.getTankCapacity(i)).setStyle(Styles.MODE_FLUID)));
                                }
                                tooltipScreen.renderTooltip(matrixStack, convert(tooltip), tooltipScreen.width / 2 + 10, tooltipScreen.height / 2 - 16);
                            }
                        }
                    }
                } else if (mc.objectMouseOver instanceof EntityRayTraceResult) {
                    EntityRayTraceResult hit = (EntityRayTraceResult) mc.objectMouseOver;
                    Entity entity = hit.getEntity();
                    if (ItemInit.USELESS_ITEM.get().getMode(stack) == UselessItemItem.Mode.ENTITY) {
                        List<ITextComponent> tooltip = new ArrayList<>();
                        tooltip.add(new StringTextComponent("--- Looking at entity ---").setStyle(Styles.MODE_ENTITY));
                        tooltip.add(entity.getDisplayName());
                        if (entity instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            tooltip.add((
                                    new StringTextComponent("Health: ").setStyle(Styles.TOOLTIP)
                                    .append(new StringTextComponent(Float.toString(livingEntity.getHealth())).setStyle(Styles.MODE_ENTITY))
                                    .append(new StringTextComponent(" / ").setStyle(Styles.TOOLTIP))
                                    .append(new StringTextComponent(Float.toString(livingEntity.getMaxHealth())).setStyle(Styles.MODE_ENTITY))
                            ));
                        }
                        tooltipScreen.renderTooltip(matrixStack, convert(tooltip), tooltipScreen.width / 2 + 10, tooltipScreen.height / 2 - 16);
                    }
                }
            }
        }
    }

    private static List<IReorderingProcessor> convert(List<ITextComponent> tooltip) {
        return Lists.transform(tooltip, ITextComponent::func_241878_f);
    }

}
