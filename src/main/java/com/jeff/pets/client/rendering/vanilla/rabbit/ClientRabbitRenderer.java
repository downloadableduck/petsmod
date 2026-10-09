package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer {

    public ClientRabbitRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    protected void preRenderCallback(ClientRabbit state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientRabbit livingEntityRenderState = (ClientRabbit) __e;
        return new Identifier("minecraft", "textures/entity/rabbit/brown.png");
    }
}