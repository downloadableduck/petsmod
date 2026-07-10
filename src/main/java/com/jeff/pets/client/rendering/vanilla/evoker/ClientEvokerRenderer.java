package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEvokerRenderer extends PetRenderer<@NotNull ClientEvoker, @NotNull ClientEvokerModel<ClientEvoker>> {

    public ClientEvokerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientEvokerModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEvoker livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/evoker.png");
    }
}
