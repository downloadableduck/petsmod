package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer< ClientDonkey,  ClientHorseModel<ClientDonkey>> {
    public static ModelLayerLocation DONKEY_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientdonkey"), "main");

    public ClientDonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientHorseModel<>(context.bakeLayer(ModelLayers.DONKEY)), 0.5f);
    }

    public static LayerDefinition createBodyLayer() {
        return DonkeyModel.createBodyLayer();
    }

    public  ResourceLocation getTextureLocation(ClientDonkey donkeyRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/horse/donkey.png");
    }

    @Override
    protected void scale(ClientDonkey state,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
