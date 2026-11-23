//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientCat;

public class ClientCatRenderer extends PetRenderer {
    public ClientCatRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientCatModel(), 0.7F);
    }

    protected void applyScale(Mob state, float f) {
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity livingEntityRenderState) {
        switch (Central.CONFIG.catSkin) {
            case "black":
                return "/mob/cat/all_black.png";
            case "tuxedo":
                return "/mob/cat/black.png";
            case "british_shorthair":
                return "/mob/cat/british_shorthair.png";
            case "calico":
                return "/mob/cat/calico.png";
            case "jellie":
                return "/mob/cat/jellie.png";
            case "ocelot":
                return "/mob/cat/ocelot.png";
            case "persian":
                return "/mob/cat/persian.png";
            case "ragdoll":
                return "/mob/cat/ragdoll.png";
            case "red":
                return "/mob/cat/red.png";
            case "siamese":
                return "/mob/cat/siamese.png";
            case "tabby":
                return "/mob/cat/tabby.png";
            case "white":
                return "/mob/cat/white.png";
            default:
                return "/mob/cat/black.png";
        }
    }

    public void renderModel(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientCat cat = (ClientCat)entity;
        cat.setSitting((cat.vehicle != null));
    }
}
