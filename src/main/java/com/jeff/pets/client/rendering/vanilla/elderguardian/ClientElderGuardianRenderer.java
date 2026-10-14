package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;

import org.jetbrains.annotations.NotNull;

public class ClientElderGuardianRenderer extends PetRenderer<ClientElderGuardian> {

    public ClientElderGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.75f);
    }

    @Override
    public void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientElderGuardian elderGuardian = (ClientElderGuardian) entity;
        com.jeff.pets.compat.GlStateManager.scalef(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientElderGuardian livingEntityRenderState = (ClientElderGuardian) entity;
        //livingEntityRenderState.spike = 1;
        return ("textures/mob/guardian_elder.png");
    }
}
