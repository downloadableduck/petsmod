package com.jeff.pets.client.rendering.vanilla.villager;

import net.minecraft.entity.EntityLivingBase;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ClientVillagerDefaultLayer implements LayerRenderer {

    private final RenderLiving renderer;

    public ClientVillagerDefaultLayer(RenderLiving renderLayerParent) {
        this.renderer = renderLayerParent;
    }

    @Override
    public void render(EntityLivingBase __e, float f, float g, float h, float i, float j, float k, float l) {
        ClientVillager villager = (ClientVillager) __e;
        GlStateManager.pushMatrix();
        this.renderer.bindTexture(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getMainModel().render(__e, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
