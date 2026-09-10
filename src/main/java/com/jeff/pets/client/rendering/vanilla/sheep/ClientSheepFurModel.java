package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadruPedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

public class ClientSheepFurModel extends QuadruPedEntityModel {
    private float headXRot;

    public ClientSheepFurModel() {
        super(12, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setPivot(0.0F, 6.0F, -8.0F);
        this.torso = new ModelPart(this, 28, 8);
        this.torso.addCuboid(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        float f = 0.5F;
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backRightLeg.setPivot(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backLeftLeg.setPivot(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontRightLeg.setPivot(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontLeftLeg.setPivot(3.0F, 12.0F, -5.0F);
    }

    public void animateModel(LivingEntity sheepEntity, float f, float g, float h) {
        ClientSheep sheep = (ClientSheep) sheepEntity;
        super.animateModel(sheepEntity, f, g, h);
    }

    public void setAngles(float f, float g, float h, float i, float j, float s, Entity entity) {
        ClientSheep sheep = (ClientSheep) entity;
        super.setAngles(f, g, h, i, j, s, entity);
    }
}