package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCow;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer {

    public ClientCowRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientCow LivingEntityRenderState = (ClientCow) __e;
        return new Identifier("minecraft", "textures/entity/cow/cow.png");
    }

    protected void preRenderCallback(ClientCow state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }
}