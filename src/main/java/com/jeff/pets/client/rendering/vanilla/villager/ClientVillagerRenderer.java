package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<ClientVillager, VillagerModel<ClientVillager>> {

    public ClientVillagerRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new VillagerModel<>(0), 0.5F);
        this.addLayer(new ClientVillagerDefaultLayer(this));
        this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(ClientVillager state, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientVillager villagerRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
    }
}
