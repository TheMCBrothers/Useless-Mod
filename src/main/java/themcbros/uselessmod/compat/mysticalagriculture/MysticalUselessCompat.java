package themcbros.uselessmod.compat.mysticalagriculture;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import themcbros.uselessmod.UselessMod;

@MysticalAgriculturePlugin
public class MysticalUselessCompat implements IMysticalAgriculturePlugin {

    public MysticalUselessCompat() {}

    private static final CropTextures INGOT_CROP_TEXTURES;
    private static final Crop USELESS;
    private static final Crop SUPER_USELESS;
    private static final Crop USELESS_SHEEP;
    private static final Crop USELESS_PIG;
    private static final Crop USELESS_CHICKEN;
    private static final Crop USELESS_COW;
    private static final Crop USELESS_SKELETON;

    private static final MobSoulType USELESS_SHEEP_SOUL_TYPE = new MobSoulType(UselessMod.rl("useless_sheep"), UselessMod.rl("useless_sheep"), 10.0D, 15198183);
    private static final MobSoulType USELESS_PIG_SOUL_TYPE = new MobSoulType(UselessMod.rl("useless_pig"), UselessMod.rl("useless_pig"), 10.0D, 15771042);
    private static final MobSoulType USELESS_CHICKEN_SOUL_TYPE = new MobSoulType(UselessMod.rl("useless_chicken"), UselessMod.rl("useless_chicken"), 10.0D, 10592673);
    private static final MobSoulType USELESS_COW_SOUL_TYPE = new MobSoulType(UselessMod.rl("useless_cow"), UselessMod.rl("useless_cow"), 10.0D, 4470310);
    private static final MobSoulType USELESS_SKELETON_SOUL_TYPE = new MobSoulType(UselessMod.rl("useless_skeleton"), UselessMod.rl("useless_skeleton"), 10.0D, 12698049);

    static {
        INGOT_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_INGOT_BLANK);
        USELESS = new Crop(UselessMod.rl("useless"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x468b44, LazyIngredient.tag("forge:ingots/useless"));
        SUPER_USELESS = new Crop(UselessMod.rl("super_useless"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x255123, LazyIngredient.tag("forge:ingots/super_useless"));
        USELESS_SHEEP = new Crop(UselessMod.rl("useless_sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(USELESS_SHEEP_SOUL_TYPE)));
        USELESS_PIG = new Crop(UselessMod.rl("useless_pig"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(USELESS_PIG_SOUL_TYPE)));
        USELESS_CHICKEN = new Crop(UselessMod.rl("useless_chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(USELESS_CHICKEN_SOUL_TYPE)));
        USELESS_COW = new Crop(UselessMod.rl("useless_cow"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(USELESS_COW_SOUL_TYPE)));
        USELESS_SKELETON = new Crop(UselessMod.rl("useless_skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(USELESS_SKELETON_SOUL_TYPE)));
    }

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        registry.register(USELESS);
        registry.register(SUPER_USELESS);

        registry.register(USELESS_SHEEP);
        registry.register(USELESS_PIG);
        registry.register(USELESS_CHICKEN);
        registry.register(USELESS_COW);
        registry.register(USELESS_SKELETON);
    }

    @Override
    public void onRegisterMobSoulTypes(IMobSoulTypeRegistry registry) {
        registry.register(USELESS_SHEEP_SOUL_TYPE);
        registry.register(USELESS_PIG_SOUL_TYPE);
        registry.register(USELESS_CHICKEN_SOUL_TYPE);
        registry.register(USELESS_COW_SOUL_TYPE);
        registry.register(USELESS_SKELETON_SOUL_TYPE);
    }
}