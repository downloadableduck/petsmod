package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer {

    public ClientChickenRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel(), 0.3F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientChicken livingEntityRenderState = (ClientChicken) __e;
        return new Identifier("minecraft", "textures/entity/chicken.png");
    }

    protected void preRenderCallback(ClientChicken state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }
}