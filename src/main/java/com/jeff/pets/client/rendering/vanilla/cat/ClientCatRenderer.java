package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer<@NotNull ClientCat, @NotNull ClientCatModel> {

    public ClientCatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientCatModel(0), 0.7F);
    }

    @Override
    protected void scale(ClientCat state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientCat livingEntityRenderState) {
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
    public void render(ClientCat cat, float f, float g, float h, float i, float j, float k) {
        super.render(cat, f, g, h, i, j, k);
        cat.setSitting(cat.hasVehicle());
    }
}
