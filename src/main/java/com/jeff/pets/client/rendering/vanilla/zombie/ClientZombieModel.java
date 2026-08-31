package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.ModelUtils;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieModel extends ModelBiped {

    public ClientZombieModel() {
        super(0.0F, 0.0F, 64, 64);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, entityIn);
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) entityIn;
            ModelUtils.animateZombieArms(this.bipedLeftArm, this.bipedRightArm, true, living.getSwingProgress(partialTicks), partialTicks);
        }
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.renderer.GlStateManager.scale(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
