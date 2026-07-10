package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<@NotNull ClientWolf, @NotNull ClientWolfModel> {

    public ClientWolfRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientWolf livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWolf livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new ResourceLocation("minecraft", wolfTexturePath);
    }

    @Override
    public void render(ClientWolf wolf, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(wolf, f, g, poseStack, source, i);
        wolf.setInSittingPose(wolf.isPassenger());
    }
}
