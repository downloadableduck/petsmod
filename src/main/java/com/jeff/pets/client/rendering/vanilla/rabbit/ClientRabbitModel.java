package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitModel extends EntityModel<ClientRabbit> {
    public final Cuboid head;
    private final Cuboid rearFootLeft = new Cuboid(this, 26, 24);
    private final Cuboid rearFootRight;
    private final Cuboid haunchLeft;
    private final Cuboid haunchRight;
    private final Cuboid body;
    private final Cuboid frontLegLeft;
    private final Cuboid frontLegRight;
    private final Cuboid earRight;
    private final Cuboid earLeft;
    private final Cuboid tail;
    private final Cuboid nose;
    private float jumpRotation;

    public ClientRabbitModel() {
        this.rearFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootLeft.setRotationPoint(3.0F, 17.5F, 3.7F);
        this.rearFootLeft.mirror = true;
        this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
        this.rearFootRight = new Cuboid(this, 8, 24);
        this.rearFootRight.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootRight.setRotationPoint(-3.0F, 17.5F, 3.7F);
        this.rearFootRight.mirror = true;
        this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
        this.haunchLeft = new Cuboid(this, 30, 15);
        this.haunchLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchLeft.setRotationPoint(3.0F, 17.5F, 3.7F);
        this.haunchLeft.mirror = true;
        this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
        this.haunchRight = new Cuboid(this, 16, 15);
        this.haunchRight.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchRight.setRotationPoint(-3.0F, 17.5F, 3.7F);
        this.haunchRight.mirror = true;
        this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
        this.body = new Cuboid(this, 0, 0);
        this.body.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
        this.body.setRotationPoint(0.0F, 19.0F, 8.0F);
        this.body.mirror = true;
        this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
        this.frontLegLeft = new Cuboid(this, 8, 15);
        this.frontLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegLeft.setRotationPoint(3.0F, 17.0F, -1.0F);
        this.frontLegLeft.mirror = true;
        this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
        this.frontLegRight = new Cuboid(this, 0, 15);
        this.frontLegRight.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegRight.setRotationPoint(-3.0F, 17.0F, -1.0F);
        this.frontLegRight.mirror = true;
        this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
        this.head = new Cuboid(this, 32, 0);
        this.head.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
        this.head.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.earRight = new Cuboid(this, 52, 0);
        this.earRight.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earRight.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.earRight.mirror = true;
        this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
        this.earLeft = new Cuboid(this, 58, 0);
        this.earLeft.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earLeft.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.earLeft.mirror = true;
        this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
        this.tail = new Cuboid(this, 52, 6);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        this.tail.setRotationPoint(0.0F, 20.0F, 7.0F);
        this.tail.mirror = true;
        this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
        this.nose = new Cuboid(this, 32, 9);
        this.nose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
        this.nose.setRotationPoint(0.0F, 16.0F, -1.0F);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(Cuboid Cuboid, float f, float g, float h) {
        Cuboid.pitch = f;
        Cuboid.yaw = g;
        Cuboid.roll = h;
    }

    @Override
    public void render(ClientRabbit rabbit, float i, float j, float f, float g, float h, float k) {
        if (CONFIG.isBaby) {
            float l = 1.5F;
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.56666666F, 0.56666666F, 0.56666666F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 1.375F, 0.125F);
            ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((Cuboid) -> Cuboid.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.4F, 0.4F, 0.4F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 2.25F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((Cuboid) -> Cuboid.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        } else {
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.6F, 0.6F, 0.6F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 1.0F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose, new Cuboid[0]).forEach((Cuboid) -> Cuboid.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        }

    }

    public void setAngles(ClientRabbit rabbit, float f, float g, float h, float i, float j, float s) {
        float k = h - (float) rabbit.age;
        this.nose.pitch = j * ((float) Math.PI / 180F);
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.earRight.pitch = j * ((float) Math.PI / 180F);
        this.earLeft.pitch = j * ((float) Math.PI / 180F);
        this.nose.yaw = i * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
        this.earRight.yaw = this.nose.yaw - 0.2617994F;
        this.earLeft.yaw = this.nose.yaw + 0.2617994F;
        this.jumpRotation = MathHelper.sin((float) Math.PI);
        this.haunchLeft.pitch = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.haunchRight.pitch = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rearFootLeft.pitch = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rearFootRight.pitch = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.frontLegLeft.pitch = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.frontLegRight.pitch = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }
}
