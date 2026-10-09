package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientCaveSpiderRenderer extends PetRenderer {

    public ClientCaveSpiderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SpiderEntityModel(), 0.75f);
    }

    protected void preRenderCallback(ClientCaveSpider caveSpider, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientCaveSpider livingEntityRenderState = (ClientCaveSpider) __e;
        return new Identifier("minecraft", "textures/entity/spider/cave_spider.png");
    }
}