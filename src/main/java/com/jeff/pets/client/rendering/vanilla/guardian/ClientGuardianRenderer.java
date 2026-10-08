package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientGuardianRenderer extends PetRenderer {

    public ClientGuardianRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.7F);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientGuardian livingEntityRenderState = (ClientGuardian) __e;
        return new ResourceLocation("minecraft", "textures/entity/guardian.png");
    }
}