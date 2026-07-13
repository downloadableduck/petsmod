package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<ClientRabbit, ClientRabbitModel> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    @Override
    protected void scale(ClientRabbit livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientRabbit rabbitRenderState) {
        if (CONFIG.activePet.equals("brown")) {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        } else if (CONFIG.activePet.equals("white")) {
            rabbitTextureLocation = "textures/entity/rabbit/white.png";
        } else if (CONFIG.activePet.equals("black")) {
            rabbitTextureLocation = "textures/entity/rabbit/black.png";
        } else if (CONFIG.activePet.equals("gold")) {
            rabbitTextureLocation = "textures/entity/rabbit/gold.png";
        } else if (CONFIG.activePet.equals("salt")) {
            rabbitTextureLocation = "textures/entity/rabbit/salt.png";
        } else if (CONFIG.activePet.equals("splotched")) {
            rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
        } else if (CONFIG.activePet.equals("killer")) {
            rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
        } else if (CONFIG.activePet.equals("toast")) {
            rabbitTextureLocation = "textures/entity/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new ResourceLocation("minecraft", rabbitTextureLocation);
    }
}
