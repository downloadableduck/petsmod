package com.jeff.pets.client.rendering.vanilla.silverfish;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.util.ResourceLocation;

public class ClientSilverfishRenderer extends PetRenderer {

    public ClientSilverfishRenderer() {
        super(new ModelSilverfish(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}

