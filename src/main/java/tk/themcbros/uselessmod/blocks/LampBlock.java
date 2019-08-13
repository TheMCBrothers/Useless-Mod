package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import tk.themcbros.uselessmod.items.LampBlockItem;
import tk.themcbros.uselessmod.lists.ModItems;

public class LampBlock extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
	
	public LampBlock(Properties props) {
		super(props);
		
		this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)).with(COLOR, DyeColor.WHITE));
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(LIT, COLOR);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		IItemProvider provider = this;
		
		switch (state.get(COLOR)) {
		case WHITE:
			provider = ModItems.WHITE_LAMP;
			break;
		case BLACK:
			provider = ModItems.BLACK_LAMP;
			break;
		case BLUE:
			provider = ModItems.BLUE_LAMP;
			break;
		case BROWN:
			provider = ModItems.BROWN_LAMP;
			break;
		case CYAN:
			provider = ModItems.CYAN_LAMP;
			break;
		case GRAY:
			provider = ModItems.GRAY_LAMP;
			break;
		case GREEN:
			provider = ModItems.GREEN_LAMP;
			break;
		case LIGHT_BLUE:
			provider = ModItems.LIGHT_BLUE_LAMP;
			break;
		case LIGHT_GRAY:
			provider = ModItems.LIGHT_GRAY_LAMP;
			break;
		case LIME:
			provider = ModItems.LIME_LAMP;
			break;
		case MAGENTA:
			provider = ModItems.MAGENTA_LAMP;
			break;
		case ORANGE:
			provider = ModItems.ORANGE_LAMP;
			break;
		case PINK:
			provider = ModItems.PINK_LAMP;
			break;
		case PURPLE:
			provider = ModItems.PURPLE_LAMP;
			break;
		case RED:
			provider = ModItems.RED_LAMP;
			break;
		case YELLOW:
			provider = ModItems.YELLOW_LAMP;
			break;
		default:
			break;

		}
		
		ItemStack stack = new ItemStack(provider);
		return stack;
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState state, net.minecraft.world.storage.loot.LootContext.Builder builder) {
		List<ItemStack> drops = new ArrayList<ItemStack>();
		IItemProvider provider = this;
		
		switch (state.get(COLOR)) {
		case WHITE:
			provider = ModItems.WHITE_LAMP;
			break;
		case BLACK:
			provider = ModItems.BLACK_LAMP;
			break;
		case BLUE:
			provider = ModItems.BLUE_LAMP;
			break;
		case BROWN:
			provider = ModItems.BROWN_LAMP;
			break;
		case CYAN:
			provider = ModItems.CYAN_LAMP;
			break;
		case GRAY:
			provider = ModItems.GRAY_LAMP;
			break;
		case GREEN:
			provider = ModItems.GREEN_LAMP;
			break;
		case LIGHT_BLUE:
			provider = ModItems.LIGHT_BLUE_LAMP;
			break;
		case LIGHT_GRAY:
			provider = ModItems.LIGHT_GRAY_LAMP;
			break;
		case LIME:
			provider = ModItems.LIME_LAMP;
			break;
		case MAGENTA:
			provider = ModItems.MAGENTA_LAMP;
			break;
		case ORANGE:
			provider = ModItems.ORANGE_LAMP;
			break;
		case PINK:
			provider = ModItems.PINK_LAMP;
			break;
		case PURPLE:
			provider = ModItems.PURPLE_LAMP;
			break;
		case RED:
			provider = ModItems.RED_LAMP;
			break;
		case YELLOW:
			provider = ModItems.YELLOW_LAMP;
			break;
		default:
			break;

		}
		
		ItemStack stack = new ItemStack(provider);
		drops.add(stack);
		return drops;
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack heldStack = player.getHeldItem(handIn);
		if(heldStack != null && heldStack.getItem() instanceof DyeItem) {
			DyeColor color = ((DyeItem) heldStack.getItem()).getDyeColor();
			state = state.with(COLOR, color);
		} else {
			if(state.get(LIT) == Boolean.valueOf(false)) state = state.with(LIT, Boolean.valueOf(true));
			else state = state.with(LIT, Boolean.valueOf(false));
		}
		
		worldIn.setBlockState(pos, state, 3);
		
		return true;
	}

	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState state = this.getDefaultState();
		ItemStack stack = context.getItem();
		if(stack.getItem() instanceof LampBlockItem) {
			LampBlockItem lamp = (LampBlockItem) stack.getItem();
			state = state.with(COLOR, lamp.getColor());
		}
		return state;
	}
	
	@Override
	public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos) {
		return getLightValue(state);
	}
	
	@Override
	public int getLightValue(BlockState state) {
		return state.get(LIT) ? 15 : 0;
	}

}
