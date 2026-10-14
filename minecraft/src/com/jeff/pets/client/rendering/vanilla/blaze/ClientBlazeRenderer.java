package com.jeff.pets.client.rendering.vanilla.blaze;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBlaze;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientBlazeRenderer extends PetRenderer {

    public ClientBlazeRenderer() {
        super(new ModelBlaze(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/mob/fire.png";
    }
}

