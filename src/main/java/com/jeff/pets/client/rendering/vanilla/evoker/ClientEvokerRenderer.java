package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEvokerRenderer extends PetRenderer<ClientEvoker> {

    public ClientEvokerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEvokerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEvoker livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/evoker.png");
    }
}
