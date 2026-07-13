package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<ClientPolarBear, ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void scale(ClientPolarBear livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bear/polarbear.png");
    }
}
