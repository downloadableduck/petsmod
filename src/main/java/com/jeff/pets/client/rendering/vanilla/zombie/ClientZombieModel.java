package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.rendering.AnimationUtils.animateZombieArms;

public class ClientZombieModel extends BiPedModel {

    public ClientZombieModel() {
        super(0.0F, 0.0F, 64, 64);
    }

    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, Entity entityIn) {
        super.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, entityIn);
        if (entityIn instanceof MobEntity) {
            MobEntity living = (MobEntity) entityIn;
            animateZombieArms(this.field_1477, this.field_1476, true, living.getHandSwingProgress(partialTicks), partialTicks);
        }
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}
