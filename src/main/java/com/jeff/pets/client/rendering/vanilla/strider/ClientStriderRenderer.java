package com.jeff.pets.client.rendering.vanilla.strider;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientStriderRenderer extends PetRenderer<@NotNull ClientStrider, @NotNull ClientStriderMOdel> {

    public String striderTexturePath;

    public ClientStriderRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientStriderMOdel(), 0.5F);
    }

    @Override
    protected void scale(@NotNull ClientStrider livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getTextureLocation(ClientStrider striderRenderState) {
        if (Objects.equals(CONFIG.striderSkin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(CONFIG.striderSkin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return new ResourceLocation("minecraft", striderTexturePath);
    }
}
