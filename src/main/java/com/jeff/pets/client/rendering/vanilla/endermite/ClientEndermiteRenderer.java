package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientEndermiteRenderer extends PetRenderer {

    public ClientEndermiteRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.model.ModelSilverfish(), 0.75f);
    }

    protected void preRenderCallback(ClientEndermite endermite, float f) {
        GL11.glScalef(0.7f, 0.7f, 0.7f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientEndermite endermiteEntity = (ClientEndermite) __e;
        return new ResourceLocation("minecraft", "textures/entity/endermite.png");
    }
}