package com.jeff.pets.vanilla.cavespider;

import com.jeff.pets.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends MobRenderer<@NotNull ClientCaveSpider, @NotNull LivingEntityRenderState, @NotNull ClientCaveSpiderModel> {
    public static final ModelLayerLocation CAVE_SPIDER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcavespider"), "main");

    public ClientCaveSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCaveSpiderModel(context.bakeLayer(ModelLayers.CAVE_SPIDER)), 0.75f);
        this.shadowRadius = 0.56f;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/spider/cave_spider.png");
    }
}
