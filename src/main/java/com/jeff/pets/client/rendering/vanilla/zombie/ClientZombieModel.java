package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.rendering.AnimationUtils.animateZombieArms;

public class ClientZombieModel extends BiPedModel {

    public ClientZombieModel() {
        super(0.0F, 0.0F, 64, 64);
    }

    @Override
    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, Entity entityIn) {
        super.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, entityIn);
        if (entityIn instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entityIn;
            animateZombieArms(this.leftArm, this.rightArm, true, living.getHandSwingProgress(partialTicks), partialTicks);
        }
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
