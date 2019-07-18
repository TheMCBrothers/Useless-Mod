package tk.themcbros.uselessmod.client.models.block;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.closet.ClosetRegistry;

public class ClosetItemOverride extends ItemOverrideList {

	@Override
	public IBakedModel getModelWithOverrides(IBakedModel modelOriginal, ItemStack stack, World worldIn, LivingEntity entityIn) {
		if(modelOriginal instanceof ClosetModel) {
			CompoundNBT tag = stack.getChildTag("uselessmod");
			if(tag != null) {
				String casingId = ClosetRegistry.CASINGS.get(tag.getString("casingId")).getTexture();
				String beddingId = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId")).getTexture();
				return ((ClosetModel) modelOriginal).getCustomModel(casingId, beddingId, Direction.EAST, true);
			}
		}
		
		return modelOriginal;
	}

}
