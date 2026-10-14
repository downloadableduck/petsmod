package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<ClientZombie> {

    public ClientZombieRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientZombie livingEntityRenderState = (ClientZombie) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientZombie livingEntityRenderState = (ClientZombie) entity;
        return ("textures/mob/zombie/zombie.png");
    }
}
