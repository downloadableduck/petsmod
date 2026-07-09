package com.jeff.pets.client.rendering.vanilla.goat;

import com.jeff.pets.mob.vanilla.neutral.ClientGoat;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientGoatRenderer extends PetRenderer< ClientGoat,  ClientGoatModel> {

    public static final ModelLayerLocation GOAT_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientgoat"), "main");

    public ClientGoatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientGoatModel(context.bakeLayer(ModelLayers.GOAT)), 0.75f);
    }

    @Override
    protected void scale( ClientGoat livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientGoat livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/goat/goat.png");
    }
}
