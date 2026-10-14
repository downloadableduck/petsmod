package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer {

    public ClientCatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCatModel(), 0.7F);
    }

    @Override
    protected void applyScale(MobEntity state, float f) {
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull String getTexture(Entity livingEntityRenderState) {
        switch (CONFIG.catSkin) {
            case "black":
                return ("textures/mob/cat/all_black.png");
            case "tuxedo":
                return ("textures/mob/cat/black.png");
            case "british_shorthair":
                return ("textures/mob/cat/british_shorthair.png");
            case "calico":
                return ("textures/mob/cat/calico.png");
            case "jellie":
                return ("textures/mob/cat/jellie.png");
            case "ocelot":
                return ("textures/mob/cat/ocelot.png");
            case "persian":
                return ("textures/mob/cat/persian.png");
            case "ragdoll":
                return ("textures/mob/cat/ragdoll.png");
            case "red":
                return ("textures/mob/cat/red.png");
            case "siamese":
                return ("textures/mob/cat/siamese.png");
            case "tabby":
                return ("textures/mob/cat/tabby.png");
            case "white":
                return ("textures/mob/cat/white.png");
            default:
                return ("textures/mob/cat/black.png");
        }
    }

    @Override
    public void renderModel(MobEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCat cat = (ClientCat) entity;
        super.renderModel(entity, f, g, h, i, j, k);
        cat.setSitting(cat.isRiding());
    }
}
