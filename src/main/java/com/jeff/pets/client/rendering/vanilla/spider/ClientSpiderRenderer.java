package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSpiderRenderer extends PetRenderer<@NotNull ClientSpider, @NotNull SpiderModel<ClientSpider>> {

    public ClientSpiderRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}
