package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<ClientDonkey> {

    public ClientDonkeyRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.5f);
    }

    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientDonkey donkeyRenderState = (ClientDonkey) entity;
        return ("textures/mob/horse/donkey.png");
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientDonkey state = (ClientDonkey) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
