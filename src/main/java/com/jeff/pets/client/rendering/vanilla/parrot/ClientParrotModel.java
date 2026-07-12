package com.jeff.pets.client.rendering.vanilla.parrot;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ClientParrotModel extends ListModel<ClientParrot> {
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart wingLeft;
    private final ModelPart wingRight;
    private final ModelPart head;
    private final ModelPart head2;
    private final ModelPart beak1;
    private final ModelPart beak2;
    private final ModelPart feather;
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public ClientParrotModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new ModelPart(this, 2, 8);
        this.body.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F);
        this.body.setPos(0.0F, 16.5F, -3.0F);
        this.tail = new ModelPart(this, 22, 1);
        this.tail.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 1.0F);
        this.tail.setPos(0.0F, 21.07F, 1.16F);
        this.wingLeft = new ModelPart(this, 19, 8);
        this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
        this.wingLeft.setPos(1.5F, 16.94F, -2.76F);
        this.wingRight = new ModelPart(this, 19, 8);
        this.wingRight.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
        this.wingRight.setPos(-1.5F, 16.94F, -2.76F);
        this.head = new ModelPart(this, 2, 2);
        this.head.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F);
        this.head.setPos(0.0F, 15.69F, -2.76F);
        this.head2 = new ModelPart(this, 10, 0);
        this.head2.addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F);
        this.head2.setPos(0.0F, -2.0F, -1.0F);
        this.head.addChild(this.head2);
        this.beak1 = new ModelPart(this, 11, 7);
        this.beak1.addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.beak1.setPos(0.0F, -0.5F, -1.5F);
        this.head.addChild(this.beak1);
        this.beak2 = new ModelPart(this, 16, 7);
        this.beak2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.beak2.setPos(0.0F, -1.75F, -2.45F);
        this.head.addChild(this.beak2);
        this.feather = new ModelPart(this, 2, 18);
        this.feather.addBox(0.0F, -4.0F, -2.0F, 0.0F, 5.0F, 4.0F);
        this.feather.setPos(0.0F, -2.15F, 0.15F);
        this.head.addChild(this.feather);
        this.legLeft = new ModelPart(this, 14, 18);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.legLeft.setPos(1.0F, 22.0F, -1.05F);
        this.legRight = new ModelPart(this, 14, 18);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.legRight.setPos(-1.0F, 22.0F, -1.05F);
    }

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.body, this.wingLeft, this.wingRight, this.tail, this.head, this.legLeft, this.legRight);
    }

    public void setupAnim(ClientParrot parrot, float f, float g, float h, float i, float j) {
        this.setupAnim(ParrotModel.State.FLYING, parrot.tickCount, f, g, h, i, j);
    }

    public void prepareMobModel(ClientParrot parrot, float f, float g, float h) {
        this.prepare(ParrotModel.State.FLYING);
    }

    public void renderOnShoulder(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k, int l) {
        this.prepare(ParrotModel.State.ON_SHOULDER);
        this.setupAnim(ParrotModel.State.ON_SHOULDER, l, f, g, 0.0F, h, k);
        this.parts().forEach((modelPart) -> modelPart.render(poseStack, vertexConsumer, i, j));
    }

    private void setupAnim(ParrotModel.State state, int i, float f, float g, float h, float j, float k) {
        this.head.xRot = k * ((float) Math.PI / 180F);
        this.head.yRot = j * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.x = 0.0F;
        this.body.x = 0.0F;
        this.tail.x = 0.0F;
        this.wingRight.x = -1.5F;
        this.wingLeft.x = 1.5F;
        switch (state) {
            case SITTING:
                break;
            case PARTY:
                float l = Mth.cos((float) i);
                float m = Mth.sin((float) i);
                this.head.x = l;
                this.head.y = 15.69F + m;
                this.head.xRot = 0.0F;
                this.head.yRot = 0.0F;
                this.head.zRot = Mth.sin((float) i) * 0.4F;
                this.body.x = l;
                this.body.y = 16.5F + m;
                this.wingLeft.zRot = -0.0873F - h;
                this.wingLeft.x = 1.5F + l;
                this.wingLeft.y = 16.94F + m;
                this.wingRight.zRot = 0.0873F + h;
                this.wingRight.x = -1.5F + l;
                this.wingRight.y = 16.94F + m;
                this.tail.x = l;
                this.tail.y = 21.07F + m;
                break;
            case STANDING:
                ModelPart var10000 = this.legLeft;
                var10000.xRot += Mth.cos(f * 0.6662F) * 1.4F * g;
                var10000 = this.legRight;
                var10000.xRot += Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            case FLYING:
            case ON_SHOULDER:
            default:
                float n = h * 0.3F;
                this.head.y = 15.69F + n;
                this.tail.xRot = 1.015F + Mth.cos(f * 0.6662F) * 0.3F * g;
                this.tail.y = 21.07F + n;
                this.body.y = 16.5F + n;
                this.wingLeft.zRot = -0.0873F - h;
                this.wingLeft.y = 16.94F + n;
                this.wingRight.zRot = 0.0873F + h;
                this.wingRight.y = 16.94F + n;
                this.legLeft.y = 22.0F + n;
                this.legRight.y = 22.0F + n;
        }

    }

    private void prepare(ParrotModel.State state) {
        this.feather.xRot = -0.2214F;
        this.body.xRot = 0.4937F;
        this.wingLeft.xRot = -0.6981F;
        this.wingLeft.yRot = -(float) Math.PI;
        this.wingRight.xRot = -0.6981F;
        this.wingRight.yRot = -(float) Math.PI;
        this.legLeft.xRot = -0.0299F;
        this.legRight.xRot = -0.0299F;
        this.legLeft.y = 22.0F;
        this.legRight.y = 22.0F;
        this.legLeft.zRot = 0.0F;
        this.legRight.zRot = 0.0F;
        switch (state) {
            case SITTING:
                float f = 1.9F;
                this.head.y = 17.59F;
                this.tail.xRot = 1.5388988F;
                this.tail.y = 22.97F;
                this.body.y = 18.4F;
                this.wingLeft.zRot = -0.0873F;
                this.wingLeft.y = 18.84F;
                this.wingRight.zRot = 0.0873F;
                this.wingRight.y = 18.84F;
                ++this.legLeft.y;
                ++this.legRight.y;
                ++this.legLeft.xRot;
                ++this.legRight.xRot;
                break;
            case PARTY:
                this.legLeft.zRot = -0.34906584F;
                this.legRight.zRot = 0.34906584F;
            case STANDING:
            case ON_SHOULDER:
            default:
                break;
            case FLYING:
                ModelPart var10000 = this.legLeft;
                var10000.xRot += 0.6981317F;
                var10000 = this.legRight;
                var10000.xRot += 0.6981317F;
        }

    }
}
