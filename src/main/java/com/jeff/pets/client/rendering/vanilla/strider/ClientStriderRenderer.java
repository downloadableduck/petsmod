package com.jeff.pets.client.rendering.vanilla.strider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientStriderRenderer extends PetRenderer<ClientStrider, ClientStriderMOdel> {

    public String striderTexturePath;

    public ClientStriderRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientStriderMOdel(), 0.5F);
    }

    @Override
    protected void scale(ClientStrider livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public ResourceLocation getTextureLocation(ClientStrider striderRenderState) {
        if (Objects.equals(CONFIG.striderSkin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(CONFIG.striderSkin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return new ResourceLocation("minecraft", striderTexturePath);
    }
}
