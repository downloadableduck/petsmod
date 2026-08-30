package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.SkeletonModel;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<ClientWitherSkeleton> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
