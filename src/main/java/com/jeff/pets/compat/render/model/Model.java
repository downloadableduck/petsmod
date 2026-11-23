package com.jeff.pets.compat.render.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * b1.7.3-compatible Model base.
 */
public abstract class Model extends net.minecraft.client.render.model.Model {
    public boolean isBaby;
    public int textureWidth = 64;
    public int textureHeight = 32;
    protected MobEntity currentEntity;
    private final Map<String, int[]> texturePositions = new HashMap<>();

    @Override
    public void render(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // subclasses (PetModel) can override render(entity,...) and we call via stored entity
    }

    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void prepare(MobEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
        this.currentEntity = entity;
    }

    // Newer-style hook used by some pet code; not part of b1.7.3 MC Model
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.currentEntity = entity instanceof MobEntity ? (MobEntity) entity : this.currentEntity;
        this.render(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.currentEntity = entity instanceof MobEntity ? (MobEntity) entity : this.currentEntity;
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setTexturePos(String key, int u, int v) {
        texturePositions.put(key, new int[]{u, v});
    }

    public int[] getTexturePos(String key) {
        return texturePositions.get(key);
    }
}
