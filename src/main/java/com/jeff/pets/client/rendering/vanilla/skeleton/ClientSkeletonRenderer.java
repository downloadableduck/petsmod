package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSkeletonRenderer extends PetRenderer<@NotNull ClientSkeleton> {

    public ClientSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonEntityModel(), 0.75f);
    }

    @Override
    public void method_5777(ClientSkeleton state, float f, float g, float h) {
        super.method_5777(state, f, g, h);
        if (state.getVehicle() != null) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.5f, 0);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientSkeleton livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/skeleton.png");
    }
}