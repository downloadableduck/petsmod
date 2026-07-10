package com.jeff.pets.client.rendering.vanilla.vindicator;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.vanilla.hostile.ClientVindicator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVindicatorRenderer extends PetRenderer<@NotNull ClientVindicator, @NotNull ClientEvokerModel<ClientVindicator>> {


    public ClientVindicatorRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientEvokerModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVindicator livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vindicator.png");
    }
}
