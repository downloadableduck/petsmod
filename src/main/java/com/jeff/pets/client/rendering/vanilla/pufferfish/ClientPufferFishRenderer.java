package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.client.renderer.entity.model.ModelPufferFishBig;
import net.minecraft.util.ResourceLocation;

public class ClientPufferFishRenderer extends PetRenderer<ClientPufferFish, ModelPufferFishBig> {

    public ClientPufferFishRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelPufferFishBig(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientPufferFish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/pufferfish.png");
    }
}