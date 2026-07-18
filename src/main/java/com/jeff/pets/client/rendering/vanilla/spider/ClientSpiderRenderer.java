package com.jeff.pets.client.rendering.vanilla.spider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientSpider;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSpiderRenderer extends PetRenderer<@NotNull ClientSpider, @NotNull SpiderEntityModel<ClientSpider>> {

    public ClientSpiderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SpiderEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSpider livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/spider/spider.png");
    }
}
