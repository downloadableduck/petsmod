package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.render.entity.model.StrayEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSkeletonRenderer extends PetRenderer<@NotNull ClientSkeleton, @NotNull StrayEntityModel<@NotNull ClientSkeleton>> {

    public ClientSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new StrayEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void setupTransforms(ClientSkeleton state, float f, float g, float h) {
        super.setupTransforms(state, f, g, h);
        if (state.hasVehicle()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
