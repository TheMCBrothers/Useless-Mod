package tk.themcbros.uselessmod.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EnergySwordItem extends SwordItem implements IEnergyDurabilityItem {

    public EnergySwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        IEnergyDurabilityItem.super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return IEnergyDurabilityItem.super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return IEnergyDurabilityItem.super.isRepairable(stack);
    }

    @Override
    public boolean isDamageable() {
        return IEnergyDurabilityItem.super.isDamageable();
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack) {
        return IEnergyDurabilityItem.super.getRarity(stack);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return IEnergyDurabilityItem.super.initCapabilities(stack, nbt);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return IEnergyDurabilityItem.super.showDurabilityBar(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return IEnergyDurabilityItem.super.getRGBDurabilityForDisplay(stack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.extractEnergy(stack, RF_PER_USE, false);
        return this.hasEnoughEnergy(stack);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, World worldIn, BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        assert worldIn != null;
        if(!worldIn.isRemote && state.getBlockHardness(worldIn, pos) > 0.0f) {
            this.extractEnergy(stack, RF_PER_USE, false);
        }
        return this.hasEnoughEnergy(stack);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return this.canHarvestBlock(stack, state) ? super.getDestroySpeed(stack, state) : 1f;
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return this.hasEnoughEnergy(stack) && super.canHarvestBlock(stack, state);
    }

}
