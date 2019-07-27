package tk.themcbros.uselessmod.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.lists.ModItems;

public class UselessItem extends Item {

	public UselessItem(Properties properties) {
		super(properties.defaultMaxDamage(28));
		
		addPropertyOverride(new ResourceLocation("empowered"), (stack, world, entity) -> UselessItem.this.canRepair ? 1F : 0F);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		boolean success = false;
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		TileEntity tileEntity = world.getTileEntity(pos);
		PlayerEntity player = context.getPlayer();
		if(world.isRemote) {
			return ActionResultType.PASS;
		} else {
			if(tileEntity != null) {
				IFluidHandler fluidHandler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
				IEnergyStorage energyHandler = tileEntity.getCapability(CapabilityEnergy.ENERGY).orElse(null);
				if(fluidHandler != null) {
					List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
					
					textComponents.add(new StringTextComponent("-- Fluid Handler --"));
					String fluid = fluidHandler.getTankProperties()[0].getContents() != null ? fluidHandler.getTankProperties()[0].getContents().getLocalizedName() : "none";
					int capacity = fluidHandler.getTankProperties()[0].getCapacity();
					int amount = fluidHandler.getTankProperties()[0].getContents() != null ? fluidHandler.getTankProperties()[0].getContents().amount : 0;
					textComponents.add(new StringTextComponent("Fluid: " + fluid));
					textComponents.add(new StringTextComponent("Tank: " + amount + " / " + capacity + " mB"));
					
					for(ITextComponent iTextComponent : textComponents) {
						player.sendStatusMessage(iTextComponent, false);
					}
					success = true;
				}
				if(energyHandler != null) {
					List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
					
					textComponents.add(new StringTextComponent("-- Energy Storage --"));
					textComponents.add(new StringTextComponent(energyHandler.getEnergyStored() + " / " + energyHandler.getMaxEnergyStored() + " FE"));
					
					for(ITextComponent iTextComponent : textComponents) {
						player.sendStatusMessage(iTextComponent, false);
					}
					success = true;
				}
			}
		}
		return success ? ActionResultType.SUCCESS : ActionResultType.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.USELESS_NUGGET));
		playerIn.getHeldItem(handIn).damageItem(1, playerIn, (player) -> { player.sendBreakAnimation(handIn); });
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		ITextComponent desc = new TranslationTextComponent("tooltip.uselessmod.press_shift");
		int state = GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT);
		if (state == GLFW.GLFW_PRESS)
			desc = new TranslationTextComponent("item.uselessmod.useless_item.desc");
		tooltip.add(desc);
	}

}
