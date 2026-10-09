package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientSkeletonRenderer extends PetRenderer {

    public ClientSkeletonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonEntityModel(), 0.75f);
    }

    public void setLivingAnimations(ClientSkeleton state, float f, float g, float h) {
        if (state.vehicle != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientSkeleton livingEntityRenderState = (ClientSkeleton) __e;
        return new Identifier("minecraft", "textures/entity/skeleton/skeleton.png");
    }
}