package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class ClientGuardianRenderer extends PetRenderer<@NotNull ClientGuardian, @NotNull ClientGuardianModel<ClientGuardian>> {

    public ClientGuardianRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientGuardian livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/guardian.png");
    }
}
