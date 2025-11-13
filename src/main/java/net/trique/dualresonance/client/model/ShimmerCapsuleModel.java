package net.trique.dualresonance.client.model;

import net.minecraft.resources.ResourceLocation;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.item.gear.ShimmerCapsuleItem;
import software.bernie.geckolib.model.GeoModel;

public class ShimmerCapsuleModel extends GeoModel<ShimmerCapsuleItem> {

    private static final ResourceLocation MODEL =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "geo/shimmer_capsule.geo.json");

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "textures/geckolib/shimmer_capsule.png");

    private static final ResourceLocation ANIM =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "animations/shimmer_capsule.animation.json");

    @Override
    public ResourceLocation getModelResource(ShimmerCapsuleItem animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ShimmerCapsuleItem animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(ShimmerCapsuleItem animatable) {
        return ANIM;
    }
}
