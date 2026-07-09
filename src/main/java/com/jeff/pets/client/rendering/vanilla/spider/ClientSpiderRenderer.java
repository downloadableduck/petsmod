package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSpiderRenderer extends PetRenderer<@NotNull ClientSpider, @NotNull SpiderModel<ClientSpider>> {

    public static final ModelLayerLocation SPIDER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientspider"), "main");

    public ClientSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/spider.png");
    }
}
