package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;

public class ClientEnderDragonModel extends EntityModel<ClientEnderDragon> {
    private final ModelRenderer head;
    private final ModelRenderer neck;
    private final ModelRenderer jaw;
    private final ModelRenderer body;
    private final ModelRenderer leftWing;
    private final ModelRenderer leftWingTip;
    private final ModelRenderer leftFrontLeg;
    private final ModelRenderer leftFrontLegTip;
    private final ModelRenderer leftFrontFoot;
    private final ModelRenderer leftRearLeg;
    private final ModelRenderer leftRearLegTip;
    private final ModelRenderer leftRearFoot;
    private final ModelRenderer rightWing;
    private final ModelRenderer rightWingTip;
    private final ModelRenderer rightFrontLeg;
    private final ModelRenderer rightFrontLegTip;
    private final ModelRenderer rightFrontFoot;
    private final ModelRenderer rightRearLeg;
    private final ModelRenderer rightRearLegTip;
    private final ModelRenderer rightRearFoot;
    private EnderDragonEntity entity;
    private float a;

    public ClientEnderDragonModel() {
        this.texWidth = 256;
        this.texHeight = 256;
        float f = -16.0F;
        this.head = new ModelRenderer(this);
        this.head.addBox("upperlip", -6.0F, -1.0F, -24.0F, 12, 5, 16, 0.0F, 176, 44);
        this.head.addBox("upperhead", -8.0F, -8.0F, -10.0F, 16, 16, 16, 0.0F, 112, 30);
        this.head.mirror = true;
        this.head.addBox("scale", -5.0F, -12.0F, -4.0F, 2, 4, 6, 0.0F, 0, 0);
        this.head.addBox("nostril", -5.0F, -3.0F, -22.0F, 2, 2, 4, 0.0F, 112, 0);
        this.head.mirror = false;
        this.head.addBox("scale", 3.0F, -12.0F, -4.0F, 2, 4, 6, 0.0F, 0, 0);
        this.head.addBox("nostril", 3.0F, -3.0F, -22.0F, 2, 2, 4, 0.0F, 112, 0);
        this.jaw = new ModelRenderer(this);
        this.jaw.setPos(0.0F, 4.0F, -8.0F);
        this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16, 0.0F, 176, 65);
        this.head.addChild(this.jaw);
        this.neck = new ModelRenderer(this);
        this.neck.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F, 192, 104);
        this.neck.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6, 0.0F, 48, 0);
        this.body = new ModelRenderer(this);
        this.body.setPos(0.0F, 4.0F, 8.0F);
        this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, 0, 0);
        this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12, 0.0F, 220, 53);
        this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12, 0.0F, 220, 53);
        this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12, 0.0F, 220, 53);
        this.leftWing = new ModelRenderer(this);
        this.leftWing.mirror = true;
        this.leftWing.setPos(12.0F, 5.0F, 2.0F);
        this.leftWing.addBox("bone", 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, 112, 88);
        this.leftWing.addBox("skin", 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 88);
        this.leftWingTip = new ModelRenderer(this);
        this.leftWingTip.mirror = true;
        this.leftWingTip.setPos(56.0F, 0.0F, 0.0F);
        this.leftWingTip.addBox("bone", 0.0F, -2.0F, -2.0F, 56, 4, 4, 0.0F, 112, 136);
        this.leftWingTip.addBox("skin", 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 144);
        this.leftWing.addChild(this.leftWingTip);
        this.leftFrontLeg = new ModelRenderer(this);
        this.leftFrontLeg.setPos(12.0F, 20.0F, 2.0F);
        this.leftFrontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, 112, 104);
        this.leftFrontLegTip = new ModelRenderer(this);
        this.leftFrontLegTip.setPos(0.0F, 20.0F, -1.0F);
        this.leftFrontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, 226, 138);
        this.leftFrontLeg.addChild(this.leftFrontLegTip);
        this.leftFrontFoot = new ModelRenderer(this);
        this.leftFrontFoot.setPos(0.0F, 23.0F, 0.0F);
        this.leftFrontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, 144, 104);
        this.leftFrontLegTip.addChild(this.leftFrontFoot);
        this.leftRearLeg = new ModelRenderer(this);
        this.leftRearLeg.setPos(16.0F, 16.0F, 42.0F);
        this.leftRearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, 0, 0);
        this.leftRearLegTip = new ModelRenderer(this);
        this.leftRearLegTip.setPos(0.0F, 32.0F, -4.0F);
        this.leftRearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12, 0.0F, 196, 0);
        this.leftRearLeg.addChild(this.leftRearLegTip);
        this.leftRearFoot = new ModelRenderer(this);
        this.leftRearFoot.setPos(0.0F, 31.0F, 4.0F);
        this.leftRearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, 112, 0);
        this.leftRearLegTip.addChild(this.leftRearFoot);
        this.rightWing = new ModelRenderer(this);
        this.rightWing.setPos(-12.0F, 5.0F, 2.0F);
        this.rightWing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, 112, 88);
        this.rightWing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 88);
        this.rightWingTip = new ModelRenderer(this);
        this.rightWingTip.setPos(-56.0F, 0.0F, 0.0F);
        this.rightWingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4, 0.0F, 112, 136);
        this.rightWingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.0F, -56, 144);
        this.rightWing.addChild(this.rightWingTip);
        this.rightFrontLeg = new ModelRenderer(this);
        this.rightFrontLeg.setPos(-12.0F, 20.0F, 2.0F);
        this.rightFrontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, 112, 104);
        this.rightFrontLegTip = new ModelRenderer(this);
        this.rightFrontLegTip.setPos(0.0F, 20.0F, -1.0F);
        this.rightFrontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, 226, 138);
        this.rightFrontLeg.addChild(this.rightFrontLegTip);
        this.rightFrontFoot = new ModelRenderer(this);
        this.rightFrontFoot.setPos(0.0F, 23.0F, 0.0F);
        this.rightFrontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, 144, 104);
        this.rightFrontLegTip.addChild(this.rightFrontFoot);
        this.rightRearLeg = new ModelRenderer(this);
        this.rightRearLeg.setPos(-16.0F, 16.0F, 42.0F);
        this.rightRearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, 0, 0);
        this.rightRearLegTip = new ModelRenderer(this);
        this.rightRearLegTip.setPos(0.0F, 32.0F, -4.0F);
        this.rightRearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12, 0.0F, 196, 0);
        this.rightRearLeg.addChild(this.rightRearLegTip);
        this.rightRearFoot = new ModelRenderer(this);
        this.rightRearFoot.setPos(0.0F, 31.0F, 4.0F);
        this.rightRearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, 112, 0);
        this.rightRearLegTip.addChild(this.rightRearFoot);
    }

    public void prepareMobModel(EnderDragonEntity enderDragon, float f, float g, float h) {
        this.entity = enderDragon;
        this.a = h;
    }

    public void setupAnim(ClientEnderDragon enderDragon, float f, float g, float h, float i, float j) {
    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder vertexConsumer, int i, int j, float f, float g, float h, float k) {
        poseStack.pushPose();
        float l = net.minecraft.util.math.MathHelper.lerp(this.a, this.entity.oFlapTime, this.entity.flapTime);
        this.jaw.xRot = (float) (Math.sin(l * ((float) Math.PI * 2F)) + (double) 1.0F) * 0.2F;
        float m = (float) (Math.sin(l * ((float) Math.PI * 2F) - 1.0F) + (double) 1.0F);
        m = (m * m + m * 2.0F) * 0.05F;
        poseStack.translate(0.0F, m - 2.0F, -3.0F);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(m * 2.0F));
        float n = 0.0F;
        float o = 20.0F;
        float p = -12.0F;
        float q = 1.5F;
        double[] ds = this.entity.getLatencyPos(6, this.a);
        float r = net.minecraft.util.math.MathHelper.wrapDegrees((float) (this.entity.getLatencyPos(5, this.a)[0] - this.entity.getLatencyPos(10, this.a)[0]));
        float s = net.minecraft.util.math.MathHelper.wrapDegrees((float) (this.entity.getLatencyPos(5, this.a)[0] + (double) (r / 2.0F)));
        float t = l * ((float) Math.PI * 2F);

        for (int u = 0; u < 5; ++u) {
            double[] es = this.entity.getLatencyPos(5 - u, this.a);
            float v = (float) Math.cos((float) u * 0.45F + t) * 0.15F;
            this.neck.yRot = net.minecraft.util.math.MathHelper.wrapDegrees((float) (es[0] - ds[0])) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.xRot = v + this.entity.getHeadPartYOffset(u, ds, es) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = -net.minecraft.util.math.MathHelper.wrapDegrees((float) (es[0] - (double) s)) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.y = o;
            this.neck.z = p;
            this.neck.x = n;
            o += net.minecraft.util.math.MathHelper.sin(this.neck.xRot) * 10.0F;
            p -= net.minecraft.util.math.MathHelper.cos(this.neck.yRot) * net.minecraft.util.math.MathHelper.cos(this.neck.xRot) * 10.0F;
            n -= net.minecraft.util.math.MathHelper.sin(this.neck.yRot) * net.minecraft.util.math.MathHelper.cos(this.neck.xRot) * 10.0F;
            this.neck.render(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, k);
        }

        this.head.y = o;
        this.head.z = p;
        this.head.x = n;
        double[] fs = this.entity.getLatencyPos(0, this.a);
        this.head.yRot = net.minecraft.util.math.MathHelper.wrapDegrees((float) (fs[0] - ds[0])) * ((float) Math.PI / 180F);
        this.head.xRot = net.minecraft.util.math.MathHelper.wrapDegrees(this.entity.getHeadPartYOffset(6, ds, fs)) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
        this.head.zRot = -net.minecraft.util.math.MathHelper.wrapDegrees((float) (fs[0] - (double) s)) * ((float) Math.PI / 180F);
        this.head.render(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, k);
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.0F, 0.0F);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(-r * 1.5F));
        poseStack.translate(0.0F, -1.0F, 0.0F);
        this.body.zRot = 0.0F;
        this.body.render(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, k);
        float w = l * ((float) Math.PI * 2F);
        this.leftWing.xRot = 0.125F - (float) Math.cos(w) * 0.2F;
        this.leftWing.yRot = -0.25F;
        this.leftWing.zRot = -((float) (Math.sin(w) + (double) 0.125F)) * 0.8F;
        this.leftWingTip.zRot = (float) (Math.sin(w + 2.0F) + (double) 0.5F) * 0.75F;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.zRot = -this.leftWing.zRot;
        this.rightWingTip.zRot = -this.leftWingTip.zRot;
        this.renderSide(poseStack, vertexConsumer, i, j, m, this.leftWing, this.leftFrontLeg, this.leftFrontLegTip, this.leftFrontFoot, this.leftRearLeg, this.leftRearLegTip, this.leftRearFoot, (int) k);
        this.renderSide(poseStack, vertexConsumer, i, j, m, this.rightWing, this.rightFrontLeg, this.rightFrontLegTip, this.rightFrontFoot, this.rightRearLeg, this.rightRearLegTip, this.rightRearFoot, (int) k);
        poseStack.popPose();
        float v = -net.minecraft.util.math.MathHelper.sin(l * ((float) Math.PI * 2F)) * 0.0F;
        t = l * ((float) Math.PI * 2F);
        o = 10.0F;
        p = 60.0F;
        n = 0.0F;
        ds = this.entity.getLatencyPos(11, this.a);

        for (int x = 0; x < 12; ++x) {
            fs = this.entity.getLatencyPos(12 + x, this.a);
            v += net.minecraft.util.math.MathHelper.sin((float) x * 0.45F + t) * 0.05F;
            this.neck.yRot = (net.minecraft.util.math.MathHelper.wrapDegrees((float) (fs[0] - ds[0])) * 1.5F + 180.0F) * ((float) Math.PI / 180F);
            this.neck.xRot = v + (float) (fs[1] - ds[1]) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = net.minecraft.util.math.MathHelper.wrapDegrees((float) (fs[0] - (double) s)) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.y = o;
            this.neck.z = p;
            this.neck.x = n;
            o += net.minecraft.util.math.MathHelper.sin(this.neck.xRot) * 10.0F;
            p -= net.minecraft.util.math.MathHelper.cos(this.neck.yRot) * net.minecraft.util.math.MathHelper.cos(this.neck.xRot) * 10.0F;
            n -= net.minecraft.util.math.MathHelper.sin(this.neck.yRot) * net.minecraft.util.math.MathHelper.cos(this.neck.xRot) * 10.0F;
            this.neck.render(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, k);
        }

        poseStack.popPose();
    }

    private void renderSide(MatrixStack poseStack, IVertexBuilder vertexConsumer, int i, int j, float f, ModelRenderer modelPart, ModelRenderer modelPart2, ModelRenderer modelPart3, ModelRenderer modelPart4, ModelRenderer modelPart5, ModelRenderer modelPart6, ModelRenderer modelPart7, int k) {
        modelPart5.xRot = 1.0F + f * 0.1F;
        modelPart6.xRot = 0.5F + f * 0.1F;
        modelPart7.xRot = 0.75F + f * 0.1F;
        modelPart2.xRot = 1.3F + f * 0.1F;
        modelPart3.xRot = -0.5F - f * 0.1F;
        modelPart4.xRot = 0.75F + f * 0.1F;
        modelPart.render(poseStack, vertexConsumer, i, j, k, 1, 1, 1);
        modelPart2.render(poseStack, vertexConsumer, i, j, k, 1, 1, 1);
        modelPart5.render(poseStack, vertexConsumer, i, j, k, 1, 1, 1);
    }
}
