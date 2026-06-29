package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends PetRenderer<@NotNull ClientCaveSpider, @NotNull SpiderModel<ClientCaveSpider>> {
    public static final ModelLayerLocation CAVE_SPIDER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcavespider"), "main");

    public ClientCaveSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.CAVE_SPIDER)), 0.75f);
    }

    @Override
    protected void scale(ClientCaveSpider caveSpider, PoseStack poseStack, float f) {
        poseStack.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCaveSpider livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/spider/cave_spider.png");
    }
}
