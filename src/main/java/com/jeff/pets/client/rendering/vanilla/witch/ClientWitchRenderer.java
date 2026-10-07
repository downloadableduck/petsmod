package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientWitchRenderer extends PetRenderer {

    public ClientWitchRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelWitch(0), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}

