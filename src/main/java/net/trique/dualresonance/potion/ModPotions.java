package net.trique.dualresonance.potion;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.effect.ModEffects;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, DualResonance.MODID);

    public static final DeferredHolder<Potion, Potion> SHIMMER_RAGE =
            POTIONS.register("shimmer_rage",
                    () -> new Potion(new MobEffectInstance(ModEffects.SHIMMER_RAGE, 20 * 60 * 3)));

    public static final DeferredHolder<Potion, Potion> SHIMMER_RAGE_LONG =
            POTIONS.register("shimmer_rage_long",
                    () -> new Potion(new MobEffectInstance(ModEffects.SHIMMER_RAGE, 20 * 60 * 8)));

    public static final DeferredHolder<Potion, Potion> SHIMMER_RAGE_STRONG =
            POTIONS.register("shimmer_rage_strong",
                    () -> new Potion(new MobEffectInstance(ModEffects.SHIMMER_RAGE, 20 * 30 * 3, 1)));
}
