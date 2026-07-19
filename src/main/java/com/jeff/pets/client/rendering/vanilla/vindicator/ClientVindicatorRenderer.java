package com.jeff.pets.client.rendering.vanilla.vindicator;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.vanilla.hostile.ClientVindicator;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVindicatorRenderer extends PetRenderer<@NotNull ClientVindicator, @NotNull ClientEvokerModel<ClientVindicator>> {


    public ClientVindicatorRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientEvokerModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientVindicator livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/vindicator.png");
    }
}
