package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.render.model.entity.SpiderModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends PetRenderer<@NotNull ClientCaveSpider, @NotNull SpiderModel<ClientCaveSpider>> {

    public ClientCaveSpiderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    protected void applyScale(ClientCaveSpider caveSpider, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientCaveSpider livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/spider/cave_spider.png");
    }
}
