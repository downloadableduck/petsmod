package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer {

    public ClientPufferFishRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.model.ModelSquid(), 0.75f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientPufferFish livingEntityRenderState = (ClientPufferFish) __e;
        return new ResourceLocation("minecraft", "textures/entity/fish/pufferfish.png");
    }
}