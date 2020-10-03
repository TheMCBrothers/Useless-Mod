package themcbros.uselessmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Collections;

public class UselessPaxelItem extends ToolItem {
    public UselessPaxelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(attackDamageIn, attackSpeedIn, tier, Collections.emptySet(), properties
                .addToolType(ToolType.AXE, tier.getHarvestLevel())
                .addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
                .addToolType(ToolType.SHOVEL, tier.getHarvestLevel()));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = AxeItem.BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block != null) {
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
                if (playerentity != null) {
                    context.getItem().damageItem(1, playerentity, player -> player.sendBreakAnimation(context.getHand()));
                }
            }

            return ActionResultType.SUCCESS;
        } else {
            if (context.getFace() == Direction.DOWN) {
                return ActionResultType.PASS;
            } else {
                PlayerEntity playerentity = context.getPlayer();
                BlockState blockstate1 = ShovelItem.SHOVEL_LOOKUP.get(blockstate.getBlock());
                BlockState blockstate2 = null;
                if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
                    world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    blockstate2 = blockstate1;
                } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
                    world.playEvent(null, 1009, blockpos, 0);
                    blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.FALSE);
                }

                if (blockstate2 != null) {
                    if (!world.isRemote) {
                        world.setBlockState(blockpos, blockstate2, 11);
                        if (playerentity != null) {
                            context.getItem().damageItem(1, playerentity, player -> player.sendBreakAnimation(context.getHand()));
                        }
                    }

                    return ActionResultType.SUCCESS;
                } else {
                    return ActionResultType.PASS;
                }
            }
        }
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return this.efficiency;
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return true;
    }
}
