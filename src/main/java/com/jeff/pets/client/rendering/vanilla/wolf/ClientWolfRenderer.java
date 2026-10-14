package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer {

    public ClientWolfRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    protected void applyScale(MobEntity livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull String getTexture(Entity livingEntityRenderState) {
        String wolfTexturePath = "textures/mob/wolf/wolf.png";

        return (wolfTexturePath);
    }

    @Override
    public void renderModel(MobEntity e, float f, float g, float h, float i, float j, float k) {
        ClientWolf wolf = (ClientWolf) e;
        super.renderModel(e, f, g, h, i, j, k);
        wolf.setSitting(wolf.isRiding());
    }
}
