package com.jeff.pets.client.rendering.vanilla.wolf;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;

import java.util.List;

public class ClientWolfModel extends EntityModel {
    private final ModelPart head;
    private final ModelPart realHead;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart tail;
    private final ModelPart realTail;
    private final ModelPart upperBody;

    public ClientWolfModel() {
        float f = 0.0F;
        float g = 13.5F;
        this.head = new ModelPart(this, 0, 0);
        this.head.setPivot(-1.0F, 13.5F, -7.0F);
        this.realHead = new ModelPart(this, 0, 0);
        this.realHead.addCuboid(-2.0F, -3.0F, -2.0F, (int) 6.0, (int) 6.0, (int) 4.0, 0.0F);
        this.head.add(this.realHead);
        this.body = new ModelPart(this, 18, 14);
        this.body.addCuboid(-3.0F, -2.0F, -3.0F, (int) 6.0, (int) 9.0, (int) 6.0, 0.0F);
        this.body.setPivot(0.0F, 14.0F, 2.0F);
        this.upperBody = new ModelPart(this, 21, 0);
        this.upperBody.addCuboid(-3.0F, -3.0F, -3.0F, (int) 8.0, (int) 6.0, (int) 7.0, 0.0F);
        this.upperBody.setPivot(-1.0F, 14.0F, 2.0F);
        this.leg0 = new ModelPart(this, 0, 18);
        this.leg0.addCuboid(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg0.setPivot(-2.5F, 16.0F, 7.0F);
        this.leg1 = new ModelPart(this, 0, 18);
        this.leg1.addCuboid(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg1.setPivot(0.5F, 16.0F, 7.0F);
        this.leg2 = new ModelPart(this, 0, 18);
        this.leg2.addCuboid(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg2.setPivot(-2.5F, 16.0F, -4.0F);
        this.leg3 = new ModelPart(this, 0, 18);
        this.leg3.addCuboid(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg3.setPivot(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(this, 9, 18);
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        this.realTail = new ModelPart(this, 9, 18);
        this.realTail.addCuboid(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.tail.add(this.realTail);
        this.realHead.setTextureOffset(16, 14).addCuboid(-2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.setTextureOffset(16, 14).addCuboid(2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.setTextureOffset(0, 10).addCuboid(-0.5F, 0.0F, -5.0F, (int) 3.0, (int) 3.0, (int) 4.0, 0.0F);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.tail, this.upperBody);
    }

    public void animateModel(ClientWolf wolf, float f, float g, float h) {
        //this.tail.posY = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;

        if (wolf.isPassenger()) {
            this.upperBody.setPivot(-1.0F, 16.0F, -3.0F);
            this.upperBody.posX = 1.2566371F;
            this.upperBody.posY = 0.0F;
            this.body.setPivot(0.0F, 18.0F, 0.0F);
            this.body.posX = ((float) Math.PI / 4F);
            this.tail.setPivot(-1.0F, 21.0F, 6.0F);
            this.leg0.setPivot(-2.5F, 22.7F, 2.0F);
            this.leg0.posX = ((float) Math.PI * 1.5F);
            this.leg1.setPivot(0.5F, 22.7F, 2.0F);
            this.leg1.posX = ((float) Math.PI * 1.5F);
            this.leg2.posX = 5.811947F;
            this.leg2.setPivot(-2.49F, 17.0F, -4.0F);
            this.leg3.posX = 5.811947F;
            this.leg3.setPivot(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setPivot(0.0F, 14.0F, 2.0F);
            this.body.posX = ((float) Math.PI / 2F);
            this.upperBody.setPivot(-1.0F, 14.0F, -3.0F);
            this.upperBody.posX = this.body.posX;
            this.tail.setPivot(-1.0F, 12.0F, 8.0F);
            this.leg0.setPivot(-2.5F, 16.0F, 7.0F);
            this.leg1.setPivot(0.5F, 16.0F, 7.0F);
            this.leg2.setPivot(-2.5F, 16.0F, -4.0F);
            this.leg3.setPivot(0.5F, 16.0F, -4.0F);
            this.leg0.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.leg1.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg2.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg3.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }

        this.realHead.posZ = 0;
        this.upperBody.posZ = 0;
        this.body.posZ = 0;
        //this.realTail.posZ = wolf.getBodyRollAngle(h, -0.2F);
    }

    public void setAngles(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        this.head.posX = j * ((float) Math.PI / 180F);
        this.head.posY = i * ((float) Math.PI / 180F);
        this.tail.posX = 45;
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float k) {
        super.render(entity, f, g, h, i, j, k);
        ClientWolf wolf = (ClientWolf) entity;
        this.setAngles(wolf, f, g, h, i, j, k);
        this.animateModel(wolf, f, g, h);
        for (ModelPart cube : this.getParts()) {
            cube.render(k);
        }
    }

    private List<ModelPart> getParts() {
        return ImmutableList.of(head, body, leg0, leg1, leg2, leg3, tail, upperBody);
    }
}
