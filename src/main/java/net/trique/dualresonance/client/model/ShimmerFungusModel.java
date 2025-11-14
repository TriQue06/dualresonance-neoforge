package net.trique.dualresonance.client.model;

import net.minecraft.resources.ResourceLocation;
import net.trique.dualresonance.DualResonance;
import net.trique.dualresonance.item.gear.ShimmerFungusItem;
import software.bernie.geckolib.model.GeoModel;

public class ShimmerFungusModel extends GeoModel<ShimmerFungusItem> {

    private static final ResourceLocation MODEL =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "geo/shimmer_mushroom.geo.json");

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "textures/geckolib/shimmer_mushroom.png");

    private static final ResourceLocation ANIM =
            ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "animations/shimmer_mushroom.animation.json");

    @Override
    public ResourceLocation getModelResource(ShimmerFungusItem animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ShimmerFungusItem animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(ShimmerFungusItem animatable) {
        return ANIM;
    }
}
