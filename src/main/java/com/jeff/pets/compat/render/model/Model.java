//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat.render.model;

import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererCreeper;
import net.minecraft.client.render.renderer.DrawMode;
import net.minecraft.client.render.tessellator.RenderBuffer;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.joml.primitives.AABBdc;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;

import java.util.HashMap;
import java.util.Map;

public abstract class Model implements StaticEntityModel {
    public boolean isBaby;
    public int textureWidth = 64;
    public int textureHeight = 32;
    public float swingProgress;
    public boolean riding = false;
    protected Mob currentEntity;
    private final Map<String, int[]> texturePositions = new HashMap();
    public TessellatorGeneral tessellator;

    public TessellatorGeneral getTessellator() {
        if (tessellator == null) return new TessellatorGeneral() {
            @Override
            public void setShade1i(int i) {

            }

            @Override
            public void setColor1i(int i) {

            }

            @Override
            public void lockColor() {

            }

            @Override
            public void setLightmapCoord1i(int i) {

            }

            @Override
            public void setNormal(float v, float v1, float v2) {

            }

            @Override
            public void setTextureUV(double v, double v1) {

            }

            @Override
            public void startDrawing(@NotNull DrawMode drawMode) {

            }

            @Override
            public void addVertex(double v, double v1, double v2) {

            }

            @Override
            public void setTranslation(double v, double v1, double v2) {

            }

            @Override
            public void offsetTranslation(double v, double v1, double v2) {

            }

            @Override
            public void draw() {

            }

            @Override
            public @NotNull RenderBuffer record(int i, int i1) {
                return null;
            }
        };
        return tessellator;
    }

    public Model() {
    }

    public void render(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void animateModel(Mob entity, float limbSwing, float limbSwingAmount, float tickDelta) {
        this.currentEntity = entity;
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.currentEntity = entity instanceof Mob ? (Mob)entity : this.currentEntity;
        this.render(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.currentEntity = entity instanceof Mob ? (Mob)entity : this.currentEntity;
        this.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setTexturePos(String key, int u, int v) {
        this.texturePositions.put(key, new int[]{u, v});
    }

    public int[] getTexturePos(String key) {
        return (int[])this.texturePositions.get(key);
    }

    @Override
    public void resetBones() {

    }

    @Override
    public @NotNull BoneTransform getTransform(@NotNull String s) {
        return new BoneTransform();
    }

    @Override
    public @NotNull AABBdc visibleBounds() {
        return this.currentEntity.bb;
    }

    @Override
    public void translateToBone(@NotNull String s) {

    }

    @Override
    public void render() {

    }

    @Override
    public void delete() {

    }
}
