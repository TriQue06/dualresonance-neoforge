package net.trique.dualresonance.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.dualresonance.DualResonance;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, DualResonance.MODID);

    public static final ResourceKey<MobEffect> SHIMMER_RAGE_KEY =
            ResourceKey.create(Registries.MOB_EFFECT,
                    ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "shimmer_rage"));

    public static final DeferredHolder<MobEffect, MobEffect> SHIMMER_RAGE =
            EFFECTS.register(SHIMMER_RAGE_KEY.location().getPath(), ShimmerRageEffect::new);
}