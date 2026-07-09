package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.mob.vanilla.passive.ClientBat;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientBatRenderer extends PetRenderer< ClientBat,  ClientBatModel> {
    public static final ModelLayerLocation BAT_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/bat.png"), "main");

    public ClientBatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientBatModel(context.bakeLayer(BAT_LOCATION)), 0.25F);
    }

    @Override
    protected void scale(ClientBat bat, PoseStack poseStack, float f) {
        poseStack.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientBat batRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bat.png");
    }
}
