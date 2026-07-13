package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientRabbitModel extends EntityModel<ClientRabbit> {
    public final ModelRenderer head;
    private final ModelRenderer rearFootLeft = new ModelRenderer(this, 26, 24);
    private final ModelRenderer rearFootRight;
    private final ModelRenderer haunchLeft;
    private final ModelRenderer haunchRight;
    private final ModelRenderer body;
    private final ModelRenderer frontLegLeft;
    private final ModelRenderer frontLegRight;
    private final ModelRenderer earRight;
    private final ModelRenderer earLeft;
    private final ModelRenderer tail;
    private final ModelRenderer nose;
    private float jumpRotation;

    public ClientRabbitModel() {
        this.rearFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
        this.rearFootLeft.setPos(3.0F, 17.5F, 3.7F);
        this.rearFootLeft.mirror = true;
        this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
        this.rearFootRight = new ModelRenderer(this, 8, 24);
        this.rearFootRight.addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
        this.rearFootRight.setPos(-3.0F, 17.5F, 3.7F);
        this.rearFootRight.mirror = true;
        this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
        this.haunchLeft = new ModelRenderer(this, 30, 15);
        this.haunchLeft.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
        this.haunchLeft.setPos(3.0F, 17.5F, 3.7F);
        this.haunchLeft.mirror = true;
        this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
        this.haunchRight = new ModelRenderer(this, 16, 15);
        this.haunchRight.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
        this.haunchRight.setPos(-3.0F, 17.5F, 3.7F);
        this.haunchRight.mirror = true;
        this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F);
        this.body.setPos(0.0F, 19.0F, 8.0F);
        this.body.mirror = true;
        this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
        this.frontLegLeft = new ModelRenderer(this, 8, 15);
        this.frontLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
        this.frontLegLeft.setPos(3.0F, 17.0F, -1.0F);
        this.frontLegLeft.mirror = true;
        this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
        this.frontLegRight = new ModelRenderer(this, 0, 15);
        this.frontLegRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
        this.frontLegRight.setPos(-3.0F, 17.0F, -1.0F);
        this.frontLegRight.mirror = true;
        this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 32, 0);
        this.head.addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F);
        this.head.setPos(0.0F, 16.0F, -1.0F);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelRenderer(this, 52, 0);
        this.earRight.addBox(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
        this.earRight.setPos(0.0F, 16.0F, -1.0F);
        this.earRight.mirror = true;
        this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
        this.earLeft = new ModelRenderer(this, 58, 0);
        this.earLeft.addBox(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
        this.earLeft.setPos(0.0F, 16.0F, -1.0F);
        this.earLeft.mirror = true;
        this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
        this.tail = new ModelRenderer(this, 52, 6);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F);
        this.tail.setPos(0.0F, 20.0F, 7.0F);
        this.tail.mirror = true;
        this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 32, 9);
        this.nose.addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F);
        this.nose.setPos(0.0F, 16.0F, -1.0F);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelRenderer modelPart, float f, float g, float h) {
        modelPart.xRot = f;
        modelPart.yRot = g;
        modelPart.zRot = h;
    }

    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack poseStack, com.mojang.blaze3d.vertex.IVertexBuilder vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (this.young) {
            float l = 1.5F;
            poseStack.pushPose();
            poseStack.scale(0.56666666F, 0.56666666F, 0.56666666F);
            poseStack.translate(0.0F, 1.375F, 0.125F);
            ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((modelPart) -> modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k));
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.scale(0.4F, 0.4F, 0.4F);
            poseStack.translate(0.0F, 2.25F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((modelPart) -> modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k));
            poseStack.popPose();
        } else {
            poseStack.pushPose();
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.translate(0.0F, 1.0F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose, new ModelRenderer[0]).forEach((modelPart) -> modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k));
            poseStack.popPose();
        }

    }

    public void setupAnim(ClientRabbit rabbit, float f, float g, float h, float i, float j) {
        float k = h - (float) rabbit.tickCount;
        this.nose.xRot = j * ((float) Math.PI / 180F);
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.earRight.xRot = j * ((float) Math.PI / 180F);
        this.earLeft.xRot = j * ((float) Math.PI / 180F);
        this.nose.yRot = i * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.earRight.yRot = this.nose.yRot - 0.2617994F;
        this.earLeft.yRot = this.nose.yRot + 0.2617994F;
        this.jumpRotation = net.minecraft.util.math.MathHelper.sin((float) Math.PI);
        this.haunchLeft.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.haunchRight.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.frontLegLeft.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.frontLegRight.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }
}
