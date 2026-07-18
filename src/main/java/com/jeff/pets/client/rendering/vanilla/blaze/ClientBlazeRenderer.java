package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.render.entity.model.BlazeEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer<@NotNull ClientBlaze, @NotNull BlazeEntityModel<ClientBlaze>> {

    public ClientBlazeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new BlazeEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientBlaze livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/blaze.png");
    }
}
