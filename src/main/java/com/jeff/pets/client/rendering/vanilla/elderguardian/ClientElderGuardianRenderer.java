package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import net.minecraft.util.ResourceLocation;

public class ClientElderGuardianRenderer extends PetRenderer<ClientElderGuardian, ClientGuardianModel<ClientElderGuardian>> {

    public ClientElderGuardianRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public void scale(ClientElderGuardian elderGuardian, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(2.35f, 2.35f, 2.35f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientElderGuardian livingEntityRenderState) {
        //livingEntityRenderState.spike = 1;
        return new ResourceLocation("minecraft", "textures/entity/guardian_elder.png");
    }
}
