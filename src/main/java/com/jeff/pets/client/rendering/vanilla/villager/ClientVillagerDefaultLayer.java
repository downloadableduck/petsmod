package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer extends FeatureRenderer<@NotNull ClientVillager, @NotNull VillagerResemblingModel<ClientVillager>> {

    public ClientVillagerDefaultLayer(FeatureRendererContext<@NotNull ClientVillager, @NotNull VillagerResemblingModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GlStateManager.pushMatrix();
        this.bindTexture(new Identifier("minecraft", "textures/entity/villager/type/plains.png"));
        this.getModel().render(villager, f, g, i, j, k, l);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}
