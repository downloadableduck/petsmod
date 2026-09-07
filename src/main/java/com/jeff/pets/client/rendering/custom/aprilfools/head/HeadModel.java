package com.jeff.pets.client.rendering.custom.aprilfools.head;

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class HeadModel extends ModelBase {
    private final ModelRenderer Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
        Head.setTextureOffset(0, 0).addBox(-8.0F, 16.0F, 0.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.0F);
        Head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.5F);
    }

    public void setRotationAngles(net.minecraft.entity.Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}