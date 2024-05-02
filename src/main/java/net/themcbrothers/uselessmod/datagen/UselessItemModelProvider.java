package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessFluids;
import net.themcbrothers.uselessmod.core.UselessItems;

public class UselessItemModelProvider extends ItemModelProvider {
    public UselessItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(UselessItems.RAW_USELESS.get());
        basicItem(UselessItems.RAW_SUPER_USELESS.get());
        basicItem(UselessItems.USELESS_DUST.get());
        basicItem(UselessItems.SUPER_USELESS_DUST.get());
        basicItem(UselessItems.USELESS_INGOT.get());
        basicItem(UselessItems.SUPER_USELESS_INGOT.get());
        basicItem(UselessItems.USELESS_NUGGET.get());
        basicItem(UselessItems.SUPER_USELESS_NUGGET.get());
        basicItem(UselessBlocks.USELESS_OAK_DOOR.asItem());
        basicItem(UselessItems.USELESS_SHEARS.get());
        basicTool(UselessItems.USELESS_SWORD.get());
        basicTool(UselessItems.SUPER_USELESS_SWORD.get());
        basicTool(UselessItems.USELESS_SHOVEL.get());
        basicTool(UselessItems.SUPER_USELESS_SHOVEL.get());
        basicTool(UselessItems.USELESS_PICKAXE.get());
        basicTool(UselessItems.SUPER_USELESS_PICKAXE.get());
        basicTool(UselessItems.USELESS_AXE.get());
        basicTool(UselessItems.SUPER_USELESS_AXE.get());
        basicTool(UselessItems.USELESS_HOE.get());
        basicTool(UselessItems.SUPER_USELESS_HOE.get());
        basicItem(UselessItems.USELESS_HELMET.get());
        basicItem(UselessItems.SUPER_USELESS_HELMET.get());
        basicItem(UselessItems.USELESS_CHESTPLATE.get());
        basicItem(UselessItems.SUPER_USELESS_CHESTPLATE.get());
        basicItem(UselessItems.USELESS_LEGGINGS.get());
        basicItem(UselessItems.SUPER_USELESS_LEGGINGS.get());
        basicItem(UselessItems.USELESS_BOOTS.get());
        basicItem(UselessItems.SUPER_USELESS_BOOTS.get());
        basicItem(UselessItems.USELESS_WHEAT_SEEDS.get());
        basicItem(UselessItems.USELESS_WHEAT.get());
        basicItem(UselessItems.COFFEE_SEEDS.get());
        basicItem(UselessItems.COFFEE_BEANS.get());
        basicItem(UselessItems.USELESS_BONE.get());
        basicItem(UselessItems.USELESS_LEATHER.get());
        basicItem(UselessItems.USELESS_FEATHER.get());

        final ModelFile brokenUselessElytra = basicItem(UselessMod.rl("broken_useless_elytra"));
        basicItem(UselessItems.USELESS_ELYTRA.get()).override().predicate(mcLoc("broken"), 1).model(brokenUselessElytra).end();
        final ModelFile brokenSuperUselessElytra = basicItem(UselessMod.rl("broken_super_useless_elytra"));
        basicItem(UselessItems.SUPER_USELESS_ELYTRA.get()).override().predicate(mcLoc("broken"), 1).model(brokenSuperUselessElytra).end();

        withExistingParent("paint_brush", mcLoc(ITEM_FOLDER + "/handheld"))
                .texture("layer0", modLoc(ITEM_FOLDER + "/paint_brush_0"))
                .texture("layer1", modLoc(ITEM_FOLDER + "/paint_brush_1"));

        getBuilder(String.valueOf(UselessItems.USELESS_SKELETON_SKULL.getId())).parent(getExistingFile(mcLoc(ITEM_FOLDER + "/template_skull")));

        this.withExistingParent("useless_sheep_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_pig_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_chicken_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_cow_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_skeleton_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));

        this.withExistingParent("bucket_paint", new ResourceLocation(NeoForgeVersion.MOD_ID, "item/bucket"))
                .customLoader(DynamicFluidContainerModelBuilder::begin)
                .fluid(UselessFluids.PAINT.get()).end();

        // Items with special renderer
        final ModelFile builtInEntityModel = new ModelFile.UncheckedModelFile("builtin/entity");

        final ModelFile uselessShieldBlocking = shieldBlockingModel("useless_shield_blocking", builtInEntityModel, "useless_block");
        shieldModel("useless_shield", builtInEntityModel, "useless_block", uselessShieldBlocking);

        final ModelFile superUselessShieldBlocking = shieldBlockingModel("super_useless_shield_blocking", builtInEntityModel, "super_useless_block");
        shieldModel("super_useless_shield", builtInEntityModel, "super_useless_block", superUselessShieldBlocking);
    }

    private void basicTool(ItemLike item) {
        final ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.asItem());
        this.singleTexture(id.getPath(), mcLoc(ITEM_FOLDER + "/handheld"), "layer0", modLoc(ITEM_FOLDER + "/" + id.getPath()));
    }

    private void shieldModel(String path, ModelFile parent, String particleTexture, ModelFile blockingModel) {
        getBuilder(path)
                .parent(parent).guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", modLoc(BLOCK_FOLDER + "/" + particleTexture))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(0, 90, 0).translation(10, 6, -4).scale(1, 1, 1).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(0, 90, 0).translation(10, 6, 12).scale(1, 1, 1).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 180, 5).translation(-10, 2, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 180, 5).translation(10, 0, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.GUI)
                .rotation(15, -25, -5).translation(2, 3, 0).scale(0.65F, 0.65F, 0.65F).end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0, 180, 0).translation(-2, 4, -5).scale(0.5F, 0.5F, 0.5F).end()
                .transform(ItemDisplayContext.GROUND)
                .rotation(0, 0, 0).translation(4, 4, 2).scale(0.25F, 0.25F, 0.25F).end()
                .end()
                .override().predicate(mcLoc("blocking"), 1).model(blockingModel).end();
    }

    private ModelFile shieldBlockingModel(String path, ModelFile parent, String particleTexture) {
        return getBuilder(path)
                .parent(parent).guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", modLoc(BLOCK_FOLDER + "/" + particleTexture))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(45, 135, 0).translation(3.51F, 11, -2).scale(1, 1, 1).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(45, 135, 0).translation(13.51F, 3, 5).scale(1, 1, 1).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 180, -5).translation(-15, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 180, -5).translation(5, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.GUI)
                .rotation(15, -25, -5).translation(2, 3, 0).scale(0.65F, 0.65F, 0.65F).end()
                .end();
    }
}
