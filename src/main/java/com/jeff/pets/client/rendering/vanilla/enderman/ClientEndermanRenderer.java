package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientEndermanRenderer extends PetRenderer {

    public ClientEndermanRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelEnderman(), 0.5f);
        this.setPetLayer(new LayerEndermanEyes(this));
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}

