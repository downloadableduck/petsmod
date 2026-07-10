package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.client.model.PufferfishBigModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer<@NotNull ClientPufferFish, @NotNull PufferfishBigModel<ClientPufferFish>> {

    public ClientPufferFishRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new PufferfishBigModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPufferFish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/pufferfish.png");
    }
}