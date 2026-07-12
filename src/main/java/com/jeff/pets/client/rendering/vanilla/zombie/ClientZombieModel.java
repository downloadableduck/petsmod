package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.mob.AbstractPet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieModel<T extends AbstractPet> extends HumanoidModel<@NotNull T> {

    public ClientZombieModel(float f) {
        super(f);
    }

    @Override
    public void setupAnim(T state, float f, float g, float h, float i, float j) {
        super.setupAnim(state, f, g, h, i, j);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, h);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
        poseStack.pushPose();
        if (CONFIG.isBaby) {
            poseStack.scale(1.5f, 1.5f, 1.5f);
        } else {
            poseStack.scale(1, 1, 1);
        }
        this.head.translateAndRotate(poseStack);
        poseStack.popPose();
    }
}
