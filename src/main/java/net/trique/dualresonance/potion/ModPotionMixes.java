package net.trique.dualresonance.potion;

import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.trique.dualresonance.item.ModItems;

public class ModPotionMixes {
    @SubscribeEvent
    public static void register(RegisterBrewingRecipesEvent event) {

        var builder = event.getBuilder();

        builder.addMix(
                net.minecraft.world.item.alchemy.Potions.AWKWARD,
                ModItems.SHIMMER_POWDER.get(),
                ModPotions.SHIMMER_RAGE
        );

        builder.addMix(
                ModPotions.SHIMMER_RAGE,
                Items.REDSTONE,
                ModPotions.SHIMMER_RAGE_LONG
        );

        builder.addMix(
                ModPotions.SHIMMER_RAGE,
                Items.GLOWSTONE_DUST,
                ModPotions.SHIMMER_RAGE_STRONG
        );
    }
}