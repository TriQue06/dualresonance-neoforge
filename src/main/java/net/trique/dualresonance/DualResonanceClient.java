package net.trique.dualresonance;

import net.minecraft.client.particle.SonicBoomParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.trique.dualresonance.client.render.ShimmerCapsuleCurioRenderer;
import net.trique.dualresonance.client.render.ShimmerFungusCurioRenderer;
import net.trique.dualresonance.item.ModItems;
import net.trique.dualresonance.particle.ModParticles;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(value = DualResonance.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DualResonance.MODID, value = Dist.CLIENT)
public class DualResonanceClient {
    public DualResonanceClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.AZURE_BEAM.get(), SonicBoomParticle.Provider::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            // ✔ Shimmer Capsule Render
            CuriosRendererRegistry.register(
                    ModItems.SHIMMER_CAPSULE.get(),
                    ShimmerCapsuleCurioRenderer::new
            );

            // ✔ Shimmer Fungus Render
            CuriosRendererRegistry.register(
                    ModItems.SHIMMER_FUNGUS.get(),
                    ShimmerFungusCurioRenderer::new
            );
        });
    }
}