package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemRenderer extends PetRenderer {

    public ClientSnowGolemRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSnowMan(), 0.5F);
    }

    protected void preRenderCallback(ClientSnowGolem snowGolem, float f) {
        super.preRenderCallback(snowGolem, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientSnowGolem livingEntityRenderState = (ClientSnowGolem) __e;
        return new ResourceLocation("minecraft", "textures/entity/snow_golem.png");
    }
}