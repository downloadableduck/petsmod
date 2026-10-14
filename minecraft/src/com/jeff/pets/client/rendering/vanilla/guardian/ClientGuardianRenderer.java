package com.jeff.pets.client.rendering.vanilla.guardian;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;


public class ClientGuardianRenderer extends PetRenderer {

    public ClientGuardianRenderer() {
        super(new ClientGuardianModel(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/guardian.png";
    }
}

