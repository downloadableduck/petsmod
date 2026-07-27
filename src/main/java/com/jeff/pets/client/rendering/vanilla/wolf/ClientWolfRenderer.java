package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<ClientWolf, ClientWolfModel> {

    public ClientWolfRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    protected void scale(ClientWolf livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientWolf livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new ResourceLocation("minecraft", wolfTexturePath);
    }

    @Override
    public void render(ClientWolf wolf, float f, float g, com.mojang.blaze3d.matrix.MatrixStack poseStack, net.minecraft.client.renderer.IRenderTypeBuffer source, int i) {
        super.render(wolf, f, g, poseStack, source, i);
        wolf.setSitting(wolf.isPassenger());
    }
}
