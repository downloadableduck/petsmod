package com.jeff.pets.client.rendering.vanilla.silverfish;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelSilverfish;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientSilverfishRenderer extends PetRenderer {

    public ClientSilverfishRenderer() {
        super(new ModelSilverfish(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/silverfish.png";
    }
}

