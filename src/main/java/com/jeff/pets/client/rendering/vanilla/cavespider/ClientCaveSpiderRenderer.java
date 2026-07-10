package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends PetRenderer<@NotNull ClientCaveSpider, @NotNull SpiderModel<ClientCaveSpider>> {

    public ClientCaveSpiderRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    protected void scale(ClientCaveSpider caveSpider, PoseStack poseStack, float f) {
        poseStack.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCaveSpider livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png");
    }
}
