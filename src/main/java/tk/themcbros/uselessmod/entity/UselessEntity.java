package tk.themcbros.uselessmod.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class UselessEntity extends CreatureEntity {
	
	public UselessEntity(EntityType<? extends CreatureEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.2D));
		this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2d);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(20.d);
	}

}
