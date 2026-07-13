package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon, ClientEnderDragonModel> {

    public ClientEnderDragonRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientEnderDragonModel(), 0.75f);
    }

    @Override
    protected void scale(ClientEnderDragon livingEntityRenderState, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientEnderDragon livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
