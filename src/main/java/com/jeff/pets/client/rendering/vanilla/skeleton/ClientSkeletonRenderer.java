//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.skeleton;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton> {
    public ClientSkeletonRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientSkeleton livingEntityRenderState = (ClientSkeleton)entity;
        return "/mob/skeleton/skeleton.png";
    }

    public void applyHandSwingRotation(Mob entity, float f, float g, float h) {
        ClientSkeleton state = (ClientSkeleton)entity;
        super.applyHandSwingRotation(state, f, g, h);
        if ((state.vehicle != null)) {
            GlStateManager.translatef(0.0F, -0.5F, 0.0F);
        }

    }
}
