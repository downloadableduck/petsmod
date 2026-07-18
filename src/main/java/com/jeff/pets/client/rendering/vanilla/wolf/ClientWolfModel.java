package com.jeff.pets.client.rendering.vanilla.wolf;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientWolfModel extends EntityModel<ClientWolf> {
    private final Cuboid head;
    private final Cuboid realHead;
    private final Cuboid body;
    private final Cuboid leg0;
    private final Cuboid leg1;
    private final Cuboid leg2;
    private final Cuboid leg3;
    private final Cuboid tail;
    private final Cuboid realTail;
    private final Cuboid upperBody;

    public ClientWolfModel() {
        float f = 0.0F;
        float g = 13.5F;
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.realHead = new Cuboid(this, 0, 0);
        this.realHead.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.head.addChild(this.realHead);
        this.body = new Cuboid(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F);
        this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.upperBody = new Cuboid(this, 21, 0);
        this.upperBody.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
        this.upperBody.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.leg0 = new Cuboid(this, 0, 18);
        this.leg0.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg0.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.leg1 = new Cuboid(this, 0, 18);
        this.leg1.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg1.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.leg2 = new Cuboid(this, 0, 18);
        this.leg2.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg2.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.leg3 = new Cuboid(this, 0, 18);
        this.leg3.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg3.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.tail = new Cuboid(this, 9, 18);
        this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.realTail = new Cuboid(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.tail.addChild(this.realTail);
        this.realHead.setTextureOffset(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.realHead.setTextureOffset(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.realHead.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
    }

    private ImmutableList<Cuboid> getParts() {
        return ImmutableList.of(head, body, leg0, leg1, leg2, leg3, tail, upperBody);
    }

    public void animateModel(ClientWolf wolf, float f, float g, float h) {
        //this.tail.yaw = MathHelper.cos(f * 0.6662F) * 1.4F * g;

        if (wolf.hasVehicle()) {
            this.upperBody.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.upperBody.pitch = 1.2566371F;
            this.upperBody.yaw = 0.0F;
            this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.body.pitch = ((float) Math.PI / 4F);
            this.tail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.leg0.setRotationPoint(-2.5F, 22.7F, 2.0F);
            this.leg0.pitch = ((float) Math.PI * 1.5F);
            this.leg1.setRotationPoint(0.5F, 22.7F, 2.0F);
            this.leg1.pitch = ((float) Math.PI * 1.5F);
            this.leg2.pitch = 5.811947F;
            this.leg2.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.leg3.pitch = 5.811947F;
            this.leg3.setRotationPoint(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.body.pitch = ((float) Math.PI / 2F);
            this.upperBody.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.upperBody.pitch = this.body.pitch;
            this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.leg0.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.leg1.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.leg2.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.leg3.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.leg0.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.leg1.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg2.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg3.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }

        this.realHead.roll = 0;
        this.upperBody.roll = 0;
        this.body.roll = 0;
        //this.realTail.zRot = wolf.getBodyRollAngle(h, -0.2F);
    }

    public void setAngles(ClientWolf wolf, float f, float g, float h, float i, float j, float s) {
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
        this.tail.pitch = 45;
    }

    @Override
    public void render(ClientWolf wolf, float f,  float g, float h, float i, float j, float k) {
        super.render(wolf, f, g, h, i, j, k);
        for (Cuboid cube : this.getParts()) {
            cube.render(k);
        }
    }
}
