package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer<ClientHusk, ClientZombieModel<ClientHusk>> {

    public ClientHuskRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientZombieModel<>(0), 0.75F);
    }

    @Override
    protected void scale(ClientHusk state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientHusk livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void setupRotations(ClientHusk husk, MatrixStack poseStack, float f, float g, float i) {
        super.setupRotations(husk, poseStack, f, g, i);
        if (husk.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
