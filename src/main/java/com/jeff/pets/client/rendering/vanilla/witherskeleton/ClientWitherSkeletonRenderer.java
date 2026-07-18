package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.render.entity.model.StrayEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends PetRenderer<@NotNull ClientWitherSkeleton, @NotNull StrayEntityModel<@NotNull ClientWitherSkeleton>> {

    public ClientWitherSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new StrayEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientWitherSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/wither_skeleton.png");
    }
}
