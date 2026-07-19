package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer extends EntityRenderLayer<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> {

    public ClientVillagerDefaultLayer(EntityRenderLayerParent<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> renderLayerParent) {
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
    public boolean colorsWhenDamaged() {
        return false;
    }
}
