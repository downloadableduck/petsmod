package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.util.ResourceLocation;


public class ClientGuardianRenderer extends PetRenderer<ClientGuardian, ClientGuardianModel<ClientGuardian>> {

    public ClientGuardianRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientGuardian livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/guardian.png");
    }
}
