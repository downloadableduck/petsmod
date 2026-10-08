package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientElderGuardianRenderer extends PetRenderer {

    public ClientElderGuardianRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.7F);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientElderGuardian livingEntityRenderState = (ClientElderGuardian) __e;
        return new ResourceLocation("minecraft", "textures/entity/guardian_elder.png");
    }

    protected void preRenderCallback(ClientElderGuardian livingEntityRenderState, float f) {
        GL11.glScalef(2.35F, 2.35F, 2.35F);
    }
}