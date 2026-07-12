package com.jeff.pets.client.rendering.vanilla.fox;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxModel extends AgeableListModel<ClientFox> {
    public final ModelPart head;
    private final ModelPart earL;
    private final ModelPart earR;
    private final ModelPart nose;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart tail;
    private float legMotionPos;

    public ClientFoxModel() {
        super(true, 8.0F, 3.35F);
        this.texWidth = 48;
        this.texHeight = 32;
        this.head = new ModelPart(this, 1, 5);
        this.head.addBox(-3.0F, -2.0F, -5.0F, 8.0F, 6.0F, 6.0F);
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.earL = new ModelPart(this, 8, 1);
        this.earL.addBox(-3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F);
        this.earR = new ModelPart(this, 15, 1);
        this.earR.addBox(3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F);
        this.nose = new ModelPart(this, 6, 18);
        this.nose.addBox(-1.0F, 2.01F, -8.0F, 4.0F, 2.0F, 3.0F);
        this.head.addChild(this.earL);
        this.head.addChild(this.earR);
        this.head.addChild(this.nose);
        this.body = new ModelPart(this, 24, 15);
        this.body.addBox(-3.0F, 3.999F, -3.5F, 6.0F, 11.0F, 6.0F);
        this.body.setPos(0.0F, 16.0F, -6.0F);
        float f = 0.001F;
        this.leg0 = new ModelPart(this, 13, 24);
        this.leg0.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        this.leg0.setPos(-5.0F, 17.5F, 7.0F);
        this.leg1 = new ModelPart(this, 4, 24);
        this.leg1.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        this.leg1.setPos(-1.0F, 17.5F, 7.0F);
        this.leg2 = new ModelPart(this, 13, 24);
        this.leg2.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        this.leg2.setPos(-5.0F, 17.5F, 0.0F);
        this.leg3 = new ModelPart(this, 4, 24);
        this.leg3.addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.001F);
        this.leg3.setPos(-1.0F, 17.5F, 0.0F);
        this.tail = new ModelPart(this, 30, 0);
        this.tail.addBox(2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 5.0F);
        this.tail.setPos(-4.0F, 15.0F, -1.0F);
        this.body.addChild(this.tail);
    }

    public void prepareMobModel(ClientFox fox, float f, float g, float h) {
        this.body.xRot = ((float) Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.leg0.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leg1.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg2.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg3.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.head.yRot = 0.0F;
        this.head.zRot = 0;
        this.leg0.visible = true;
        this.leg1.visible = true;
        this.leg2.visible = true;
        this.leg3.visible = true;
        this.body.setPos(0.0F, 16.0F, -6.0F);
        this.body.zRot = 0.0F;
        this.leg0.setPos(-5.0F, 17.5F, 7.0F);
        this.leg1.setPos(-1.0F, 17.5F, 7.0F);
        if (fox.isPassenger()) {
            this.body.xRot = ((float) Math.PI / 6F);
            this.body.setPos(0.0F, 9.0F, -3.0F);
            this.tail.xRot = ((float) Math.PI / 4F);
            this.tail.setPos(-4.0F, 15.0F, -2.0F);
            this.head.setPos(-1.0F, 10.0F, -0.25F);
            this.head.xRot = 0.0F;
            this.head.yRot = 0.0F;
            if (this.young) {
                this.head.setPos(-1.0F, 13.0F, -3.75F);
            }

            this.leg0.xRot = -1.3089969F;
            this.leg0.setPos(-5.0F, 21.5F, 6.75F);
            this.leg1.xRot = -1.3089969F;
            this.leg1.setPos(-1.0F, 21.5F, 6.75F);
            this.leg2.xRot = -0.2617994F;
            this.leg3.xRot = -0.2617994F;
        }

    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3);
    }

    public void setupAnim(ClientFox fox, float f, float g, float h, float i, float j) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
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
