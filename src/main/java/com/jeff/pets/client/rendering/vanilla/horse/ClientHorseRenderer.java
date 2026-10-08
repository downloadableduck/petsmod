package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer {

    public ClientHorseRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.7F);
    }

    protected void preRenderCallback(ClientHorse state, float f) {
        super.preRenderCallback(state, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientHorse livingEntityRenderState = (ClientHorse) __e;
        return new ResourceLocation("minecraft", "textures/entity/horse/horse_white.png");
    }
}