package themcbros.uselessmod.client.models.block.wall_closet;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;

import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class WallClosetItemOverride extends ItemOverrideList {

    @Nullable
    @Override
    public IBakedModel getOverrideModel(IBakedModel model, ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
        if (model instanceof WallClosetModel) {
            CompoundNBT tag = stack.getOrCreateTag();
            if (tag.contains("Material", Constants.NBT.TAG_STRING)) {
                ClosetMaterial material = UselessRegistries.CLOSET_MATERIALS.getValue(ResourceLocation.tryCreate(tag.getString("Material")));
                if (material != null)
                    return ((WallClosetModel) model).getCustomModel(material, Direction.NORTH);
            }
        }
        return model;
    }
}
