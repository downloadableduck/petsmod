package com.jeff.pets.client.rendering.vanilla.turtle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientTurtleModel extends QuadrupedModel<ClientTurtle> {
    private final ModelRenderer eggBelly;

    public ClientTurtleModel(float f) {
        super(12, f, true, 120.0F, 0.0F, 9.0F, 6.0F, 120);
        this.texWidth = 128;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 3, 0);
        this.head.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F);
        this.head.setPos(0.0F, 19.0F, -10.0F);
        this.body = new ModelRenderer(this);
        this.body.texOffs(7, 37).addBox(-9.5F, 3.0F, -10.0F, 19.0F, 20.0F, 6.0F, 0.0F);
        this.body.texOffs(31, 1).addBox(-5.5F, 3.0F, -13.0F, 11.0F, 18.0F, 3.0F, 0.0F);
        this.body.setPos(0.0F, 11.0F, -10.0F);
        this.eggBelly = new ModelRenderer(this);
        this.eggBelly.texOffs(70, 33).addBox(-4.5F, 3.0F, -14.0F, 9.0F, 18.0F, 1.0F, 0.0F);
        this.eggBelly.setPos(0.0F, 11.0F, -10.0F);
        int i = 1;
        this.leg0 = new ModelRenderer(this, 1, 23);
        this.leg0.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
        this.leg0.setPos(-3.5F, 22.0F, 11.0F);
        this.leg1 = new ModelRenderer(this, 1, 12);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
        this.leg1.setPos(3.5F, 22.0F, 11.0F);
        this.leg2 = new ModelRenderer(this, 27, 30);
        this.leg2.addBox(-13.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
        this.leg2.setPos(-5.0F, 21.0F, -4.0F);
        this.leg3 = new ModelRenderer(this, 27, 24);
        this.leg3.addBox(0.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
        this.leg3.setPos(5.0F, 21.0F, -4.0F);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.eggBelly));
    }

    public void setupAnim(ClientTurtle turtle, float f, float g, float h, float i, float j) {
        super.setupAnim(turtle, f, g, h, i, j);
        this.leg0.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg2.zRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg3.zRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg2.xRot = 0.0F;
        this.leg3.xRot = 0.0F;
        this.leg2.yRot = 0.0F;
        this.leg3.yRot = 0.0F;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
        this.eggBelly.xRot = ((float) Math.PI / 2F);
        if (!turtle.isInWater() && turtle.isOnGround()) {
            float k = 1.0F;
            float l = 1.0F;
            float m = 5.0F;
            this.leg2.yRot = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F + (float) Math.PI) * 8.0F * g * l;
            this.leg2.zRot = 0.0F;
            this.leg3.yRot = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.leg3.zRot = 0.0F;
            this.leg0.yRot = net.minecraft.util.math.MathHelper.cos(f * 5.0F + (float) Math.PI) * 3.0F * g;
            this.leg0.xRot = 0.0F;
            this.leg1.yRot = net.minecraft.util.math.MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.leg1.xRot = 0.0F;
        }

        this.eggBelly.visible = false;
    }

    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack poseStack, com.mojang.blaze3d.vertex.IVertexBuilder vertexConsumer, int i, int j, float f, float g, float h, float k) {
        boolean bl = this.eggBelly.visible;
        if (bl) {
            poseStack.pushPose();
            poseStack.translate(0.0F, -0.08F, 0.0F);
        }

        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
        if (bl) {
            poseStack.popPose();
        }

    }
}
