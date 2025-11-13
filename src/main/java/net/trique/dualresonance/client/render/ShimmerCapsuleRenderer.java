package net.trique.dualresonance.client.render;

import net.trique.dualresonance.client.model.ShimmerCapsuleModel;
import net.trique.dualresonance.item.gear.ShimmerCapsuleItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShimmerCapsuleRenderer extends GeoItemRenderer<ShimmerCapsuleItem> {

    public ShimmerCapsuleRenderer() {
        super(new ShimmerCapsuleModel());
    }
}
