package com.jeff.pets.client.rendering.vanilla.irongolem;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientIronGolemRenderer extends PetRenderer {

    public ClientIronGolemRenderer() {
        super(new ClientIronGolemModel(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/iron_golem.png";
    }
}

