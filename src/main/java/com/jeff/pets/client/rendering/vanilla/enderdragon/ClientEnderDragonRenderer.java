package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon> {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) entity;
        super.applyScale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) entity;
        return ("textures/mob/enderdragon/dragon.png");
    }
}
