package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientBatRenderer extends PetRenderer {

    public ClientBatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    protected void preRenderCallback(ClientBat bat, float f) {
        super.scale(bat, f);
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientBat batRenderState = (ClientBat) __e;
        return new Identifier("minecraft", "textures/entity/bat.png");
    }
}