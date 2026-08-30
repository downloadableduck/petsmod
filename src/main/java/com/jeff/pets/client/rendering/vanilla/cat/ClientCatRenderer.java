package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer {

    public ClientCatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCatModel(), 0.7F);
    }

    @Override
    protected void applyScale(LivingEntity state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(Entity livingEntityRenderState) {
        switch (CONFIG.catSkin) {
            case "black":
                return new Identifier("minecraft", "textures/entity/cat/all_black.png");
            case "tuxedo":
                return new Identifier("minecraft", "textures/entity/cat/black.png");
            case "british_shorthair":
                return new Identifier("minecraft", "textures/entity/cat/british_shorthair.png");
            case "calico":
                return new Identifier("minecraft", "textures/entity/cat/calico.png");
            case "jellie":
                return new Identifier("minecraft", "textures/entity/cat/jellie.png");
            case "ocelot":
                return new Identifier("minecraft", "textures/entity/cat/ocelot.png");
            case "persian":
                return new Identifier("minecraft", "textures/entity/cat/persian.png");
            case "ragdoll":
                return new Identifier("minecraft", "textures/entity/cat/ragdoll.png");
            case "red":
                return new Identifier("minecraft", "textures/entity/cat/red.png");
            case "siamese":
                return new Identifier("minecraft", "textures/entity/cat/siamese.png");
            case "tabby":
                return new Identifier("minecraft", "textures/entity/cat/tabby.png");
            case "white":
                return new Identifier("minecraft", "textures/entity/cat/white.png");
            default:
                return new Identifier("minecraft", "textures/entity/cat/black.png");
        }
    }

    @Override
    public void renderModel(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCat cat = (ClientCat) entity;
        super.renderModel(entity, f, g, h, i, j, k);
        cat.setSitting(cat.isRiding());
    }
}
