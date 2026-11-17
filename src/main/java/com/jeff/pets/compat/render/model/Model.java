//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat.render.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class Model extends EntityModel {
    public boolean isBaby;
    public int textureWidth = 64;
    public int textureHeight = 32;
    protected LivingEntity currentEntity;
    private final Map<String, int[]> texturePositions = new HashMap();

    public Model() {
    }

    public void render(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void animateModel(LivingEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
        this.currentEntity = entity;
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.currentEntity = entity instanceof LivingEntity ? (LivingEntity)entity : this.currentEntity;
        this.render(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.currentEntity = entity instanceof LivingEntity ? (LivingEntity)entity : this.currentEntity;
        this.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setTexturePos(String key, int u, int v) {
        this.texturePositions.put(key, new int[]{u, v});
    }

    public int[] getTexturePos(String key) {
        return (int[])this.texturePositions.get(key);
    }
}
