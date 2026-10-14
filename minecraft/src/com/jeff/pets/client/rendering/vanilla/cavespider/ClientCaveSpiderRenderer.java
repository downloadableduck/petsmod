package com.jeff.pets.client.rendering.vanilla.cavespider;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelSpider;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

public class ClientCaveSpiderRenderer extends PetRenderer {

    public ClientCaveSpiderRenderer() {
        super(new ModelSpider(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving caveSpider, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/spider/cave_spider.png";
    }
}

