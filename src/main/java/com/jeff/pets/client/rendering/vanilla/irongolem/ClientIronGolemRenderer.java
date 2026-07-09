package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientIronGolemRenderer extends PetRenderer< ClientIronGolem,  ClientIronGolemModel<ClientIronGolem>> {

    public static final ModelLayerLocation IRON_GOLEM_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientirongolem"), "main");

    public ClientIronGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientIronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientIronGolem livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/iron_golem/iron_golem.png");
    }
}
