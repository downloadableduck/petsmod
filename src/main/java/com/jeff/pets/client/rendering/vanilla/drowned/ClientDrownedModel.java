package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.AnimationUtils;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends BiPedModel {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.rightArm = new ModelPart(this, 32, 48);
        this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.rightArm.setPivot(-5.0F, 2.0F + g, 0.0F);
        this.rightLeg = new ModelPart(this, 16, 48);
        this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.rightLeg.setPivot(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float k, float j, Entity state) {
        super.setAngles(f, g, h, i, k, j, state);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.field_20533, h);
    }

    @Override
    public void animateModel(LivingEntity zombie, float f, float g, float h) {
        this.field_13385 = class_2850.EMPTY;
        this.field_13384 = class_2850.EMPTY;

        super.animateModel(zombie, f, g, h);

        if (this.field_13385 == class_2850.THROW_SPEAR) {
            this.rightArm.posX = this.rightArm.posX * 0.5F - (float) Math.PI;
            this.rightArm.posY = 0.0F;
        }

        if (this.field_20533 > 0.0F) {
            this.rightArm.posX = this.method_18915(this.field_20533, this.rightArm.posX, -2.5132742F) + this.field_20533 * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.leftArm.posX = this.method_18915(this.field_20533, this.leftArm.posX, -2.5132742F) - this.field_20533 * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.rightArm.posZ = this.method_18915(this.field_20533, this.rightArm.posZ, -0.15F);
            this.leftArm.posZ = this.method_18915(this.field_20533, this.leftArm.posZ, 0.15F);
            ModelPart var10000 = this.leftLeg;
            var10000.posX -= this.field_20533 * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.posX += this.field_20533 * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.head.posX = 0.0F;
        }
    }

    @Override
    public void render(Entity drowned, float i, float j, float f, float g, float h, float k) {
        super.render(drowned, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
