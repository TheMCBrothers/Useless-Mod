package themcbros.uselessmod.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UselessPortalParticle extends SpriteTexturedParticle {

    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    protected UselessPortalParticle(ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        this.particleScale = 0.1F * (this.rand.nextFloat() * 0.2F + 0.5F);
        float gray = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = gray * 0.3F;
        this.particleGreen = gray * 0.9F;
        this.particleBlue = gray * 0.3F;
        this.maxAge = (int)(Math.random() * 10.0D) + 40;
    }

    @Override
    public void move(double p_187110_1_, double p_187110_3_, double p_187110_5_) {
        this.setBoundingBox(this.getBoundingBox().offset(p_187110_1_, p_187110_3_, p_187110_5_));
        this.resetPositionToBB();
    }

    @Override
    public float getScale(float p_217561_1_) {
        float lvt_2_1_ = ((float)this.age + p_217561_1_) / (float)this.maxAge;
        lvt_2_1_ = 1.0F - lvt_2_1_;
        lvt_2_1_ *= lvt_2_1_;
        lvt_2_1_ = 1.0F - lvt_2_1_;
        return this.particleScale * lvt_2_1_;
    }

    @Override
    protected int getBrightnessForRender(float p_189214_1_) {
        int lvt_2_1_ = super.getBrightnessForRender(p_189214_1_);
        float lvt_3_1_ = (float)this.age / (float)this.maxAge;
        lvt_3_1_ *= lvt_3_1_;
        lvt_3_1_ *= lvt_3_1_;
        int lvt_4_1_ = lvt_2_1_ & 255;
        int lvt_5_1_ = lvt_2_1_ >> 16 & 255;
        lvt_5_1_ += (int)(lvt_3_1_ * 15.0F * 16.0F);
        if (lvt_5_1_ > 240) {
            lvt_5_1_ = 240;
        }

        return lvt_4_1_ | lvt_5_1_ << 16;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            float lvt_1_1_ = (float)this.age / (float)this.maxAge;
            float lvt_2_1_ = lvt_1_1_;
            lvt_1_1_ = -lvt_1_1_ + lvt_1_1_ * lvt_1_1_ * 2.0F;
            lvt_1_1_ = 1.0F - lvt_1_1_;
            this.posX = this.portalPosX + this.motionX * (double)lvt_1_1_;
            this.posY = this.portalPosY + this.motionY * (double)lvt_1_1_ + (double)(1.0F - lvt_2_1_);
            this.posZ = this.portalPosZ + this.motionZ * (double)lvt_1_1_;
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite animatedSprite) {
            this.spriteSet = animatedSprite;
        }

        public Particle makeParticle(BasicParticleType particleType, ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
            UselessPortalParticle lvt_15_1_ = new UselessPortalParticle(world, posX, posY, posZ, motionX, motionY, motionZ);
            lvt_15_1_.selectSpriteRandomly(this.spriteSet);
            return lvt_15_1_;
        }
    }
}
