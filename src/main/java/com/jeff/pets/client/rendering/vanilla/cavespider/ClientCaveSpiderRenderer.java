package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.renderer.entity.model.ModelSpider;
import net.minecraft.util.ResourceLocation;

public class ClientCaveSpiderRenderer extends PetRenderer<ClientCaveSpider, ModelSpider> {

    public ClientCaveSpiderRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSpider(), 0.75f);
    }

    @Override
    public void preRenderCallback(ClientCaveSpider caveSpider, float f) {
        net.minecraft.client.renderer.GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientCaveSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png");
    }
}
