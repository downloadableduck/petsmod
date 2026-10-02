package com.jeff.pets.client.rendering.vanilla.spider;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.util.ResourceLocation;

public class ClientSpiderRenderer extends PetRenderer<ClientSpider, ModelSpider> {

    public ClientSpiderRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSpider(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientSpider livingEntityRenderState = (ClientSpider) __e;
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}
