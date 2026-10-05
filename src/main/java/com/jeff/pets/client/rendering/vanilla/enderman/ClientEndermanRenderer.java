package com.jeff.pets.client.rendering.vanilla.enderman;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.util.ResourceLocation;

public class ClientEndermanRenderer extends PetRenderer {

    public ClientEndermanRenderer() {
        super(new ModelEnderman(), 0.5f);
        this.setPetLayer(new LayerEndermanEyes(this));
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}

