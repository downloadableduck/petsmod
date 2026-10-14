package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.model.entity.GhastModel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer {

    public ClientGhastRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new GhastModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(Entity livingEntityRenderState) {
        return ("textures/mob/ghast/ghast.png");
    }

    @Override
    public void applyScale(MobEntity ghast, float f) {
        com.jeff.pets.compat.GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
