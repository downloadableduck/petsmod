package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class ClientRabbitModel extends EntityModel {
    public final ModelPart head;
    private final ModelPart rearFootLeft;
    private final ModelPart rearFootRight;
    private final ModelPart haunchLeft;
    private final ModelPart haunchRight;
    private final ModelPart body;
    private final ModelPart frontLegLeft;
    private final ModelPart frontLegRight;
    private final ModelPart earRight;
    private final ModelPart earLeft;
    private final ModelPart tail;
    private final ModelPart nose;
    private float jumpRotation;

    public ClientRabbitModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rearFootLeft = new ModelPart(this, 26, 24);
        this.rearFootLeft.addCuboid(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootLeft.setPivot(3.0F, 17.5F, 3.7F);
        this.rearFootLeft.mirror = true;
        this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
        this.rearFootRight = new ModelPart(this, 8, 24);
        this.rearFootRight.addCuboid(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootRight.setPivot(-3.0F, 17.5F, 3.7F);
        this.rearFootRight.mirror = true;
        this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
        this.haunchLeft = new ModelPart(this, 30, 15);
        this.haunchLeft.addCuboid(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchLeft.setPivot(3.0F, 17.5F, 3.7F);
        this.haunchLeft.mirror = true;
        this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
        this.haunchRight = new ModelPart(this, 16, 15);
        this.haunchRight.addCuboid(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchRight.setPivot(-3.0F, 17.5F, 3.7F);
        this.haunchRight.mirror = true;
        this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 0, 0);
        this.body.addCuboid(-3.0F, -2.0F, -10.0F, 6, 5, 10);
        this.body.setPivot(0.0F, 19.0F, 8.0F);
        this.body.mirror = true;
        this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
        this.frontLegLeft = new ModelPart(this, 8, 15);
        this.frontLegLeft.addCuboid(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegLeft.setPivot(3.0F, 17.0F, -1.0F);
        this.frontLegLeft.mirror = true;
        this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
        this.frontLegRight = new ModelPart(this, 0, 15);
        this.frontLegRight.addCuboid(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegRight.setPivot(-3.0F, 17.0F, -1.0F);
        this.frontLegRight.mirror = true;
        this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
        this.head = new ModelPart(this, 32, 0);
        this.head.addCuboid(-2.5F, -4.0F, -5.0F, 5, 4, 5);
        this.head.setPivot(0.0F, 16.0F, -1.0F);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelPart(this, 52, 0);
        this.earRight.addCuboid(-2.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earRight.setPivot(0.0F, 16.0F, -1.0F);
        this.earRight.mirror = true;
        this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
        this.earLeft = new ModelPart(this, 58, 0);
        this.earLeft.addCuboid(0.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earLeft.setPivot(0.0F, 16.0F, -1.0F);
        this.earLeft.mirror = true;
        this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
        this.tail = new ModelPart(this, 52, 6);
        this.tail.addCuboid(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        this.tail.setPivot(0.0F, 20.0F, 7.0F);
        this.tail.mirror = true;
        this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
        this.nose = new ModelPart(this, 32, 9);
        this.nose.addCuboid(-0.5F, -2.5F, -5.5F, 1, 1, 1);
        this.nose.setPivot(0.0F, 16.0F, -1.0F);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelPart modelPart, float f, float g, float h) {
        modelPart.posX = f;
        modelPart.posY = g;
        modelPart.posZ = h;
    }

    @Override
    public void render(Entity entity, float i, float j, float f, float g, float h, float k) {
        ClientRabbit rabbit = (ClientRabbit) entity;
        if (this.child) {
            float l = 1.5F;
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scale(0.56666666F, 0.56666666F, 0.56666666F);
            com.mojang.blaze3d.platform.GlStateManager.translate(0.0F, 1.375F, 0.125F);
            ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((modelPart) -> modelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scale(0.4F, 0.4F, 0.4F);
            com.mojang.blaze3d.platform.GlStateManager.translate(0.0F, 2.25F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((modelPart) -> modelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        } else {
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scale(0.6F, 0.6F, 0.6F);
            com.mojang.blaze3d.platform.GlStateManager.translate(0.0F, 1.0F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose).forEach((modelPart) -> modelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        }

    }

    public void setAngles(ClientRabbit rabbit, float f, float g, float h, float i, float j, float u) {
        float k = h - (float) rabbit.ticksAlive;
        this.nose.posX = j * ((float) Math.PI / 180F);
        this.head.posX = j * ((float) Math.PI / 180F);
        this.earRight.posX = j * ((float) Math.PI / 180F);
        this.earLeft.posX = j * ((float) Math.PI / 180F);
        this.nose.posY = i * ((float) Math.PI / 180F);
        this.head.posY = i * ((float) Math.PI / 180F);
        this.earRight.posY = this.nose.posY - 0.2617994F;
        this.earLeft.posY = this.nose.posY + 0.2617994F;
        this.jumpRotation = net.minecraft.util.math.MathHelper.sin((float) Math.PI);
        this.haunchLeft.posX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.haunchRight.posX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rearFootLeft.posX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rearFootRight.posX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.frontLegLeft.posX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.frontLegRight.posX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }
}
