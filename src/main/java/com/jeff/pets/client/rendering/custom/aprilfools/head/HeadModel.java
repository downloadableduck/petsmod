package com.jeff.pets.client.rendering.custom.aprilfools.head;
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.jeff.pets.mob.custom.aprilfools.Head;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;

public class HeadModel extends EntityModel<Head> {
    private final Cuboid Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new Cuboid(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
        Head.setTextureOffset(0, 0).addBox(-8.0F, 16.0F, 0.0F, 8, 8, 8, 0.0F, false);
        Head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);
    }

    @Override
    public void setAngles(Head entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float s) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(Head head, float f, float g, float h, float i, float j, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.pitch = x;
        modelRenderer.yaw = y;
        modelRenderer.roll = z;
    }
}