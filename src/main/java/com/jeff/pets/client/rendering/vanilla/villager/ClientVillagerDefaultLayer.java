package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

public class ClientVillagerDefaultLayer extends LayerRenderer<ClientVillager, VillagerModel<ClientVillager>> {

    public ClientVillagerDefaultLayer(IEntityRenderer<ClientVillager, VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(com.mojang.blaze3d.matrix.MatrixStack poseStack, net.minecraft.client.renderer.IRenderTypeBuffer source, int i, ClientVillager entityRenderState, float f, float g, float h, float j, float k, float l) {
        renderColoredCutoutModel(this.getParentModel(), new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"), poseStack, source, i, entityRenderState, 1, 1, 1);
    }
}
