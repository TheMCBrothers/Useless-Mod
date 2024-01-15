package net.themcbrothers.uselessmod.compat.jade;

import net.themcbrothers.uselessmod.world.level.block.CoffeeMachineBlock;
import net.themcbrothers.uselessmod.world.level.block.CupBlock;
import net.themcbrothers.uselessmod.world.level.block.PaintedWoolBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class UselessJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(CoffeeMachineProvider.INSTANCE, CoffeeMachineBlockEntity.class);
        registration.registerBlockDataProvider(CoffeeCupProvider.INSTANCE, CupBlockEntity.class);
        registration.registerBlockDataProvider(PaintedWoolProvider.INSTANCE, PaintedWoolBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(CoffeeMachineProvider.INSTANCE, CoffeeMachineBlock.class);
        registration.registerBlockComponent(CoffeeCupProvider.INSTANCE, CupBlock.class);
        registration.registerBlockComponent(PaintedWoolProvider.INSTANCE, PaintedWoolBlock.class);
    }
}
