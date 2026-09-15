package com.jeff.pets.client.rendering.vanilla.witch;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.util.ResourceLocation;

public class ClientWitchRenderer extends PetRenderer {

    public ClientWitchRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelWitch(0), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}

