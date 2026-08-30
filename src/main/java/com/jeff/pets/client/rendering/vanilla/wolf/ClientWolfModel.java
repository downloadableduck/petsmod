package com.jeff.pets.client.rendering.vanilla.wolf;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientWolfModel extends Model {
    private final ModelPart head;
    private final ModelPart realHead;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart tail;
    private final ModelPart realTail;
    private final ModelPart upperBody;

    public ClientWolfModel() {
        float f = 0.0F;
        float g = 13.5F;
        this.head = new ModelPart(this, 0, 0);
        this.head.setPos(-1.0F, 13.5F, -7.0F);
        this.realHead = new ModelPart(this, 0, 0);
        this.realHead.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.head.addChild(this.realHead);
        this.body = new ModelPart(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F);
        this.body.setPos(0.0F, 14.0F, 2.0F);
        this.upperBody = new ModelPart(this, 21, 0);
        this.upperBody.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
        this.upperBody.setPos(-1.0F, 14.0F, 2.0F);
        this.leg0 = new ModelPart(this, 0, 18);
        this.leg0.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg0.setPos(-2.5F, 16.0F, 7.0F);
        this.backRightLeg = new ModelPart(this, 0, 18);
        this.backRightLeg.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.backRightLeg.setPos(0.5F, 16.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 18);
        this.backLeftLeg.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.backLeftLeg.setPos(-2.5F, 16.0F, -4.0F);
        this.frontRightLeg = new ModelPart(this, 0, 18);
        this.frontRightLeg.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.frontRightLeg.setPos(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(this, 9, 18);
        this.tail.setPos(-1.0F, 12.0F, 8.0F);
        this.realTail = new ModelPart(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.tail.addChild(this.realTail);
        this.realHead.setTextureCoords(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.realHead.setTextureCoords(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.realHead.setTextureCoords(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
    }

    private ImmutableList<ModelPart> getParts() {
        return ImmutableList.of(head, body, leg0, backRightLeg, backLeftLeg, frontRightLeg, tail, upperBody);
    }

    public void prepare(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h) {
        ClientWolf wolf = (ClientWolf) entity;
        //this.tail.rotationY = MathHelper.cos(f * 0.6662F) * 1.4F * g;

        if (wolf.isRiding()) {
            this.upperBody.setPos(-1.0F, 16.0F, -3.0F);
            this.upperBody.rotationX = 1.2566371F;
            this.upperBody.rotationY = 0.0F;
            this.body.setPos(0.0F, 18.0F, 0.0F);
            this.body.rotationX = ((float) Math.PI / 4F);
            this.tail.setPos(-1.0F, 21.0F, 6.0F);
            this.leg0.setPos(-2.5F, 22.7F, 2.0F);
            this.leg0.rotationX = ((float) Math.PI * 1.5F);
            this.backRightLeg.setPos(0.5F, 22.7F, 2.0F);
            this.backRightLeg.rotationX = ((float) Math.PI * 1.5F);
            this.backLeftLeg.rotationX = 5.811947F;
            this.backLeftLeg.setPos(-2.49F, 17.0F, -4.0F);
            this.frontRightLeg.rotationX = 5.811947F;
            this.frontRightLeg.setPos(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.rotationX = ((float) Math.PI / 2F);
            this.upperBody.setPos(-1.0F, 14.0F, -3.0F);
            this.upperBody.rotationX = this.body.rotationX;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.leg0.setPos(-2.5F, 16.0F, 7.0F);
            this.backRightLeg.setPos(0.5F, 16.0F, 7.0F);
            this.backLeftLeg.setPos(-2.5F, 16.0F, -4.0F);
            this.frontRightLeg.setPos(0.5F, 16.0F, -4.0F);
            this.leg0.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.backRightLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.backLeftLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.frontRightLeg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }

        this.realHead.rotationZ = 0;
        this.upperBody.rotationZ = 0;
        this.body.rotationZ = 0;
        //this.realTail.zRot = wolf.getBodyrotationZAngle(h, -0.2F);
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        this.head.rotationX = j * ((float) Math.PI / 180F);
        this.head.rotationY = i * ((float) Math.PI / 180F);
        this.tail.rotationX = 45;
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f,  float g, float h, float i, float j, float k) {
        super.render(entity, f, g, h, i, j, k);
        for (ModelPart cube : this.getParts()) {
            cube.render(k);
        }
    }
}
