package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientSkeletonRenderer extends PetRenderer {

    public ClientSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    public void setLivingAnimations(ClientSkeleton state, float f, float g, float h) {
        if (state.ridingEntity != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientSkeleton livingEntityRenderState = (ClientSkeleton) __e;
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }
}