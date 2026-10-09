package com.jeff.pets.client.rendering.custom.aprilfools.head;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.render.ModelBox;
import net.minecraft.client.render.model.ModelPart;

public class HeadModel extends PetModel {
    private final ModelPart Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelPart(this);
        Head.setPivot(5.0F, 0.0F, -5.0F);
        Head.cuboids.add(new ModelBox(Head, 0, 0, -8.0F, 16.0F, 0.0F, 8, 8, 8, 0.0F));
        Head.cuboids.add(new ModelBox(Head, 32, 0, -8.0F, 16.0F, 0.0F, 8, 8, 8, 0.5F));
    }

    public void setRotationAngles(net.minecraft.entity.Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f) {
        //previously the render function, render code was moved to a method below
    }

    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelRenderer, float x, float y, float z) {
        ModelRenderer.posX = x;
        ModelRenderer.posY = y;
        ModelRenderer.posZ = z;
    }
}