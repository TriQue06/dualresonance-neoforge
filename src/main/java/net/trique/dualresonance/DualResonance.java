package net.trique.dualresonance;

import net.neoforged.fml.loading.FMLEnvironment;
import net.trique.dualresonance.client.ShimmerRageOverlay;
import net.trique.dualresonance.effect.ModEffects;
import net.trique.dualresonance.item.ModCreativeTabs;
import net.trique.dualresonance.item.ModItems;
import net.trique.dualresonance.particle.ModParticles;
import net.trique.dualresonance.potion.ModPotionMixes;
import net.trique.dualresonance.potion.ModPotions;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(DualResonance.MODID)
public class DualResonance {
    public static final String MODID = "dualresonance";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DualResonance(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.addListener(ModPotionMixes::register);

        if (FMLEnvironment.dist.isClient()) {
            NeoForge.EVENT_BUS.register(new ShimmerRageOverlay());
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Dual Resonance setup complete.");
    }
}