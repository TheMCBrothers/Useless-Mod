package themcbros.uselessmod.client.models.block.supplier;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class MachineSupplierModelOverride extends ItemOverrideList {
    @Nullable
    @Override
    public IBakedModel getOverrideModel(IBakedModel modelOriginal, ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
        CompoundNBT tag = stack.getChildTag("BlockEntityTag");
        if (tag != null && tag.contains("Mimic", Constants.NBT.TAG_COMPOUND)) {
            BlockState mimic = NBTUtil.readBlockState(tag.getCompound("Mimic"));
            return Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(mimic);
        }
        return modelOriginal;
    }
}
