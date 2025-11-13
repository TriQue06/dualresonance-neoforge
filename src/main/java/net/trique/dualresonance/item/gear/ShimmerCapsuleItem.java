package net.trique.dualresonance.item.gear;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.trique.dualresonance.effect.ModEffects;

public class ShimmerCapsuleItem extends Item implements ICurioItem, GeoItem {

    private static final RawAnimation ANIM =
            RawAnimation.begin().thenPlay("animation.shimmer_capsule.new");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ShimmerCapsuleItem(Properties props) {
        super(props);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "controller",
                        0,
                        state -> PlayState.CONTINUE
                ).triggerableAnim("new", ANIM)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private static final int TICK_INTERVAL = 20 * 5;
    private static final int EFFECT_DURATION = 20 * 5;
    private static final double CHANCE = 0.25;

    @Override
    public boolean canEquip(SlotContext ctx, ItemStack stack) {
        return ctx.identifier().equals("capsule");
    }

    @Override
    public void curioTick(SlotContext ctx, ItemStack stack) {
        LivingEntity entity = ctx.entity();
        Level level = entity.level();

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            GeoItem.getOrAssignId(stack, serverLevel);
        }

        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;

        if (level.getGameTime() % TICK_INTERVAL != 0) return;

        if (level.random.nextDouble() <= CHANCE) {
            player.addEffect(new MobEffectInstance(
                    ModEffects.SHIMMER_RAGE,
                    EFFECT_DURATION,
                    0,
                    false,
                    true,
                    true
            ));
        }
    }
}
