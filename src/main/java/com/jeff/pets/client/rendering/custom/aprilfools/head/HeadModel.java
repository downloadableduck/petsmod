package com.jeff.pets.client.rendering.custom.aprilfools.head;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.jeff.pets.mob.custom.aprilfools.Head;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class HeadModel extends EntityModel<Head> {
    private final RendererModel Head;

    public HeadModel() {
        texWidth = 64;
        texHeight = 64;

        Head = new RendererModel(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
        Head.texOffs(0, 0).addBox(-8.0F, 16.0F, 0.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.0F, false);
        Head.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, (int) 8.0F, (int) 8.0F, (int) 8.0F, 0.5F, false);
    }

    @Override
    public void setupAnim(Head entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(Head head, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }
}