package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.minecraft.client.renderer.entity.layers.LayerStrayClothing;
import net.minecraft.util.ResourceLocation;

public class ClientStrayRenderer extends PetRenderer<ClientStray, ModelSkeleton> {

    public ClientStrayRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSkeleton(), 0.75f);
        this.addLayer(new LayerStrayClothing(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ClientStray livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void applyRotations(ClientStray state, float f, float g, float h) {
        super.applyRotations(state, f, g, h);
        if (state.isRiding()) {
            net.minecraft.client.renderer.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}
