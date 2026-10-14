package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.SkeletonModel;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;

import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<ClientWitherSkeleton> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientWitherSkeleton livingEntityRenderState = (ClientWitherSkeleton) entity;
        return ("textures/mob/skeleton/wither_skeleton.png");
    }
}
