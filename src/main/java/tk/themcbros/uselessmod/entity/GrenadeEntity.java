package tk.themcbros.uselessmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.ModEntityTypes;
import tk.themcbros.uselessmod.lists.ModItems;

public class GrenadeEntity extends ProjectileItemEntity {
	
	public GrenadeEntity(EntityType<? extends GrenadeEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	public GrenadeEntity(World worldIn, LivingEntity throwerIn) {
		super(ModEntityTypes.GRENADE, throwerIn, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public GrenadeEntity(World worldIn, double x, double y, double z) {
		super(ModEntityTypes.GRENADE, x, y, z, worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		final BlockPos blockPos = new BlockPos(this);
		world.createExplosion(this, DamageSource.MAGIC, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 5, false, Mode.DESTROY);
		remove();
	}

	@Override
	protected Item func_213885_i() {
		return ModItems.GRENADE;
	}

	
	
}
