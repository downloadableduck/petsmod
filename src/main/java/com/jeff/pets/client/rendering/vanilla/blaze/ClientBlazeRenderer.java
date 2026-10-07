package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientBlazeRenderer extends PetRenderer {

    public ClientBlazeRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelBlaze(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/blaze.png");
    }
}

