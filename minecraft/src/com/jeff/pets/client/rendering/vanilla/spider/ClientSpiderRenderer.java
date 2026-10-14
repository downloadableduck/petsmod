package com.jeff.pets.client.rendering.vanilla.spider;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelSpider;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientSpiderRenderer extends PetRenderer {

    public ClientSpiderRenderer() {
        super(new ModelSpider(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/spider/spider.png";
    }
}

