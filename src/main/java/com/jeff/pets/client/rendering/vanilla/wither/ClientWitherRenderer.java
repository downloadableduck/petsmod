package com.jeff.pets.client.rendering.vanilla.wither;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientWither;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientWitherRenderer extends PetRenderer {

    public ClientWitherRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWitherModel(0), 0.75f);
    }

    protected void preRenderCallback(ClientWither livingEntityRenderState, float f) {
        GL11.glScalef(0.25f, 0.25f, 0.25f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientWither livingEntityRenderState = (ClientWither) __e;
        return new Identifier("minecraft", "textures/entity/wither/wither.png");
    }
}