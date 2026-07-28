package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
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
    protected void scale(ClientDonkey state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
