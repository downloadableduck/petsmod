//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.spider;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;

public class ClientSpiderRenderer extends PetRenderer<ClientSpider> {
    public ClientSpiderRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SpiderEntityModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientSpider livingEntityRenderState = (ClientSpider)entity;
        return "textures/mob/spider/spider.png";
    }
}
