package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModEntityTypes;

public class UselessPig extends Pig {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);

    public UselessPig(EntityType<? extends Pig> type, Level level) {
        super(type, level);
    }

    @Override
    public Pig getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return ModEntityTypes.USELESS_PIG.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }
}
