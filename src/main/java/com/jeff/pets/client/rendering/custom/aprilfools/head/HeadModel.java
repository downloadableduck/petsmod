package com.jeff.pets.client.rendering.custom.aprilfools.head;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.render.model.ModelPart;

public class HeadModel extends PetModel {
    private final ModelPart Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelPart(this);
        Head.setPivot(0.0F, 0.0F, 0.0F);
        setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
        Head.setTextureOffset(0, 0).addCuboid(-8.0F, 16.0F, 0.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.0F);
        Head.setTextureOffset(32, 0).addCuboid(-4.0F, -8.0F, -4.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.5F);
    }

    public void setAngles(net.minecraft.entity.Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.posX = x;
        ModelPart.posY = y;
        ModelPart.posZ = z;
    }
}