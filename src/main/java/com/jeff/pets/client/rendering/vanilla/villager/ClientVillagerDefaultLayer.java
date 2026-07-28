package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

public class ClientVillagerDefaultLayer extends LayerRenderer<ClientVillager, VillagerModel<ClientVillager>> {

    public ClientVillagerDefaultLayer(IEntityRenderer<ClientVillager, VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GlStateManager.pushMatrix();
        this.bindTexture(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"));
        this.getParentModel().render(villager, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsOnDamage() {
        return false;
    }
}
