package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.render.model.entity.SpiderModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSpiderRenderer extends PetRenderer<@NotNull ClientSpider, @NotNull SpiderModel<ClientSpider>> {

    public ClientSpiderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSpider livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/spider/spider.png");
    }
}
