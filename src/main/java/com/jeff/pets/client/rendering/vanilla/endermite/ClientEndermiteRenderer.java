package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.render.model.entity.EndermiteModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<ClientEndermite> {

    public ClientEndermiteRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermiteModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEndermite livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/endermite.png");
    }
}
