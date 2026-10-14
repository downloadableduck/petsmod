package com.jeff.pets.client.rendering.custom.aquatic.koi;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

public class KoiRenderer extends PetRenderer {

    public KoiRenderer() {
        super(new KoiModel(), 0.5f);
    }

    @Override
    public String getEntityTexture( final Entity state) {
        return "textures/entity/koi/koi.png";
    }
}

