package com.jeff.pets.client.rendering.vanilla.polarbear;

import net.minecraft.client.render.entity.model.QuadruPedEntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class ClientPolarBearModel extends QuadruPedEntityModel {
    public ClientPolarBearModel() {
        super(12, 0.0F);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-3.5F, -3.0F, -3.0F, (int) 7.0, (int) 7.0, (int) 7.0, 0.0F);
        this.head.setPivot(0.0F, 10.0F, -16.0F);
        this.head.setTextureOffset(0, 44).addCuboid(-2.5F, 1.0F, -6.0F, (int) 5.0, (int) 3.0, (int) 3.0, 0.0F);
        this.head.setTextureOffset(26, 0).addCuboid(-4.5F, -4.0F, -1.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        ModelPart modelPart = this.head.setTextureOffset(26, 0);
        modelPart.mirror = true;
        modelPart.addCuboid(2.5F, -4.0F, -1.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.torso = new ModelPart(this);
        this.torso.setTextureOffset(0, 19).addCuboid(-5.0F, -13.0F, -7.0F, (int) 14.0, (int) 14.0, (int) 11.0, 0.0F);
        this.torso.setTextureOffset(39, 0).addCuboid(-4.0F, -25.0F, -7.0F, (int) 12.0, (int) 12.0, (int) 10.0, 0.0F);
        this.torso.setPivot(-2.0F, 9.0F, 12.0F);
        int i = 10;
        this.backRightLeg = new ModelPart(this, 50, 22);
        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 10.0, (int) 8.0, 0.0F);
        this.backRightLeg.setPivot(-3.5F, 14.0F, 6.0F);
        this.backLeftLeg = new ModelPart(this, 50, 22);
        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 10.0, (int) 8.0, 0.0F);
        this.backLeftLeg.setPivot(3.5F, 14.0F, 6.0F);
        this.frontRightLeg = new ModelPart(this, 50, 40);
        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 10.0, (int) 6.0, 0.0F);
        this.frontRightLeg.setPivot(-2.5F, 14.0F, -7.0F);
        this.frontLeftLeg = new ModelPart(this, 50, 40);
        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 10.0, (int) 6.0, 0.0F);
        this.frontLeftLeg.setPivot(2.5F, 14.0F, -7.0F);
        --this.backRightLeg.pivotX;
        ++this.backLeftLeg.pivotX;
        ModelPart var10000 = this.backRightLeg;
        var10000.pivotZ += 0.0F;
        var10000 = this.backLeftLeg;
        var10000.pivotZ += 0.0F;
        --this.frontRightLeg.pivotX;
        ++this.frontLeftLeg.pivotX;
        --this.frontRightLeg.pivotZ;
        --this.frontLeftLeg.pivotZ;
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float u, Entity polarBear) {
        super.setAngles(f, g, h, i, j, u, polarBear);
        float k = h - (float) polarBear.ticksAlive;
        float l = 0;
        l *= l;
        float m = 1.0F - l;
        this.torso.posX = ((float) Math.PI / 2F) - l * (float) Math.PI * 0.35F;
        this.torso.pivotY = 9.0F * m + 11.0F * l;
        this.frontRightLeg.pivotY = 14.0F * m - 6.0F * l;
        this.frontRightLeg.pivotZ = -8.0F * m - 4.0F * l;
        ModelPart var10000 = this.frontRightLeg;
        var10000.posX -= l * (float) Math.PI * 0.45F;
        this.frontLeftLeg.pivotY = this.frontRightLeg.pivotY;
        this.frontLeftLeg.pivotZ = this.frontRightLeg.pivotZ;
        var10000 = this.frontLeftLeg;
        var10000.posX -= l * (float) Math.PI * 0.45F;
        if (this.child) {
            this.head.pivotY = 10.0F * m - 9.0F * l;
            this.head.pivotZ = -16.0F * m - 7.0F * l;
        } else {
            this.head.pivotY = 10.0F * m - 14.0F * l;
            this.head.pivotZ = -16.0F * m - 3.0F * l;
        }

        var10000 = this.head;
        var10000.posX += l * (float) Math.PI * 0.15F;
    }
}
