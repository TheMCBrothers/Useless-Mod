package themcbros.uselessmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.useless_mana.WorldMana;
import themcbros.uselessmod.useless_mana.player.PlayerMana;
import themcbros.uselessmod.useless_mana.player.PlayerProperties;
import themcbros.uselessmod.util.Styles;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class UselessItemItem extends Item {

    private static final float MANA_CONSUME = 3.0F;

    public static float playerMana;

    public UselessItemItem() {
        super(new Properties().group(UselessMod.GROUP).maxStackSize(1));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTag().putInt("Mode", Mode.ENERGY.ordinal());
        return stack;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            stack.getOrCreateTag().putInt("Mode", Mode.ENERGY.ordinal());
            items.add(stack);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getHeldItem(handIn);

        if (getMode(stack) == Mode.MANA) {
            if (player.isCrouching()) {
                if (!worldIn.isRemote && worldIn instanceof ServerWorld) {
                    WorldMana worldMana = WorldMana.get((ServerWorld) worldIn);
                    float amount = worldMana.extractMana((ServerWorld) worldIn, player.getPosition());
                    PlayerMana playerMana = PlayerProperties.getPlayerMana(player);
                    playerMana.setMana(playerMana.getMana() + amount);
                    if (amount > 0) worldIn.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            } else {
                if (!worldIn.isRemote) {
                    PlayerMana playerMana = PlayerProperties.getPlayerMana(player);
                    if (playerMana.getMana() >= MANA_CONSUME) {
                        worldIn.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        player.experienceLevel += 1;
                        playerMana.setMana(playerMana.getMana() - MANA_CONSUME);
                    }
                }
            }
            return ActionResult.resultSuccess(stack);
        }

        return ActionResult.resultPass(stack);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            ItemStack stack = player.getHeldItem(context.getHand());
            if (player.isCrouching()) {
                cycleMode(stack);
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        Mode mode = this.getMode(stack);
        tooltip.add((new TranslationTextComponent("item.uselessmod.useless_item.mode").append(new StringTextComponent(": ")))
                .setStyle(Styles.TOOLTIP)
                .append(new StringTextComponent(mode.name()).setStyle(mode.style)));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.getMode(stack) == Mode.MANA && this.getDurabilityForDisplay(stack) <= 0;
    }

    public Mode getMode(ItemStack stack) {
        if (stack.getOrCreateTag().contains("Mode", Constants.NBT.TAG_INT))
            return Mode.values()[stack.getOrCreateTag().getInt("Mode")];
        return Mode.ENERGY;
    }

    public void cycleMode(ItemStack stack) {
        int mode = 0;
        if (stack.getOrCreateTag().contains("Mode", Constants.NBT.TAG_INT))
            mode = stack.getOrCreateTag().getInt("Mode");
        mode++;
        if (mode >= Mode.values().length) mode = 0;
        stack.getOrCreateTag().putInt("Mode", mode);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return this.getMode(stack) == Mode.MANA;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0xFF62B15F;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        float maxMana = MANA_CONSUME;
        float currentMana = maxMana - playerMana;
        double result = currentMana != 0 ? currentMana / maxMana : 0;
        return result > 1 ? 1 : result < 0 ? 0 : result;
    }

    public enum Mode implements IStringSerializable {
        MANA(Styles.MODE_MANA),
        FLUID(Styles.MODE_FLUID),
        ENERGY(Styles.FORGE_ENERGY),
        ENTITY(Styles.MODE_ENTITY);

        private final Style style;

        Mode(Style style) {
            this.style = style;
        }

        @Override
        public String getString() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
