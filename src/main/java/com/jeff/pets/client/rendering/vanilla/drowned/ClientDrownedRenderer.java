package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ClientDrownedRenderer extends PetRenderer {

    public ClientDrownedRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientDrowned livingEntityRenderState = (ClientDrowned) __e;
        return new Identifier("minecraft", "textures/entity/zombie/drowned.png");
    }

    public void setLivingAnimations(ClientDrowned state, float f, float g, float h) {
        if (state.vehicle != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }
}