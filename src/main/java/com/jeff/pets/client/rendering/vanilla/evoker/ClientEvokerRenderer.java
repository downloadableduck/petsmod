package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEvokerRenderer extends PetRenderer<@NotNull ClientEvoker, @NotNull ClientEvokerModel<ClientEvoker>> {

    public ClientEvokerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientEvokerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientEvoker livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/evoker.png");
    }
}
