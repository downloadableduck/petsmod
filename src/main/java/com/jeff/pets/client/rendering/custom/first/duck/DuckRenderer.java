package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck> {
    public String duckTexturePath;

    public DuckRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        Duck livingEntityRenderState = (Duck) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public void renderModel(net.minecraft.entity.mob.MobEntity entity, float f, final float k, float u, float g, float h, float i) {
        Duck duck = (Duck) entity;
        float partialTick = 1.0F;
        duck.flap = (float) Math2.lerp(partialTick, duck.oFlap, duck.flap);
        duck.flapSpeed = (float) Math2.lerp(partialTick, duck.oFlapSpeed, duck.flapSpeed);
        super.renderModel(duck, f, k, u, g, h, i);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        Duck state = (Duck) entity;
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "textures/mob/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "textures/mob/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "textures/mob/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "textures/mob/duck/bronze.png";
        }
        return (duckTexturePath);
    }
}