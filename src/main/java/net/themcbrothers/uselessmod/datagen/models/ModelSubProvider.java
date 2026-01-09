package net.themcbrothers.uselessmod.datagen.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.resources.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ModelSubProvider {
    protected final BlockModelGenerators blockModels;
    protected final Consumer<BlockModelDefinitionGenerator> blockStateOutput;
    protected final ItemModelGenerators itemModels;
    protected final BiConsumer<Identifier, ModelInstance> modelOutput;

    protected ModelSubProvider(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.blockStateOutput = blockModels.blockStateOutput;
        this.itemModels = itemModels;
        this.modelOutput = blockModels.modelOutput;
    }

    protected abstract void register();
}
