package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientElderGuardianRenderer extends PetRenderer<@NotNull ClientElderGuardian, @NotNull ClientGuardianModel<ClientElderGuardian>> {

    public ClientElderGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public void applyScale(ClientElderGuardian elderGuardian, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientElderGuardian livingEntityRenderState) {
        //livingEntityRenderState.spike = 1;
        return new Identifier("minecraft", "textures/entity/guardian_elder.png");
    }
}
