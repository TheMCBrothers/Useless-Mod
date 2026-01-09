package net.themcbrothers.uselessmod.datagen.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.List;

public class UselessModelProvider extends ModelProvider {
    private final List<ModelSubProviderFactory> subProviders;

    private UselessModelProvider(PackOutput output, List<ModelSubProviderFactory> subProviders) {
        super(output, UselessMod.MOD_ID);
        this.subProviders = subProviders;
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (var subProvider : this.subProviders) {
            subProvider.create(blockModels, itemModels).register();
        }
    }

    public static Factory<DataProvider> create(ModelSubProviderFactory... subProviders) {
        var subProviderList = List.of(subProviders);
        return output -> new UselessModelProvider(output, subProviderList);
    }

    // This matches the super-class constructor of ModelSubProvider
    @FunctionalInterface
    public interface ModelSubProviderFactory {
        ModelSubProvider create(BlockModelGenerators blockModels, ItemModelGenerators itemModels);
    }
}
