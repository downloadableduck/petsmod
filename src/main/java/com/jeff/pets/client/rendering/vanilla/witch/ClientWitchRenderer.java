package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitchRenderer extends PetRenderer {

    public ClientWitchRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.WitchEntityModel(0), 0.75f);
    }

    protected void preRenderCallback(ClientWitch witch, float f) {
        super.scale(witch, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientWitch livingEntityRenderState = (ClientWitch) __e;
        return new Identifier("minecraft", "textures/entity/witch.png");
    }
}