package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;


public class ClientVillagerDefaultLayer implements PetRenderLayer {

    private final PetRenderer renderer;

    public ClientVillagerDefaultLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(net.minecraft.entity.mob.MobEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientVillager villager = (ClientVillager) entity;
        GlStateManager.pushMatrix();
        //this.renderer.bindTexture(("textures/mob/villager/type/plains.png"));
        this.renderer.getModel().render(villager, f, g, h, i, j, k);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
