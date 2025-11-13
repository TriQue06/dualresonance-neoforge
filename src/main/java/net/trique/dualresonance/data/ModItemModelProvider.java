package net.trique.dualresonance.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DualResonance.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generatedItem(ModItems.AZURE_CRYSTAL);
        generatedItem(ModItems.STABLE_AZURE_CORE);
        handheldItem(ModItems.AZURE_CORE_STAFF);
        generatedItem(ModItems.SHIMMER_POWDER);
        generatedItem(ModItems.SHIMMER_CAPSULE);
    }

    private ItemModelBuilder generatedItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}