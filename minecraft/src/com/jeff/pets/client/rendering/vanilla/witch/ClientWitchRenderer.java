package com.jeff.pets.client.rendering.vanilla.witch;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelWitch;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientWitchRenderer extends PetRenderer {

    public ClientWitchRenderer() {
        super(new ModelWitch(0), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/witch.png";
    }
}

