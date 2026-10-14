package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer<ClientHorse> {
    public String horseTextureLocation;

    public ClientHorseRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.5f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientHorse state = (ClientHorse) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientHorse horseRenderState = (ClientHorse) entity;
        if (CONFIG.horseSkin.equals("black")) {
            horseTextureLocation = "textures/mob/horse/horse_black.png";
        } else if (CONFIG.horseSkin.equals("brown")) {
            horseTextureLocation = "textures/mob/horse/horse_brown.png";
        } else if (CONFIG.horseSkin.equals("chestnut")) {
            horseTextureLocation = "textures/mob/horse/horse_chestnut.png";
        } else if (CONFIG.horseSkin.equals("creamy")) {
            horseTextureLocation = "textures/mob/horse/horse_creamy.png";
        } else if (CONFIG.horseSkin.equals("dark_brown")) {
            horseTextureLocation = "textures/mob/horse/horse_brown.png";
        } else if (CONFIG.horseSkin.equals("gray")) {
            horseTextureLocation = "textures/mob/horse/horse_gray.png";
        } else if (CONFIG.horseSkin.equals("white")) {
            horseTextureLocation = "textures/mob/horse/horse_white.png";
        } else if (CONFIG.horseSkin.equals("skeleton")) {
            horseTextureLocation = "textures/mob/horse/horse_skeleton.png";
        } else if (CONFIG.horseSkin.equals("zombie")) {
            horseTextureLocation = "textures/mob/horse/horse_zombie.png";
        } else {
            horseTextureLocation = "textures/mob/horse/horse_black.png";
        }
        return (horseTextureLocation);
    }
}
