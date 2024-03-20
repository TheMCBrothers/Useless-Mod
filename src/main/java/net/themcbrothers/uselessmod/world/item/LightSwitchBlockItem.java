package net.themcbrothers.uselessmod.world.item;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.LampRegistry;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;

import static net.themcbrothers.uselessmod.UselessMod.translate;

public class LightSwitchBlockItem extends BlockItem {
    public LightSwitchBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        List<BlockPos> lights = stack.get(UselessDataComponents.LIGHTS.get());

        if (level != null && lights != null) {
            if (GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
                for (BlockPos pos : lights) {
                    final BlockState state = level.getBlockState(pos);
                    final ItemStack cloneStack = state.getCloneItemStack(Minecraft.getInstance().hitResult, level, pos, UselessMod.setup.getLocalPlayer());
                    final String modId = cloneStack.getItem().getCreatorModId(cloneStack);
                    final MutableComponent displayComponent = Component.literal(pos.toShortString()).append(": ").append(state.getBlock().getName());

                    ModList.get().getModContainerById(modId).ifPresent(modContainer ->
                            displayComponent.append(" (").append(modContainer.getModInfo().getDisplayName()).append(")"));

                    tooltip.add(displayComponent.withStyle(ChatFormatting.GRAY));
                }
                tooltip.add(translate("tooltip", "light_switch.clear").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
            } else {
                tooltip.add(translate("tooltip", "hold_shift").withStyle(ChatFormatting.GRAY));
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final Level level = context.getLevel();
        final Player player = context.getPlayer();
        final BlockPos pos = context.getClickedPos();
        final BlockState state = level.getBlockState(pos);
        final ItemStack stack = context.getItemInHand();

        if (LampRegistry.isLampRegistered(state)) {
            List<BlockPos> existingLights = stack.get(UselessDataComponents.LIGHTS.get());
            List<BlockPos> lights = Lists.newArrayList();

            if (existingLights != null) {
                lights.addAll(existingLights);
            }

            Component statusMessage;

            if (lights.contains(pos)) {
                statusMessage = translate("status", "light_switch.block_already_added").withStyle(ChatFormatting.RED);
            } else {
                lights.add(pos);
                stack.set(UselessDataComponents.LIGHTS.get(), lights);
                statusMessage = translate("status", "light_switch.block_added", pos.toShortString());
            }

            if (player != null) {
                player.displayClientMessage(statusMessage, true);
            }

            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        final ItemStack stack = player.getItemInHand(usedHand);

        if (player.isSecondaryUseActive()) {
            stack.remove(UselessDataComponents.LIGHTS.get());
            player.displayClientMessage(translate("status", "light_switch.cleared"), true);
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return InteractionResultHolder.pass(stack);
    }
}
