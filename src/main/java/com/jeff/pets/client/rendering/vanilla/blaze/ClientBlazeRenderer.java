package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientBlazeRenderer extends PetRenderer< ClientBlaze,  BlazeModel<ClientBlaze>> {
    public static final ModelLayerLocation BLAZE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientblaze"), "main");

    public ClientBlazeRenderer(EntityRendererProvider.Context context) {
        super(context, new BlazeModel<>(context.bakeLayer(ModelLayers.BLAZE)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientBlaze livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/blaze.png");
    }
}
