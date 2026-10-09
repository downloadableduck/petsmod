package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientSilverfishRenderer extends PetRenderer {

    public ClientSilverfishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SilverfishEntityModel(), 0.75f);
    }

    protected void preRenderCallback(ClientSilverfish silverfish, float f) {
        GL11.glScalef(0.7f, 0.7f, 0.7f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientSilverfish livingEntityRenderState = (ClientSilverfish) __e;
        return new Identifier("minecraft", "textures/entity/silverfish.png");
    }
}