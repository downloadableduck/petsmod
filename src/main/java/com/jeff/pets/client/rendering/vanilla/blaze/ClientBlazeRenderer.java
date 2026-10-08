package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer {

    public ClientBlazeRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelBlaze(), 0.75f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientBlaze livingEntityRenderState = (ClientBlaze) __e;
        return new ResourceLocation("minecraft", "textures/entity/blaze.png");
    }
}