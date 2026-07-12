package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends HumanoidModel<ClientDrowned> {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.rightArm = new ModelPart(this, 32, 48);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, f);
        this.rightArm.setPos(-5.0F, 2.0F + g, 0.0F);
        this.rightLeg = new ModelPart(this, 16, 48);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, f);
        this.rightLeg.setPos(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setupAnim(@NotNull ClientDrowned state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, h);
    }

    public void prepareMobModel(ClientDrowned zombie, float f, float g, float h) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;

        super.prepareMobModel(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
            this.rightArm.yRot = 0.0F;
        }

        if (this.swimAmount > 0.0F) {
            this.rightArm.xRot = this.rotlerpRad(this.swimAmount, this.rightArm.xRot, -2.5132742F) + this.swimAmount * 0.35F * Mth.sin(0.1F * h);
            this.leftArm.xRot = this.rotlerpRad(this.swimAmount, this.leftArm.xRot, -2.5132742F) - this.swimAmount * 0.35F * Mth.sin(0.1F * h);
            this.rightArm.zRot = this.rotlerpRad(this.swimAmount, this.rightArm.zRot, -0.15F);
            this.leftArm.zRot = this.rotlerpRad(this.swimAmount, this.leftArm.zRot, 0.15F);
            ModelPart var10000 = this.leftLeg;
            var10000.xRot -= this.swimAmount * 0.55F * Mth.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.xRot += this.swimAmount * 0.55F * Mth.sin(0.1F * h);
            this.head.xRot = 0.0F;
        }
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
