//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cavespider;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;

public class ClientCaveSpiderRenderer extends PetRenderer<ClientCaveSpider> {
    public ClientCaveSpiderRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SpiderEntityModel(), 0.75F);
    }

    protected void applyScale(LivingEntity entity, float f) {
        ClientCaveSpider caveSpider = (ClientCaveSpider)entity;
        GlStateManager.scalef(0.7F, 0.7F, 0.7F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientCaveSpider livingEntityRenderState = (ClientCaveSpider)entity;
        return "textures/mob/spider/cave_spider.png";
    }
}
