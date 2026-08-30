package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;

public class ClientVillagerDefaultLayer implements EntityRenderLayer<ClientVillager> {

    private final net.minecraft.client.render.entity.MobRenderer renderer;

    public ClientVillagerDefaultLayer(net.minecraft.client.render.entity.MobRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GlStateManager.pushMatrix();
        this.renderer.bindTexture(new Identifier("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getModel().render(villager, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
