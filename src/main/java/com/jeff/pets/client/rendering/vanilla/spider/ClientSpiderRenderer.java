package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientSpiderRenderer extends PetRenderer {

    public ClientSpiderRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelSpider(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}

