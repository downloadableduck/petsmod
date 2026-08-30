package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.resource.Identifier;

import org.jetbrains.annotations.NotNull;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton> {

    public ClientSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void applyRotation(ClientSkeleton state, float f, float g, float h) {
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            net.minecraft.client.render.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
