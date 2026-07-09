package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


public class ClientVillagerDefaultLayer extends RenderLayer< ClientVillager,  VillagerModel<ClientVillager>> {
    public static final ModelLayerLocation DEFAULT_VILLAGER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"), "main");

    public ClientVillagerDefaultLayer(RenderLayerParent< ClientVillager,  VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render( PoseStack poseStack,  MultiBufferSource source, int i, ClientVillager entityRenderState, float f, float g, float h, float j, float k, float l) {
        renderColoredCutoutModel(this.getParentModel(), DEFAULT_VILLAGER_LAYER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
    }
}
