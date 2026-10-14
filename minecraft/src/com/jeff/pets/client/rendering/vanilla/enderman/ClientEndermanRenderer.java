package com.jeff.pets.client.rendering.vanilla.enderman;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelEnderman;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientEndermanRenderer extends PetRenderer {

    public ClientEndermanRenderer() {
        super(new ModelEnderman(), 0.5f);
        this.setPetLayer(new LayerEndermanEyes(this));
    }

    @Override
    public String getEntityTexture( final Entity enderman) {
        return "textures/entity/enderman/enderman.png";
    }
}

