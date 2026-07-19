package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.render.model.entity.SkeletonModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<@NotNull ClientWitherSkeleton, @NotNull SkeletonModel<@NotNull ClientWitherSkeleton>> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new SkeletonModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientWitherSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
