package themcbros.uselessmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.particle.UselessPortalParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID, value = Dist.CLIENT)
public class ParticleInit {

    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UselessMod.MOD_ID);

    public static final RegistryObject<BasicParticleType> USELESS_PORTAL = REGISTER.register("useless_portal", () -> new BasicParticleType(false));

    // Registration
    @SubscribeEvent
    public static void registerParticles(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleInit.USELESS_PORTAL.get(), UselessPortalParticle.Factory::new);
    }

}
