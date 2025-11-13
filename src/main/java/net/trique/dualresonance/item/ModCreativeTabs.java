package net.trique.dualresonance.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.potion.ModPotions;

import java.util.function.Supplier;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DualResonance.MODID);

    private static ItemStack potionStack(Holder<Potion> potion, Item container) {
        return PotionContents.createItemStack(container, potion);
    }

    public static final Supplier<CreativeModeTab> MOD_CREATIVE_TAB =
            CREATIVE_MODE_TAB.register("modcreativetab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.SHIMMER_POWDER.get()))
                            .title(Component.translatable("dualresonance.creativetab"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.AZURE_CRYSTAL);
                                output.accept(ModItems.STABLE_AZURE_CORE);

                                output.accept(ModItems.AZURE_CORE_STAFF);

                                output.accept(ModItems.SHIMMER_POWDER);

                                output.accept(ModItems.SHIMMER_CAPSULE);

                                Holder<Potion> normal   = ModPotions.SHIMMER_RAGE;
                                Holder<Potion> longPot  = ModPotions.SHIMMER_RAGE_LONG;
                                Holder<Potion> strong   = ModPotions.SHIMMER_RAGE_STRONG;

                                output.accept(potionStack(normal, Items.POTION));
                                output.accept(potionStack(normal, Items.SPLASH_POTION));
                                output.accept(potionStack(normal, Items.LINGERING_POTION));

                                output.accept(potionStack(longPot, Items.POTION));
                                output.accept(potionStack(longPot, Items.SPLASH_POTION));
                                output.accept(potionStack(longPot, Items.LINGERING_POTION));

                                output.accept(potionStack(strong, Items.POTION));
                                output.accept(potionStack(strong, Items.SPLASH_POTION));
                                output.accept(potionStack(strong, Items.LINGERING_POTION));
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
