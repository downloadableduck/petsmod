package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

public class StingrayRenderer extends PetRenderer {

    public StingrayRenderer() {
        super(new StingrayModel(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity state) {
        return "textures/entity/stingray/stingray.png";
    }
}

