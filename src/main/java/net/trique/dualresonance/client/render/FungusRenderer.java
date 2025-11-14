package net.trique.dualresonance.client.render;

import net.trique.dualresonance.client.model.ShimmerCapsuleModel;
import net.trique.dualresonance.client.model.ShimmerFungusModel;
import net.trique.dualresonance.item.gear.ShimmerCapsuleItem;
import net.trique.dualresonance.item.gear.ShimmerFungusItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FungusRenderer extends GeoItemRenderer<ShimmerFungusItem> {

    public FungusRenderer() {
        super(new ShimmerFungusModel());
    }
}
