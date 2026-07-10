package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer<@NotNull ClientBat, @NotNull ClientBatModel> {

    public ClientBatRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    protected void scale(ClientBat bat, PoseStack poseStack, float f) {
        poseStack.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientBat batRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bat.png");
    }
}
