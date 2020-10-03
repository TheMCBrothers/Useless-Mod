package themcbros.uselessmod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.EnchantmentInit;

import javax.annotation.Nonnull;

public class FlyerEnchantment extends Enchantment {

    public FlyerEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    protected boolean canApplyTogether(@Nonnull Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.THORNS;
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = UselessMod.MOD_ID)
    public static class FlyerEquipped {
        @SubscribeEvent
        public static void doStuff(final TickEvent.PlayerTickEvent event) {
            final PlayerEntity playerEntity = event.player;
            if (!playerEntity.abilities.isCreativeMode && !playerEntity.isSpectator()) {
                int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentInit.FLYER.get(), playerEntity);
                boolean flag = level > 0;
                boolean flag1 = playerEntity.abilities.allowFlying;
                boolean flag2 = playerEntity.abilities.isFlying;
                if (flag != flag1) {
                    playerEntity.abilities.allowFlying = flag;
                    if (flag != flag2)
                        playerEntity.abilities.isFlying = flag;
                }
                if (flag && playerEntity.abilities.isFlying && !playerEntity.world.isRemote)
                    playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST)
                            .attemptDamageItem(level, playerEntity.getRNG(), (ServerPlayerEntity) playerEntity);
            }
        }
    }

}
