package com.jeff.pets.client.rendering.vanilla.skeleton;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.util.ResourceLocation;

public class ClientSkeletonRenderer extends PetRenderer<ClientSkeleton, ModelSkeleton> {

    public ClientSkeletonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientSkeleton livingEntityRenderState = (ClientSkeleton) __e;
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    protected void applyRotations(EntityLivingBase __e, float f, float g, float h) {
        ClientSkeleton state = (ClientSkeleton) __e;
        super.applyRotations(__e, f, g, h);
        if (__e.field_70153_n != null) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
