package tk.themcbros.uselessmod.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.LightSwitchTileEntity;

public class UselessItem extends Item {

	public UselessItem(Properties properties) {
		super(properties.defaultMaxDamage(28));
		
		addPropertyOverride(new ResourceLocation("empowered"), (stack, world, entity) -> UselessItem.this.hasEffect(stack) ? 1F : 0F);
	}
	
	@Override
	public ItemStack getDefaultInstance() {
		CompoundNBT tag = new CompoundNBT();
		tag.putBoolean("empowered", false);
		return new ItemStack(this, 1, tag);
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(this.isInGroup(group)) items.add(this.getDefaultInstance());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		boolean success = false;
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		TileEntity tileEntity = world.getTileEntity(pos);
		PlayerEntity player = context.getPlayer();
		if(tileEntity != null) {
			
			/*if(tileEntity instanceof EnergyCableTileEntity) {
				EnergyCableTileEntity energyCable = (EnergyCableTileEntity) tileEntity;
				EnergyCableNetwork cableNetwork = energyCable.getNetwork();
				if(cableNetwork != null) {
					CustomEnergyStorage energyHandler = cableNetwork.energyStorage;
					if(energyHandler != null) {
						List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
						textComponents.add(new StringTextComponent("-- Energy Cable --"));
						textComponents.add(new StringTextComponent("Network Infos (" + cableNetwork.key + ") :"));
						textComponents.add(new StringTextComponent(energyHandler.getEnergyStored() + " / " + energyHandler.getMaxEnergyStored() + " FE"));
						textComponents.add(new StringTextComponent("Cable Amount: " + cableNetwork.CABLES.size()));
						textComponents.add(new StringTextComponent("Consumer Amount: " + cableNetwork.CONSUMERS.size()));
						for(ITextComponent iTextComponent : textComponents) {
							if(!world.isRemote) player.sendStatusMessage(iTextComponent, false);
						}
					}
				}
				
				return ActionResultType.SUCCESS;
			}*/
			
			if(tileEntity instanceof LightSwitchTileEntity) {
				LightSwitchTileEntity lightSwitch = (LightSwitchTileEntity) tileEntity;
				List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
				List<BlockPos> blockPositions = lightSwitch.getBlockPositions();
				
				textComponents.add(new StringTextComponent("-- Light Switch --"));
				StringBuilder builder = new StringBuilder();
				for(BlockPos blockPos : blockPositions) {
					Item item = world.getBlockState(pos).getBlock().getItem(world, blockPos, world.getBlockState(blockPos)).getItem();
					String positionString = blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ();
					builder.append("[" + positionString + " > " + item.getName() + "], ");
				}
				if (builder.length() > 1) {
					String string = builder.substring(0, builder.length() - 2);
					textComponents.add(new StringTextComponent(string));
				} else {
					textComponents.add(new StringTextComponent("No lights"));
				}
				
				for(ITextComponent iTextComponent : textComponents) {
					if(!world.isRemote) player.sendStatusMessage(iTextComponent, false);
				}
				
				return ActionResultType.SUCCESS;
			}
			
			IFluidHandler fluidHandler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
			IEnergyStorage energyHandler = tileEntity.getCapability(CapabilityEnergy.ENERGY).orElse(null);
			if(fluidHandler != null) {
				List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
				
				textComponents.add(new StringTextComponent("-- Fluid Handler --"));
				for(int i = 0; i < fluidHandler.getTanks(); i++) {
					int tankCapacity = fluidHandler.getTankCapacity(i);
					FluidStack fluidStack = fluidHandler.getFluidInTank(i);
					fluidStack.getDisplayName();
					textComponents.add(new StringTextComponent("Fluid: " + fluidStack.getFluid()));
					textComponents.add(new StringTextComponent("Amount: " + fluidStack.getAmount() + " / " + tankCapacity + " mB"));
				}
				
				
//				String fluid = fluidHandler.getTankProperties()[0].getContents() != null ? fluidHandler.getTankProperties()[0].getContents().getLocalizedName() : "none";
//				int capacity = fluidHandler.getTankProperties()[0].getCapacity();
//				int amount = fluidHandler.getTankProperties()[0].getContents() != null ? fluidHandler.getTankProperties()[0].getContents().amount : 0;
//				textComponents.add(new StringTextComponent("Fluid: " + fluid));
//				textComponents.add(new StringTextComponent("Tank: " + amount + " / " + capacity + " mB"));
				
				for(ITextComponent iTextComponent : textComponents) {
					if(!world.isRemote) player.sendStatusMessage(iTextComponent, false);
				}
				success = true;
			}
			if(energyHandler != null) {
				List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
				
				textComponents.add(new StringTextComponent("-- Energy Storage --"));
				textComponents.add(new StringTextComponent(energyHandler.getEnergyStored() + " / " + energyHandler.getMaxEnergyStored() + " FE"));
				
				for(ITextComponent iTextComponent : textComponents) {
					if(!world.isRemote) player.sendStatusMessage(iTextComponent, false);
				}
				success = true;
			}
		}
		return success ? ActionResultType.SUCCESS : ActionResultType.PASS;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return super.hasEffect(stack) || isEmpowered(stack);
	}
	
	public boolean isEmpowered(ItemStack stack) {
		return stack.hasTag() && stack.getTag().contains("empowered") && stack.getTag().getBoolean("empowered");
	}
	
	public void setEmpowered(ItemStack stack, boolean bool) {
		if(!stack.hasTag()) stack.setTag(new CompoundNBT());
		stack.getTag().putBoolean("empowered", bool);
	}
	
	public void toggleEmpowered(ItemStack stack) {
		if(isEmpowered(stack)) setEmpowered(stack, false);
		else setEmpowered(stack, true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		if(playerIn.isSneaking()) {
			toggleEmpowered(stack);
		} else if(isEmpowered(stack)) {
			chatNetworkList(worldIn, playerIn);
		} else {
			playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.USELESS_NUGGET));
			stack.damageItem(1, playerIn, (player) -> { player.sendBreakAnimation(handIn); });
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	private void chatNetworkList(World worldIn, PlayerEntity playerIn) {
		/*if(EnergyCableNetwork.NETWORK_LIST.size() <= 0) {
			if(worldIn.isRemote) playerIn.sendStatusMessage(new StringTextComponent("-- no cable networks available --"), false);
			return;
		}
		for(EnergyCableNetwork cableNetwork : EnergyCableNetwork.NETWORK_LIST.values()) {
			List<ITextComponent> textComponents = new ArrayList<ITextComponent>();
			CustomEnergyStorage energyHandler = cableNetwork.energyStorage;
			
			textComponents.add(new StringTextComponent("-- EnergyCableNetwork (" + cableNetwork.key + ") --"));
			textComponents.add(new StringTextComponent("Energy: " + energyHandler.getEnergyStored() + " / " + energyHandler.getMaxEnergyStored() + " FE"));
			textComponents.add(new StringTextComponent("Cable Amount: " + cableNetwork.CABLES.size()));
			textComponents.add(new StringTextComponent("Consumer Amount: " + cableNetwork.CONSUMERS.size()));
			for(ITextComponent iTextComponent : textComponents) {
				if(worldIn.isRemote) playerIn.sendStatusMessage(iTextComponent, false);
			}
		}*/
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
