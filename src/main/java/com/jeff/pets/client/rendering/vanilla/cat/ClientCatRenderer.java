package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer<@NotNull ClientCat, @NotNull ClientCatModel> {

    public ClientCatRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientCatModel(0), 0.7F);
    }

    @Override
    protected void scale(ClientCat state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCat livingEntityRenderState) {
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
    public void render(ClientCat cat, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(cat, f, g, poseStack, source, i);
        cat.setSitting(cat.isPassenger());
    }
}
