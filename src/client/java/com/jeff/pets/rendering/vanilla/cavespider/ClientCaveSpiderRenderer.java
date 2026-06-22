package com.jeff.pets.rendering.vanilla.cavespider;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends PetRenderer<@NotNull ClientCaveSpider, @NotNull LivingEntityRenderState, @NotNull ClientCaveSpiderModel> {
    public static final ModelLayerLocation CAVE_SPIDER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcavespider"), "main");

    public ClientCaveSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCaveSpiderModel(context.bakeLayer(ModelLayers.CAVE_SPIDER)), 0.75f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/spider/cave_spider.png");
    }
}
