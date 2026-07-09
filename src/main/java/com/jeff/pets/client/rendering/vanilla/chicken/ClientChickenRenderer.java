package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer< ClientChicken,  ClientChickenModel<ClientChicken>> {
    public static final ModelLayerLocation CHICKEN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientchicken"), "main");

    public ClientChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientChicken livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/chicken.png");
    }

    @Override
    protected void scale(ClientChicken state,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
