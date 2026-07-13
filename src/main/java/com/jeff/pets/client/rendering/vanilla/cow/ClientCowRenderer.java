package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCow;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer<ClientCow, ClientCowModel<ClientCow>> {

    public ClientCowRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientCowModel<>(), 0.7F);
    }

    public ResourceLocation getTextureLocation(ClientCow LivingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/cow/cow.png");
    }

    @Override
    protected void scale(ClientCow state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
