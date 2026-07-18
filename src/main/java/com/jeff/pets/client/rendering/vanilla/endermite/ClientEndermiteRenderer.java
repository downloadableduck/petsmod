package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.render.entity.model.EndermiteEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<@NotNull ClientEndermite, @NotNull EndermiteEntityModel<ClientEndermite>> {

    public ClientEndermiteRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new EndermiteEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientEndermite livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/endermite.png");
    }
}
