package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class ClientVillagerDefaultLayer implements LayerRenderer<ClientVillager> {

    private final RenderLiving<ClientVillager> renderer;

    public ClientVillagerDefaultLayer(RenderLiving<ClientVillager> renderLayerParent) {
        this.renderer = renderLayerParent;
    }

    @Override
    public void render(ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
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
