package com.jeff.pets.client.rendering.vanilla.spider;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.util.ResourceLocation;

public class ClientSpiderRenderer extends PetRenderer {

    public ClientSpiderRenderer() {
        super(new ModelSpider(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}

