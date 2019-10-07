package tk.themcbros.uselessmod.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.helper.IHammer;
import tk.themcbros.uselessmod.lists.ToolMaterialList;

public class HammerItem extends ToolItem {
	
	 private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.COBBLESTONE, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND);

	public HammerItem(Properties builder) {
		super(-9f, 0.1f, ToolMaterialList.useless, EFFECTIVE_ON, builder.defaultMaxDamage(125));
	}
	
	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		Block b = blockIn.getBlock();
		for(Block block : EFFECTIVE_ON) {
			if(b == block) return true;
		}
		return false;
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		BlockPos pos = context.getPos();
		World world = context.getWorld();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof IHammer) {
			return ((IHammer) block).onHammer(context);
		}
		
		BlockState newState = state.rotate(world, pos, Rotation.CLOCKWISE_90);
		if(newState != state) {
			world.setBlockState(pos, newState, 3);
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.PASS;
	}
	
}
