package net.trique.dualresonance.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.trique.dualresonance.DualResonance;

public class ShimmerRageEffect extends MobEffect {

    private static final ResourceLocation MOVE_ID  = ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "shimmer_rage_move");
    private static final ResourceLocation ATKSPD_ID= ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "shimmer_rage_attack_speed");
    private static final ResourceLocation ATKDAM_ID= ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "shimmer_rage_attack_damage");
    private static final ResourceLocation SCALE_ID = ResourceLocation.fromNamespaceAndPath(DualResonance.MODID, "shimmer_rage_scale");

    private static final double MOVE_BUFF = 0.25D;
    private static final double ATKDAM_BUFF = 1.0D;
    private static final double ATKSPD_BUFF = 0.5D;
    private static final double SCALE_BUFF = 1.0D;

    public ShimmerRageEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFF336FF);

        addAttributeModifier(Attributes.MOVEMENT_SPEED, MOVE_ID, MOVE_BUFF, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addAttributeModifier(Attributes.ATTACK_SPEED, ATKSPD_ID, ATKSPD_BUFF, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, ATKDAM_ID, ATKDAM_BUFF, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addAttributeModifier(Attributes.SCALE, SCALE_ID, SCALE_BUFF, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}