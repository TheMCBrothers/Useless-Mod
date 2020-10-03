package themcbros.uselessmod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UselessFlintAndSteelItem extends Item {
    public UselessFlintAndSteelItem(Item.Properties builder) {
        super(builder);
    }

    /**
     * Called when this item is used when targeting a Block
     */
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        if (CampfireBlock.canBeLit(state)) {
            world.playSound(playerEntity, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            world.setBlockState(pos, state.with(BlockStateProperties.LIT, Boolean.TRUE), 11);
            if (playerEntity != null) {
                context.getItem().damageItem(1, playerEntity, playerEntity1 -> playerEntity1.sendBreakAnimation(context.getHand()));
            }

            return ActionResultType.func_233537_a_(world.isRemote());
        } else {
            BlockPos pos1 = pos.offset(context.getFace());
            if (FireBlock.canLightBlock(world, pos1, context.getPlacementHorizontalFacing())) {
                world.playSound(playerEntity, pos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                BlockState state1 = FireBlock.getFireForPlacement(world, pos1);
                world.setBlockState(pos1, state1, 11);
                ItemStack itemstack = context.getItem();
                if (playerEntity instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, pos1, itemstack);
                    itemstack.damageItem(1, playerEntity, playerEntity1 -> playerEntity1.sendBreakAnimation(context.getHand()));
                }

                return ActionResultType.func_233537_a_(world.isRemote());
            } else {
                return ActionResultType.FAIL;
            }
        }
    }
}
