package net.trique.dualresonance.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.dualresonance.DualResonance;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DualResonance.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}