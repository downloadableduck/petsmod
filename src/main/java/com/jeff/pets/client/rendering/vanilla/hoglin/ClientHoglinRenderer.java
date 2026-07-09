package com.jeff.pets.client.rendering.vanilla.hoglin;

import com.jeff.pets.mob.vanilla.hostile.ClientHoglin;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHoglinRenderer extends PetRenderer< ClientHoglin,  ClientHoglinModel> {

    public static final ModelLayerLocation HOGLIN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clienthoglin"), "main");

    public ClientHoglinRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientHoglinModel(context.bakeLayer(ModelLayers.HOGLIN)), 0.75f);
    }

    @Override
    protected void scale(ClientHoglin state,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientHoglin livingEntityRenderState) {
        String hoglinTexturePath;
        if (Objects.equals(CONFIG.hoglinSkin, "hoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        } else if (Objects.equals(CONFIG.hoglinSkin, "zoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/zoglin.png";
        } else {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        }
        return new ResourceLocation("minecraft", hoglinTexturePath);
    }
}
