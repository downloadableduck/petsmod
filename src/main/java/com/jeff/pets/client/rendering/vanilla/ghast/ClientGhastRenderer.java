package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientGhastRenderer extends PetRenderer {

    public ClientGhastRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.GhastEntityModel(), 0.75f);
    }

    protected void preRenderCallback(ClientGhast state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientGhast livingEntityRenderState = (ClientGhast) __e;
        return new Identifier("minecraft", "textures/entity/ghast/ghast.png");
    }
}