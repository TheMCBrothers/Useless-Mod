package tk.themcbros.uselessmod.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class LightSwitchBlockItem extends BlockItem {

	public LightSwitchBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(stack.hasTag() && stack.getTag() != null && stack.getTag().contains("blocks")) {
			if(GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
				for(long l : stack.getTag().getLongArray("blocks")) {
					BlockPos pos = BlockPos.fromLong(l);
					BlockState state = worldIn.getBlockState(pos);
					String modid = state.getBlock().getItem(worldIn, pos, state).getItem().getRegistryName().getNamespace();
					String txt = pos.getX() + "," + pos.getY() + "," + pos.getZ() + ": " + state.getBlock().getItem(worldIn, pos, state).getItem().getName().getFormattedText();
					if(modid != "minecraft") txt = txt + " (" + modid + ")";
					tooltip.add(new StringTextComponent(txt).applyTextStyle(TextFormatting.GRAY));
				}
				tooltip.add(new TranslationTextComponent("tooltip.uselessmod.light_switch.clear"));
			} else {
				tooltip.add(new TranslationTextComponent("tooltip.uselessmod.press_shift"));
			}
		}
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		BlockPos pos = context.getPos();
		BlockState state = world.getBlockState(pos);
		ItemStack stack = context.getItem();
		
		if(state.has(BlockStateProperties.LIT) || state.getBlock() instanceof LanternBlock) {
			if(!stack.hasTag() || stack.getTag() == null) stack.setTag(new CompoundNBT());
			List<Long> longList = new ArrayList<Long>();
			if(stack.getTag().contains("blocks")) {
				for(long l : stack.getTag().getLongArray("blocks")) {
					longList.add(l);
				}
			}
			longList.add(pos.toLong());
			String bPos = "[x=" + pos.getX() + ",y=" + pos.getY() + ",z=" + pos.getZ() + "]";
			player.sendStatusMessage(new TranslationTextComponent("status.uselessmod.light_switch.block_added", bPos), true);
			stack.getTag().putLongArray("blocks", longList);
			return ActionResultType.SUCCESS;
		}
		
		return super.onItemUse(context);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(playerIn.isSneaking()) {
			stack.setTag(new CompoundNBT());
			playerIn.sendStatusMessage(new TranslationTextComponent("status.uselessmod.light_switch.cleared"), true);
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
		}
		
		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
	}

}
