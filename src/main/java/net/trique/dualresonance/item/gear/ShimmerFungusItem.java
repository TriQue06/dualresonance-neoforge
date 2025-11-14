package net.trique.dualresonance.item.gear;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.trique.dualresonance.effect.ModEffects;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ShimmerFungusItem extends Item implements ICurioItem, GeoItem {

    private static final RawAnimation ACTIVATE_ANIM =
            RawAnimation.begin().thenPlay("animation.shimmer_fungus.new");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ShimmerFungusItem(Properties props) {
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
                ).triggerableAnim("activate", ACTIVATE_ANIM)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean canEquip(SlotContext ctx, ItemStack stack) {
        return ctx.identifier().equals("belt");
    }

    @Override
    public void curioTick(SlotContext ctx, ItemStack stack) {}

    public void activate(Player player) {
        Level level = player.level();
        Vec3 pos = player.position();

        this.triggerAnim(player, 0, "controller", "activate");

        if (!level.isClientSide) {
            ServerLevel server = (ServerLevel) level;

            level.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.GENERIC_EXPLODE.value(),
                    SoundSource.PLAYERS,
                    1.2f,
                    0.7f
            );

            for (int i = 0; i < 80; i++) {
                double ox = (level.random.nextDouble() - 0.5) * 2.5;
                double oy = level.random.nextDouble() * 1.5;
                double oz = (level.random.nextDouble() - 0.5) * 2.5;

                server.sendParticles(
                        new DustParticleOptions(new Vec3(0.8f, 0.2f, 1.0f).toVector3f(), 1.0f),
                        pos.x + ox, pos.y + oy, pos.z + oz,
                        1, 0, 0, 0, 0.02
                );
            }

            double radius = 6.0;
            AABB box = new AABB(
                    pos.x - radius, pos.y - 2, pos.z - radius,
                    pos.x + radius, pos.y + 2, pos.z + radius
            );

            for (Entity e : level.getEntities(player, box)) {
                if (e instanceof LivingEntity living && living != player) {

                    Vec3 push = living.position()
                            .subtract(pos)
                            .normalize()
                            .scale(3.5);    // <-- DÜZELTİLDİ

                    living.setDeltaMovement(push.x, 1.6, push.z);
                    living.hurtMarked = true;
                }
            }

            player.addEffect(new MobEffectInstance(
                    ModEffects.SHIMMER_RAGE,
                    20 * 10,
                    0,
                    false,
                    true,
                    true
            ));
        }
    }
}