package tk.themcbros.uselessmod.compat.mystical_agriculture;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.*;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;

@MysticalAgriculturePlugin
public class UselessMysticalPlugin implements IMysticalAgriculturePlugin {

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        ICrop uselessCrop = new Crop(this.getId("useless"), CropTier.THREE, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_INGOT_BLANK, CropTextures.SEED_BLANK), 0x468b44, LazyIngredient.tag("forge:ingots/useless"));
        ICrop superUselessCrop = new Crop(this.getId("super_useless"), CropTier.THREE, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_INGOT_BLANK, CropTextures.SEED_BLANK), 0x255123, LazyIngredient.tag("forge:ingots/super_useless"));
        ICrop uselessCowCrop = new Crop(this.getId("useless_cow"), CropTier.TWO, CropType.MOB, new CropTextures(UselessMod.getId("block/useless_cow_flower"), UselessMod.getId("item/useless_cow_essence"), UselessMod.getId("item/useless_cow_seeds")), -1, LazyIngredient.tag("forge:crops/useless_wheat"));
        registry.register(uselessCrop);
        registry.register(superUselessCrop);
        registry.register(uselessCowCrop);
    }

    private ResourceLocation getId(String path) {
        return new ResourceLocation(MysticalAgricultureAPI.MOD_ID, path);
    }

}
