package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ClientRabbitModel extends ModelBase {
    public final ModelRenderer head;
    private final ModelRenderer rearFootLeft;
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
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rearFootLeft = new ModelRenderer(this, 26, 24);
        this.rearFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootLeft.setRotationPoint(3.0F, 17.5F, 3.7F);
        this.rearFootLeft.mirror = true;
        this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
        this.rearFootRight = new ModelRenderer(this, 8, 24);
        this.rearFootRight.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootRight.setRotationPoint(-3.0F, 17.5F, 3.7F);
        this.rearFootRight.mirror = true;
        this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
        this.haunchLeft = new ModelRenderer(this, 30, 15);
        this.haunchLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchLeft.setRotationPoint(3.0F, 17.5F, 3.7F);
        this.haunchLeft.mirror = true;
        this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
        this.haunchRight = new ModelRenderer(this, 16, 15);
        this.haunchRight.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchRight.setRotationPoint(-3.0F, 17.5F, 3.7F);
        this.haunchRight.mirror = true;
        this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
        this.body.setRotationPoint(0.0F, 19.0F, 8.0F);
        this.body.mirror = true;
        this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
        this.frontLegLeft = new ModelRenderer(this, 8, 15);
        this.frontLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegLeft.setRotationPoint(3.0F, 17.0F, -1.0F);
        this.frontLegLeft.mirror = true;
        this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
        this.frontLegRight = new ModelRenderer(this, 0, 15);
        this.frontLegRight.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegRight.setRotationPoint(-3.0F, 17.0F, -1.0F);
        this.frontLegRight.mirror = true;
        this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 32, 0);
        this.head.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
        this.head.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelRenderer(this, 52, 0);
        this.earRight.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earRight.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.earRight.mirror = true;
        this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
        this.earLeft = new ModelRenderer(this, 58, 0);
        this.earLeft.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earLeft.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.earLeft.mirror = true;
        this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
        this.tail = new ModelRenderer(this, 52, 6);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        this.tail.setRotationPoint(0.0F, 20.0F, 7.0F);
        this.tail.mirror = true;
        this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 32, 9);
        this.nose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
        this.nose.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelRenderer modelPart, float f, float g, float h) {
        modelPart.rotateAngleX = f;
        modelPart.rotateAngleY = g;
        modelPart.rotateAngleZ = h;
    }

    @Override
    public void render(Entity entity, float i, float j, float f, float g, float h, float k) {
        ClientRabbit rabbit = (ClientRabbit) entity;
        if (this.isChild) {
            float l = 1.5F;
            net.minecraft.client.renderer.GlStateManager.pushMatrix();
            net.minecraft.client.renderer.GlStateManager.scale(0.56666666F, 0.56666666F, 0.56666666F);
            net.minecraft.client.renderer.GlStateManager.translate(0.0F, 1.375F, 0.125F);
            ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((modelPart) -> modelPart.render(k));
            net.minecraft.client.renderer.GlStateManager.popMatrix();
            net.minecraft.client.renderer.GlStateManager.pushMatrix();
            net.minecraft.client.renderer.GlStateManager.scale(0.4F, 0.4F, 0.4F);
            net.minecraft.client.renderer.GlStateManager.translate(0.0F, 2.25F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((modelPart) -> modelPart.render(k));
            net.minecraft.client.renderer.GlStateManager.popMatrix();
        } else {
            net.minecraft.client.renderer.GlStateManager.pushMatrix();
            net.minecraft.client.renderer.GlStateManager.scale(0.6F, 0.6F, 0.6F);
            net.minecraft.client.renderer.GlStateManager.translate(0.0F, 1.0F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose).forEach((modelPart) -> modelPart.render(k));
            net.minecraft.client.renderer.GlStateManager.popMatrix();
        }

    }

    public void setRotationAngles(ClientRabbit rabbit, float f, float g, float h, float i, float j, float u) {
        float k = h - (float) rabbit.ticksExisted;
        this.nose.rotateAngleX = j * ((float) Math.PI / 180F);
        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.earRight.rotateAngleX = j * ((float) Math.PI / 180F);
        this.earLeft.rotateAngleX = j * ((float) Math.PI / 180F);
        this.nose.rotateAngleY = i * ((float) Math.PI / 180F);
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
        this.earRight.rotateAngleY = this.nose.rotateAngleY - 0.2617994F;
        this.earLeft.rotateAngleY = this.nose.rotateAngleY + 0.2617994F;
        this.jumpRotation = net.minecraft.util.math.MathHelper.sin((float) Math.PI);
        this.haunchLeft.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.haunchRight.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rearFootLeft.rotateAngleX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rearFootRight.rotateAngleX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.frontLegLeft.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.frontLegRight.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }
}
