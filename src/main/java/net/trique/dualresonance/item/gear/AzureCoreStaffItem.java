package net.trique.dualresonance.item.gear;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.trique.dualresonance.item.ModItems;
import net.trique.dualresonance.particle.ModParticles;

import java.util.HashSet;
import java.util.Set;

public class AzureCoreStaffItem extends Item {

    public static final float MAGIC_DAMAGE = 4.0F;
    public static final int COOLDOWN_TICKS = 18 * 20;
    public static final int RANGE = 22;
    public static final double ENTITY_HIT_RADIUS = 1.1D;
    public static final float PARTICLE_HEIGHT_OFFSET = 1.55F;
    public static final float KNOCKBACK = 1.3F;
    public static final float VERTICAL_BOOST = 0.22F;
    public static final float BURST_RADIUS = 2.8F;

    public AzureCoreStaffItem(Properties props) {
        super(props.attributes(createAttributeModifiers()));
    }

    private static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder().build();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 20;
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remaining) {
        if (getUseDuration(stack, user) - remaining == 1) {
            level.playSound(
                    null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS,
                    1.8F, 1.35F
            );
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (!level.isClientSide && user instanceof Player player) {

            boolean creative = player.getAbilities().instabuild;
            ItemStack fuel = findFuel(player);

            if (creative || !fuel.isEmpty()) {

                fireAzureBeam(level, user);

                if (!creative) {
                    fuel.shrink(1);
                    stack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
                    player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
                }
            }
        }
        return stack;
    }

    private ItemStack findFuel(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (s.is(ModItems.STABLE_AZURE_CORE.get()) || s.is(ModItems.AZURE_CRYSTAL.get()))
                return s;
        }
        return ItemStack.EMPTY;
    }

    private void fireAzureBeam(Level level, LivingEntity user) {

        level.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 3.0F, 1.1F);

        Vec3 source = user.position().add(0, PARTICLE_HEIGHT_OFFSET, 0);
        Vec3 target = user.position().add(user.getLookAngle().scale(RANGE));
        Vec3 direction = target.subtract(source).normalize();

        Set<Entity> hitEntities = new HashSet<>();
        int steps = Mth.floor(RANGE * 1.4f);

        for (int i = 0; i <= steps; i++) {
            Vec3 p = source.add(direction.scale(i));

            if (level instanceof ServerLevel sl) {
                sl.sendParticles(
                        ModParticles.AZURE_BEAM.get(),
                        p.x, p.y, p.z,
                        1, 0, 0, 0, 0
                );
            }

            BlockPos bp = BlockPos.containing(p);
            AABB hitBox = new AABB(bp).inflate(ENTITY_HIT_RADIUS);

            hitEntities.addAll(level.getEntitiesOfClass(LivingEntity.class, hitBox, e -> e != user));
        }

        DamageSources src = level.damageSources();

        for (Entity e : hitEntities) {
            if (e instanceof LivingEntity living) {

                living.hurt(src.magic(), MAGIC_DAMAGE);

                double resist = living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                living.push(
                        direction.x * KNOCKBACK * (1 - resist),
                        VERTICAL_BOOST * (1 - resist),
                        direction.z * KNOCKBACK * (1 - resist)
                );
                living.hasImpulse = true;

                if (level instanceof ServerLevel sl) {
                    spawnAzureBurst(sl, living.getX(), living.getY() + 1.0, living.getZ());
                }
            }
        }
    }

    private void spawnAzureBurst(ServerLevel sl, double x, double y, double z) {
        int count = 40;
        for (int i = 0; i < count; i++) {
            double rx = (sl.random.nextDouble() - 0.5) * BURST_RADIUS;
            double ry = (sl.random.nextDouble() - 0.5) * BURST_RADIUS * 0.6;
            double rz = (sl.random.nextDouble() - 0.5) * BURST_RADIUS;

            sl.sendParticles(
                    ModParticles.AZURE_BEAM.get(),
                    x + rx, y + ry, z + rz,
                    1, 0, 0, 0, 0
            );
        }
    }
}