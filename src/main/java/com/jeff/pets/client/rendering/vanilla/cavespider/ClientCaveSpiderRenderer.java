package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.render.model.entity.SpiderModel;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCaveSpiderRenderer extends PetRenderer<ClientCaveSpider> {

    public ClientCaveSpiderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderModel(), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientCaveSpider caveSpider = (ClientCaveSpider) entity;
        com.jeff.pets.compat.GlStateManager.scalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientCaveSpider livingEntityRenderState = (ClientCaveSpider) entity;
        return new Identifier("minecraft", "textures/entity/spider/cave_spider.png");
    }
}
