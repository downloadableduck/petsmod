package com.jeff.pets.client.rendering.vanilla.villager;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ClientVillagerDefaultLayer implements LayerRenderer {

    private final RendererLivingEntity renderer;

    public ClientVillagerDefaultLayer(RendererLivingEntity renderLayerParent) {
        this.renderer = renderLayerParent;
    }

    @Override
    public void render( final EntityLivingBase villager, float f, float g, float h, float i, float j, float k, float l) {
        GlStateManager.pushMatrix();
        this.renderer.bindTexture(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getMainModel().render(villager, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}


