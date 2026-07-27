package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<ClientDonkey, ClientHorseModel<ClientDonkey>> {

    public ClientDonkeyRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel<>(0), 0.5f);
    }

    public ResourceLocation getTextureLocation(ClientDonkey donkeyRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/horse/donkey.png");
    }

    @Override
    protected void scale(ClientDonkey state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
