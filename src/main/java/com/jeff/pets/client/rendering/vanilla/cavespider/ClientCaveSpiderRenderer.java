package com.jeff.pets.client.rendering.vanilla.cavespider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientCaveSpiderRenderer extends PetRenderer {

    public ClientCaveSpiderRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSpider(), 0.75f);
    }

    protected void preRenderCallback(ClientCaveSpider caveSpider, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientCaveSpider livingEntityRenderState = (ClientCaveSpider) __e;
        return new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png");
    }
}