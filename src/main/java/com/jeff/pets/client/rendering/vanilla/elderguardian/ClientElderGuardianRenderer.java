package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientElderGuardianRenderer extends PetRenderer<@NotNull ClientElderGuardian> {

    public ClientElderGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientElderGuardian livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/guardian_elder.png");
    }

    @Override
    protected void scale(ClientElderGuardian livingEntityRenderState, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scale(2.35F, 2.35F, 2.35F);
    }
}