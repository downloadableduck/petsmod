package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class ClientSpiderRenderer extends PetRenderer<ClientSpider, SpiderModel<ClientSpider>> {

    public ClientSpiderRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}
