package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.render.entity.model.BlazeEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer {

    public ClientBlazeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new BlazeEntityModel(), 0.75f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientBlaze livingEntityRenderState = (ClientBlaze) __e;
        return new Identifier("minecraft", "textures/entity/blaze.png");
    }
}