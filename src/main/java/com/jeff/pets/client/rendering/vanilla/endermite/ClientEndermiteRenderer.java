package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientEndermiteRenderer extends PetRenderer< ClientEndermite,  EndermiteModel<ClientEndermite>> {

    public static final ModelLayerLocation ENDERMITE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientendermite"), "main");

    public ClientEndermiteRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermiteModel<>(context.bakeLayer(ModelLayers.ENDERMITE)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientEndermite livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/endermite.png");
    }
}
