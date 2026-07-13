package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.client.renderer.entity.model.PufferFishBigModel;
import net.minecraft.util.ResourceLocation;

public class ClientPufferFishRenderer extends PetRenderer<ClientPufferFish, PufferFishBigModel<ClientPufferFish>> {

    public ClientPufferFishRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new PufferFishBigModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPufferFish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/pufferfish.png");
    }
}