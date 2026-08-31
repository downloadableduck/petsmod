package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer<ClientCat, ClientCatModel> {

    public ClientCatRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCatModel(), 0.7F);
    }

    @Override
    public void preRenderCallback(ClientCat state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(ClientCat livingEntityRenderState) {
        switch (CONFIG.catSkin) {
            case "black":
                return new ResourceLocation("minecraft", "textures/entity/cat/all_black.png");
            case "tuxedo":
                return new ResourceLocation("minecraft", "textures/entity/cat/black.png");
            case "british_shorthair":
                return new ResourceLocation("minecraft", "textures/entity/cat/british_shorthair.png");
            case "calico":
                return new ResourceLocation("minecraft", "textures/entity/cat/calico.png");
            case "jellie":
                return new ResourceLocation("minecraft", "textures/entity/cat/jellie.png");
            case "ocelot":
                return new ResourceLocation("minecraft", "textures/entity/cat/ocelot.png");
            case "persian":
                return new ResourceLocation("minecraft", "textures/entity/cat/persian.png");
            case "ragdoll":
                return new ResourceLocation("minecraft", "textures/entity/cat/ragdoll.png");
            case "red":
                return new ResourceLocation("minecraft", "textures/entity/cat/red.png");
            case "siamese":
                return new ResourceLocation("minecraft", "textures/entity/cat/siamese.png");
            case "tabby":
                return new ResourceLocation("minecraft", "textures/entity/cat/tabby.png");
            case "white":
                return new ResourceLocation("minecraft", "textures/entity/cat/white.png");
            default:
                return new ResourceLocation("minecraft", "textures/entity/cat/black.png");
        }
    }

    @Override
    public void renderModel(ClientCat cat, float f, float g, float h, float i, float j, float k) {
        super.renderModel(cat, f, g, h, i, j, k);
        cat.setSitting(cat.isRiding());
    }
}
