package tk.themcbros.uselessmod.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.util.Constants;
import org.lwjgl.glfw.GLFW;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;
import tk.themcbros.uselessmod.blocks.LightSwitchBlockBlock;

import javax.annotation.Nullable;

public class LightSwitchBlockItem extends BlockItem {

	public LightSwitchBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		assert worldIn != null;
		if(stack.hasTag() && stack.getTag().contains("BlockEntityTag", Constants.NBT.TAG_COMPOUND) && stack.getChildTag("BlockEntityTag").contains("Lights", Constants.NBT.TAG_LONG_ARRAY)) {
			if(GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
				for(long l : stack.getChildTag("BlockEntityTag").getLongArray("Lights")) {
					BlockPos pos = BlockPos.fromLong(l);
					BlockState state = worldIn.getBlockState(pos);
					String modid = state.getBlock().getItem(worldIn, pos, state).getItem().getRegistryName().getNamespace();
					String txt = pos.getX() + "," + pos.getY() + "," + pos.getZ() + ": " + state.getBlock().getItem(worldIn, pos, state).getItem().getName().getFormattedText();
					if(modid != "minecraft" && ModList.get().getModContainerById(modid).isPresent()) 
						txt += " (" + ModList.get().getModContainerById(modid).orElseThrow(IllegalStateException::new).getModInfo().getDisplayName() + ")";
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
		
		if(LightSwitchBlockBlock.LIGHTS_OFF_ON.containsKey(state) || LightSwitchBlockBlock.LIGHTS_ON_OFF.containsKey(state)) {
//		if(state.has(BlockStateProperties.LIT) || state.getBlock() instanceof LanternBlock) {
			if(!stack.hasTag()) stack.setTag(new CompoundNBT());
			CompoundNBT blockEntityTag = stack.getOrCreateChildTag("BlockEntityTag");
			List<Long> longList = new ArrayList<Long>();
			if(blockEntityTag.contains("Lights")) {
				for(long l : blockEntityTag.getLongArray("Lights")) {
					longList.add(l);
				}
			}
			
			if(!longList.contains(pos.toLong())) {
				longList.add(pos.toLong());
				String bPos = "[" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]";
				if (player != null)
					player.sendStatusMessage(new TranslationTextComponent("status.uselessmod.light_switch.block_added", bPos), true);
				blockEntityTag.putLongArray("Lights", longList);
			} else if (player != null) {
				player.sendStatusMessage(new TranslationTextComponent("status.uselessmod.light_switch.block_already_added").applyTextStyle(TextFormatting.RED), true);
			}
			return ActionResultType.SUCCESS;
		}
		
		return super.onItemUse(context);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(playerIn.isCrouching()) { // TODO
			stack.setTag(new CompoundNBT());
			playerIn.sendStatusMessage(new TranslationTextComponent("status.uselessmod.light_switch.cleared"), true);
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
		}
		
		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
	}

}
