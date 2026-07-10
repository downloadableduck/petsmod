package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer extends RenderLayer<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> {

    public ClientVillagerDefaultLayer(RenderLayerParent<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, ClientVillager entityRenderState, float f, float g, float h, float j, float k, float l) {
        renderColoredCutoutModel(this.getParentModel(), new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"), poseStack, source, i, entityRenderState, 1, 1, 1);
    }
}
