package com.jeff.pets.vanilla.spider;

import com.jeff.pets.vanilla.neutral.ClientSpider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSpiderRenderer extends MobRenderer<@NotNull ClientSpider, @NotNull LivingEntityRenderState, @NotNull SpiderModel> {

    public static final ModelLayerLocation SPIDER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientspider"), "main");

    public ClientSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/spider/spider.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
