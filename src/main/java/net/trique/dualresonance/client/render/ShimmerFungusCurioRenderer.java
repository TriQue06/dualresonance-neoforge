package net.trique.dualresonance.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import software.bernie.geckolib.animatable.GeoItem;

public class ShimmerFungusCurioRenderer implements ICurioRenderer {

    private final FungusRenderer itemRenderer = new FungusRenderer();

    private long lastTrigger = 0;
    private static final int INTERVAL = 20 * 5;

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>>
    void render(ItemStack stack,
                SlotContext slotContext,
                PoseStack poseStack,
                RenderLayerParent<T, M> parent,
                MultiBufferSource buffer,
                int light,
                float limbSwing,
                float limbSwingAmount,
                float partialTicks,
                float ageInTicks,
                float netHeadYaw,
                float headPitch)
    {
        poseStack.pushPose();

        M model = parent.getModel();

        if (model instanceof HumanoidModel<?> humanoid) {

            ModelPart body = humanoid.body;
            body.translateAndRotate(poseStack);

            poseStack.translate(0.0f, 0.45f, -0.20f);

            poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
        }

        if (slotContext.entity().isCrouching()) {
            poseStack.translate(0f, 0.15f, 0.0f);
        }

        long time = Minecraft.getInstance().level.getGameTime();
        if (time - lastTrigger >= INTERVAL) {
            lastTrigger = time;

            long id = GeoItem.getId(stack);
            if (id != Long.MAX_VALUE) {
                ((GeoItem) stack.getItem()).triggerAnim(
                        slotContext.entity(),
                        id,
                        "controller",
                        "new"
                );
            }
        }

        itemRenderer.renderByItem(
                stack,
                ItemDisplayContext.NONE,
                poseStack,
                buffer,
                light,
                0
        );

        poseStack.popPose();
    }
}