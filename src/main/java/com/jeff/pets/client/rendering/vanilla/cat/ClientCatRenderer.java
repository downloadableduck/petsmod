//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientCat;

public class ClientCatRenderer extends PetRenderer {
    public ClientCatRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientCatModel(), 0.7F);
    }

    protected void applyScale(LivingEntity state, float f) {
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity livingEntityRenderState) {
        switch (Central.CONFIG.catSkin) {
            case "black":
                return "textures/mob/cat/all_black.png";
            case "tuxedo":
                return "textures/mob/cat/black.png";
            case "british_shorthair":
                return "textures/mob/cat/british_shorthair.png";
            case "calico":
                return "textures/mob/cat/calico.png";
            case "jellie":
                return "textures/mob/cat/jellie.png";
            case "ocelot":
                return "textures/mob/cat/ocelot.png";
            case "persian":
                return "textures/mob/cat/persian.png";
            case "ragdoll":
                return "textures/mob/cat/ragdoll.png";
            case "red":
                return "textures/mob/cat/red.png";
            case "siamese":
                return "textures/mob/cat/siamese.png";
            case "tabby":
                return "textures/mob/cat/tabby.png";
            case "white":
                return "textures/mob/cat/white.png";
            default:
                return "textures/mob/cat/black.png";
        }
    }

    public void renderModel(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCat cat = (ClientCat)entity;
        cat.setSitting(cat.hasVehicle());
    }
}
