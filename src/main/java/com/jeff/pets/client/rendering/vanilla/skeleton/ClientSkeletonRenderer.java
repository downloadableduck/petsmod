package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;

import org.jetbrains.annotations.NotNull;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton> {

    public ClientSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientSkeleton livingEntityRenderState = (ClientSkeleton) entity;
        return ("textures/mob/skeleton/skeleton.png");
    }

    @Override
    public void applyRotation(net.minecraft.entity.mob.MobEntity entity, float f, float g, float h) {
        ClientSkeleton state = (ClientSkeleton) entity;
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            com.jeff.pets.compat.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
