package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer {

    public ClientEndermanRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelEnderman(), 0.5f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientEnderman livingEntityRenderState = (ClientEnderman) __e;
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}