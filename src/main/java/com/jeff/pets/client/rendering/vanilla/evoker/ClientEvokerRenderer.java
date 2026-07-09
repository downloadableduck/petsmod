package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientEvokerRenderer extends PetRenderer< ClientEvoker,  ClientEvokerModel<ClientEvoker>> {
    public static final ModelLayerLocation EVOKER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientevoker"), "main");

    public ClientEvokerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel(context.bakeLayer(ModelLayers.EVOKER)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientEvoker livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/evoker.png");
    }
}
