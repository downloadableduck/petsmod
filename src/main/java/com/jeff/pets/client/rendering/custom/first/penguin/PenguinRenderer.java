package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer {

    public PenguinRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.75f);
    }

    public void renderModel(final Penguin penguin, float f, float partialTicks, float h, float i, float j, float k) {
        super.renderModel(penguin, f, partialTicks, h, i, j, k);
    }

    protected void preRenderCallback(@NotNull Penguin livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        Penguin livingEntityRenderState = (Penguin) __e;
        return new ResourceLocation(MOD_ID, "textures/entity/penguin/penguin.png");
    }
}