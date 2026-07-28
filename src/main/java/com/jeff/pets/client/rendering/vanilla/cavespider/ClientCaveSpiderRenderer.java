package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class ClientCaveSpiderRenderer extends PetRenderer<ClientCaveSpider, SpiderModel<ClientCaveSpider>> {

    public ClientCaveSpiderRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    protected void scale(ClientCaveSpider caveSpider, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientCaveSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png");
    }
}
