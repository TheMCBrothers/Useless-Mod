package tk.themcbros.uselessmod.client.models.block;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;

import javax.annotation.Nullable;

public class ClosetItemOverride extends ItemOverrideList {

    @Nullable
    @Override
    public IBakedModel getModelWithOverrides(IBakedModel modelOriginal, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
        if (modelOriginal instanceof NewClosetModel) {
            CompoundNBT tag = stack.getChildTag("uselessmod");
            if (tag != null) {
                IClosetMaterial casing = ClosetRegistry.CASINGS.get(tag.getString("casingId"));
                IClosetMaterial bedding = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId"));
                return ((NewClosetModel) modelOriginal).getCustomModel(casing, bedding, Direction.NORTH);
            }
        }
        return modelOriginal;
    }

}