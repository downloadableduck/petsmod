package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<@NotNull ClientPolarBear, @NotNull ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientPolarBear livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bear/polarbear.png");
    }
}
