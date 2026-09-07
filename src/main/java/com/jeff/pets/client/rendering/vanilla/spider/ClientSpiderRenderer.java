package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.util.ResourceLocation;

public class ClientSpiderRenderer extends PetRenderer<ClientSpider, ModelSpider> {

    public ClientSpiderRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSpider(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}
