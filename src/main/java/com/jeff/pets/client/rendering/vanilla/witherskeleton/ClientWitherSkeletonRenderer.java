//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.SkeletonModel;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;

public class ClientWitherSkeletonRenderer extends PetRenderer<ClientWitherSkeleton> {
    public ClientWitherSkeletonRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientWitherSkeleton livingEntityRenderState = (ClientWitherSkeleton)entity;
        return "textures/mob/skeleton/wither_skeleton.png";
    }
}
