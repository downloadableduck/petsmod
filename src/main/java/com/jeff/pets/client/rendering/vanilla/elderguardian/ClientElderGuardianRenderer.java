package com.jeff.pets.client.rendering.vanilla.elderguardian;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import net.minecraft.util.ResourceLocation;

public class ClientElderGuardianRenderer extends PetRenderer<ClientElderGuardian, ClientGuardianModel> {

    public ClientElderGuardianRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientElderGuardian elderGuardian = (ClientElderGuardian) __e;
        net.minecraft.client.renderer.GlStateManager.scalef(2.35f, 2.35f, 2.35f);

    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientElderGuardian livingEntityRenderState = (ClientElderGuardian) __e;
        //livingEntityRenderState.spike = 1;
        return new ResourceLocation("minecraft", "textures/entity/guardian_elder.png");
    }
}
