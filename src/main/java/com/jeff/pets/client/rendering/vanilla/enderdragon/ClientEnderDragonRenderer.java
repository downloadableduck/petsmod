package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientEnderDragonRenderer extends PetRenderer {

    public ClientEnderDragonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    protected void preRenderCallback(@NotNull ClientEnderDragon livingEntityRenderState, float f) {
        super.preRenderCallback(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) __e;
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}