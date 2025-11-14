package net.trique.dualresonance.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.item.gear.AzureCoreStaffItem;
import net.trique.dualresonance.item.gear.ShimmerCapsuleItem;
import net.trique.dualresonance.item.gear.ShimmerFungusItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(DualResonance.MODID);

    public static final DeferredItem<Item> AZURE_CRYSTAL = ITEMS.register("azure_crystal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STABLE_AZURE_CORE = ITEMS.register("stable_azure_core",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AZURE_CORE_STAFF = ITEMS.register("azure_core_staff",
            () -> new AzureCoreStaffItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SHIMMER_POWDER = ITEMS.register("shimmer_powder",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SHIMMER_CAPSULE = ITEMS.register("shimmer_capsule",
            () -> new ShimmerCapsuleItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SHIMMER_FUNGUS = ITEMS.register("shimmer_fungus",
            () -> new ShimmerFungusItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}