package com.jeff.pets.client.rendering.vanilla.llama;

import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.QuadruPedEntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class ClientLlamaModel extends QuadruPedEntityModel {
    private final ModelPart chest1;
    private final ModelPart chest2;

    public ClientLlamaModel(float p_i47226_1_) {
        super(15, p_i47226_1_);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-2.0F, -14.0F, -10.0F, 4, 4, 9, p_i47226_1_);
        this.head.setPivot(0.0F, 7.0F, -6.0F);
        this.head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8, 18, 6, p_i47226_1_);
        this.head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3, 3, 2, p_i47226_1_);
        this.head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3, 3, 2, p_i47226_1_);
        this.torso = new ModelPart(this, 29, 0);
        this.torso.addCuboid(-6.0F, -10.0F, -7.0F, 12, 18, 10, p_i47226_1_);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        this.chest1 = new ModelPart(this, 45, 28);
        this.chest1.addCuboid(-3.0F, 0.0F, 0.0F, 8, 8, 3, p_i47226_1_);
        this.chest1.setPivot(-8.5F, 3.0F, 3.0F);
        this.chest1.posY = ((float) Math.PI / 2F);
        this.chest2 = new ModelPart(this, 45, 41);
        this.chest2.addCuboid(-3.0F, 0.0F, 0.0F, 8, 8, 3, p_i47226_1_);
        this.chest2.setPivot(5.5F, 3.0F, 3.0F);
        this.chest2.posY = ((float) Math.PI / 2F);
        int i = 4;
        int j = 14;
        this.backRightLeg = new ModelPart(this, 29, 29);
        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.backRightLeg.setPivot(-2.5F, 10.0F, 6.0F);
        this.backLeftLeg = new ModelPart(this, 29, 29);
        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.backLeftLeg.setPivot(2.5F, 10.0F, 6.0F);
        this.frontRightLeg = new ModelPart(this, 29, 29);
        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.frontRightLeg.setPivot(-2.5F, 10.0F, -4.0F);
        this.frontLeftLeg = new ModelPart(this, 29, 29);
        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.frontLeftLeg.setPivot(2.5F, 10.0F, -4.0F);
        --this.backRightLeg.pivotX;
        ++this.backLeftLeg.pivotX;
        this.backRightLeg.pivotZ += 0.0F;
        this.backLeftLeg.pivotZ += 0.0F;
        --this.frontRightLeg.pivotX;
        ++this.frontLeftLeg.pivotX;
        --this.frontRightLeg.pivotZ;
        --this.frontLeftLeg.pivotZ;
        this.field_1515 += 2.0F;
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        boolean flag = false;
        ClientLlama p_78088_1_ = (ClientLlama) entity;
        this.setAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        if (this.child) {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, this.field_1514 * p_78088_7_, this.field_1515 * p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f1 = 0.7F;
            GlStateManager.scale(0.71428573F, 0.64935064F, 0.7936508F);
            GlStateManager.translate(0.0F, 21.0F * p_78088_7_, 0.22F);
            this.head.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f2 = 1.1F;
            GlStateManager.scale(0.625F, 0.45454544F, 0.45454544F);
            GlStateManager.translate(0.0F, 33.0F * p_78088_7_, 0.0F);
            this.torso.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.45454544F, 0.41322312F, 0.45454544F);
            GlStateManager.translate(0.0F, 33.0F * p_78088_7_, 0.0F);
            this.backRightLeg.render(p_78088_7_);
            this.backLeftLeg.render(p_78088_7_);
            this.frontRightLeg.render(p_78088_7_);
            this.frontLeftLeg.render(p_78088_7_);
            GlStateManager.popMatrix();
        } else {
            this.head.render(p_78088_7_);
            this.torso.render(p_78088_7_);
            this.backRightLeg.render(p_78088_7_);
            this.backLeftLeg.render(p_78088_7_);
            this.frontRightLeg.render(p_78088_7_);
            this.frontLeftLeg.render(p_78088_7_);
        }

        if (flag) {
            this.chest1.render(p_78088_7_);
            this.chest2.render(p_78088_7_);
        }

    }
}
