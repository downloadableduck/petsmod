package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.PufferfishBigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientPufferFishRenderer extends PetRenderer< ClientPufferFish,  PufferfishBigModel<ClientPufferFish>> {

    public static final ModelLayerLocation PUFFERFISH_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientpufferfish"), "main");

    public ClientPufferFishRenderer(EntityRendererProvider.Context context) {
        super(context, new PufferfishBigModel<>(context.bakeLayer(ModelLayers.PUFFERFISH_BIG)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientPufferFish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/pufferfish.png");
    }
}