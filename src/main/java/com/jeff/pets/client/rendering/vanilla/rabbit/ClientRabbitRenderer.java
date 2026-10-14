package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<ClientRabbit> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientRabbit livingEntityRenderState = (ClientRabbit) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientRabbit rabbitRenderState = (ClientRabbit) entity;
        if (CONFIG.activePet.equals("brown")) {
            rabbitTextureLocation = "textures/mob/rabbit/brown.png";
        } else if (CONFIG.activePet.equals("white")) {
            rabbitTextureLocation = "textures/mob/rabbit/white.png";
        } else if (CONFIG.activePet.equals("black")) {
            rabbitTextureLocation = "textures/mob/rabbit/black.png";
        } else if (CONFIG.activePet.equals("gold")) {
            rabbitTextureLocation = "textures/mob/rabbit/gold.png";
        } else if (CONFIG.activePet.equals("salt")) {
            rabbitTextureLocation = "textures/mob/rabbit/salt.png";
        } else if (CONFIG.activePet.equals("splotched")) {
            rabbitTextureLocation = "textures/mob/rabbit/white_splotched.png";
        } else if (CONFIG.activePet.equals("killer")) {
            rabbitTextureLocation = "textures/mob/rabbit/caerbannog.png";
        } else if (CONFIG.activePet.equals("toast")) {
            rabbitTextureLocation = "textures/mob/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/mob/rabbit/brown.png";
        }

        return (rabbitTextureLocation);
    }
}
