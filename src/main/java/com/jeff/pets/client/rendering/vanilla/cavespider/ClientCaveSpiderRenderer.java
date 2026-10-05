package com.jeff.pets.client.rendering.vanilla.cavespider;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientCaveSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientCaveSpiderRenderer extends PetRenderer {

    public ClientCaveSpiderRenderer() {
        super(new ModelSpider(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase caveSpider, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png");
    }
}

