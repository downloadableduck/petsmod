package com.jeff.pets.rendering.vanilla.enderdragon;

import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class ClientEnderDragonModel extends EntityModel<ClientEnderDragon> {
    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart jaw;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart leftWingTip;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontLegTip;
    private final ModelPart leftFrontFoot;
    private final ModelPart leftRearLeg;
    private final ModelPart leftRearLegTip;
    private final ModelPart leftRearFoot;
    private final ModelPart rightWing;
    private final ModelPart rightWingTip;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontLegTip;
    private final ModelPart rightFrontFoot;
    private final ModelPart rightRearLeg;
    private final ModelPart rightRearLegTip;
    private final ModelPart rightRearFoot;
    private final ModelPart root;
    @Nullable
    private ClientEnderDragon entity;
    private float a;

    public ClientEnderDragonModel(ModelPart modelPart) {
        this.root = modelPart;
        this.head = modelPart.getChild("head");
        this.jaw = this.head.getChild("jaw");
        this.neck = modelPart.getChild("neck");
        this.body = modelPart.getChild("body");
        this.leftWing = modelPart.getChild("left_wing");
        this.leftWingTip = this.leftWing.getChild("left_wing_tip");
        this.leftFrontLeg = modelPart.getChild("left_front_leg");
        this.leftFrontLegTip = this.leftFrontLeg.getChild("left_front_leg_tip");
        this.leftFrontFoot = this.leftFrontLegTip.getChild("left_front_foot");
        this.leftRearLeg = modelPart.getChild("left_hind_leg");
        this.leftRearLegTip = this.leftRearLeg.getChild("left_hind_leg_tip");
        this.leftRearFoot = this.leftRearLegTip.getChild("left_hind_foot");
        this.rightWing = modelPart.getChild("right_wing");
        this.rightWingTip = this.rightWing.getChild("right_wing_tip");
        this.rightFrontLeg = modelPart.getChild("right_front_leg");
        this.rightFrontLegTip = this.rightFrontLeg.getChild("right_front_leg_tip");
        this.rightFrontFoot = this.rightFrontLegTip.getChild("right_front_foot");
        this.rightRearLeg = modelPart.getChild("right_hind_leg");
        this.rightRearLegTip = this.rightRearLeg.getChild("right_hind_leg_tip");
        this.rightRearFoot = this.rightRearLegTip.getChild("right_hind_foot");
    }

    public void prepareMobModel(ClientEnderDragon enderDragon, float f, float g, float h) {
        this.entity = enderDragon;
        this.a = h;
    }

    public void setupAnim(ClientEnderDragon enderDragon, float f, float g, float h, float i, float j) {
    }

    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
        if (this.entity == null) {
            this.root.render(poseStack, vertexConsumer, i, j);
            return;
        }
        poseStack.pushPose();
        float f = Mth.lerp(this.a, this.entity.oFlapTime, this.entity.flapTime);
        this.jaw.xRot = (float) (Math.sin(f * ((float) Math.PI * 2F)) + (double) 1.0F) * 0.2F;
        float g = (float) (Math.sin(f * ((float) Math.PI * 2F) - 1.0F) + (double) 1.0F);
        g = (g * g + g * 2.0F) * 0.05F;
        poseStack.translate(0.0F, g - 2.0F, -3.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(g * 2.0F));
        float h = 0.0F;
        float l = 20.0F;
        float m = -12.0F;
        float n = 1.5F;
        double[] ds = this.entity.getLatencyPos(6, this.a);
        float o = Mth.wrapDegrees((float) (this.entity.getLatencyPos(5, this.a)[0] - this.entity.getLatencyPos(10, this.a)[0]));
        float p = Mth.wrapDegrees((float) (this.entity.getLatencyPos(5, this.a)[0] + (double) (o / 2.0F)));
        float q = f * ((float) Math.PI * 2F);

        for (int r = 0; r < 5; ++r) {
            double[] es = this.entity.getLatencyPos(5 - r, this.a);
            float s = (float) Math.cos((float) r * 0.45F + q) * 0.15F;
            this.neck.yRot = Mth.wrapDegrees((float) (es[0] - ds[0])) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.xRot = s + this.entity.getHeadPartYOffset(r, ds, es) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = -Mth.wrapDegrees((float) (es[0] - (double) p)) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.y = l;
            this.neck.z = m;
            this.neck.x = h;
            l += Mth.sin(this.neck.xRot) * 10.0F;
            m -= Mth.cos(this.neck.yRot) * Mth.cos(this.neck.xRot) * 10.0F;
            h -= Mth.sin(this.neck.yRot) * Mth.cos(this.neck.xRot) * 10.0F;
            this.neck.render(poseStack, vertexConsumer, i, j, k);
        }

        this.head.y = l;
        this.head.z = m;
        this.head.x = h;
        double[] fs = this.entity.getLatencyPos(0, this.a);
        this.head.yRot = Mth.wrapDegrees((float) (fs[0] - ds[0])) * ((float) Math.PI / 180F);
        this.head.xRot = Mth.wrapDegrees(this.entity.getHeadPartYOffset(6, ds, fs)) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
        this.head.zRot = -Mth.wrapDegrees((float) (fs[0] - (double) p)) * ((float) Math.PI / 180F);
        this.head.render(poseStack, vertexConsumer, i, j, k);
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.0F, 0.0F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(-o * 1.5F));
        poseStack.translate(0.0F, -1.0F, 0.0F);
        this.body.zRot = 0.0F;
        this.body.render(poseStack, vertexConsumer, i, j, k);
        float t = f * ((float) Math.PI * 2F);
        this.leftWing.xRot = 0.125F - (float) Math.cos(t) * 0.2F;
        this.leftWing.yRot = -0.25F;
        this.leftWing.zRot = -((float) (Math.sin(t) + (double) 0.125F)) * 0.8F;
        this.leftWingTip.zRot = (float) (Math.sin(t + 2.0F) + (double) 0.5F) * 0.75F;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.zRot = -this.leftWing.zRot;
        this.rightWingTip.zRot = -this.leftWingTip.zRot;
        this.renderSide(poseStack, vertexConsumer, i, j, g, this.leftWing, this.leftFrontLeg, this.leftFrontLegTip, this.leftFrontFoot, this.leftRearLeg, this.leftRearLegTip, this.leftRearFoot, k);
        this.renderSide(poseStack, vertexConsumer, i, j, g, this.rightWing, this.rightFrontLeg, this.rightFrontLegTip, this.rightFrontFoot, this.rightRearLeg, this.rightRearLegTip, this.rightRearFoot, k);
        poseStack.popPose();
        float s = -Mth.sin(f * ((float) Math.PI * 2F)) * 0.0F;
        q = f * ((float) Math.PI * 2F);
        l = 10.0F;
        m = 60.0F;
        h = 0.0F;
        ds = this.entity.getLatencyPos(11, this.a);

        for (int u = 0; u < 12; ++u) {
            fs = this.entity.getLatencyPos(12 + u, this.a);
            s += Mth.sin((float) u * 0.45F + q) * 0.05F;
            this.neck.yRot = (Mth.wrapDegrees((float) (fs[0] - ds[0])) * 1.5F + 180.0F) * ((float) Math.PI / 180F);
            this.neck.xRot = s + (float) (fs[1] - ds[1]) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = Mth.wrapDegrees((float) (fs[0] - (double) p)) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.y = l;
            this.neck.z = m;
            this.neck.x = h;
            l += Mth.sin(this.neck.xRot) * 10.0F;
            m -= Mth.cos(this.neck.yRot) * Mth.cos(this.neck.xRot) * 10.0F;
            h -= Mth.sin(this.neck.yRot) * Mth.cos(this.neck.xRot) * 10.0F;
            this.neck.render(poseStack, vertexConsumer, i, j, k);
        }

        poseStack.popPose();
    }

    private void renderSide(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, ModelPart modelPart, ModelPart modelPart2, ModelPart modelPart3, ModelPart modelPart4, ModelPart modelPart5, ModelPart modelPart6, ModelPart modelPart7, int k) {
        modelPart5.xRot = 1.0F + f * 0.1F;
        modelPart6.xRot = 0.5F + f * 0.1F;
        modelPart7.xRot = 0.75F + f * 0.1F;
        modelPart2.xRot = 1.3F + f * 0.1F;
        modelPart3.xRot = -0.5F - f * 0.1F;
        modelPart4.xRot = 0.75F + f * 0.1F;
        modelPart.render(poseStack, vertexConsumer, i, j, k);
        modelPart2.render(poseStack, vertexConsumer, i, j, k);
        modelPart5.render(poseStack, vertexConsumer, i, j, k);
    }
}
