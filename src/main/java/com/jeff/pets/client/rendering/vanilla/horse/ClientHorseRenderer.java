//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.horse;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;

public class ClientHorseRenderer extends PetRenderer<ClientHorse> {
    public String horseTextureLocation;

    public ClientHorseRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0.0F), 0.5F);
    }

    protected void applyScale(LivingEntity entity, float f) {
        ClientHorse state = (ClientHorse)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientHorse horseRenderState = (ClientHorse)entity;
        if (Central.CONFIG.horseSkin.equals("black")) {
            this.horseTextureLocation = "textures/mob/horse/horse_black.png";
        } else if (Central.CONFIG.horseSkin.equals("brown")) {
            this.horseTextureLocation = "textures/mob/horse/horse_brown.png";
        } else if (Central.CONFIG.horseSkin.equals("chestnut")) {
            this.horseTextureLocation = "textures/mob/horse/horse_chestnut.png";
        } else if (Central.CONFIG.horseSkin.equals("creamy")) {
            this.horseTextureLocation = "textures/mob/horse/horse_creamy.png";
        } else if (Central.CONFIG.horseSkin.equals("dark_brown")) {
            this.horseTextureLocation = "textures/mob/horse/horse_brown.png";
        } else if (Central.CONFIG.horseSkin.equals("gray")) {
            this.horseTextureLocation = "textures/mob/horse/horse_gray.png";
        } else if (Central.CONFIG.horseSkin.equals("white")) {
            this.horseTextureLocation = "textures/mob/horse/horse_white.png";
        } else if (Central.CONFIG.horseSkin.equals("skeleton")) {
            this.horseTextureLocation = "textures/mob/horse/horse_skeleton.png";
        } else if (Central.CONFIG.horseSkin.equals("zombie")) {
            this.horseTextureLocation = "textures/mob/horse/horse_zombie.png";
        } else {
            this.horseTextureLocation = "textures/mob/horse/horse_black.png";
        }

        return this.horseTextureLocation;
    }
}
