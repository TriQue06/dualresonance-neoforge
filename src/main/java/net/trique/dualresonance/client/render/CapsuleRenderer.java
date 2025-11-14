package net.trique.dualresonance.client.render;

import net.trique.dualresonance.client.model.ShimmerCapsuleModel;
import net.trique.dualresonance.item.gear.ShimmerCapsuleItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CapsuleRenderer extends GeoItemRenderer<ShimmerCapsuleItem> {

    public CapsuleRenderer() {
        super(new ShimmerCapsuleModel());
    }
}
