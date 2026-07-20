package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.render.model.entity.BlazeModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer<@NotNull ClientBlaze, @NotNull BlazeModel<ClientBlaze>> {

    public ClientBlazeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new BlazeModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientBlaze livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/blaze.png");
    }
}
