package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientSilverfishRenderer extends PetRenderer {

    public ClientSilverfishRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelSilverfish(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}

