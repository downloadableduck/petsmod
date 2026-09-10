package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer implements FeatureRenderer<ClientVillager> {

    private final ClientVillagerRenderer renderer;

    public ClientVillagerDefaultLayer(ClientVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(@NotNull ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GlStateManager.pushMatrix();
        this.renderer.bindTexture(new Identifier("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getModel().render(villager, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean combineTextures() {
        return false;
    }
}