package com.jeff.pets.compat.render.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * b1.8-compatible Model base extending MC's Model and adding newer API surface used by this mod.
 */
public abstract class Model extends net.minecraft.client.render.model.Model {
    public boolean isBaby;
    public int textureWidth = 64;
    public int textureHeight = 32;
    private final Map<String, int[]> texturePositions = new HashMap<>();

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    @Override
    public void prepare(MobEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
    }

    public void setTexturePos(String key, int u, int v) {
        texturePositions.put(key, new int[]{u, v});
    }

    public int[] getTexturePos(String key) {
        return texturePositions.get(key);
    }
}
