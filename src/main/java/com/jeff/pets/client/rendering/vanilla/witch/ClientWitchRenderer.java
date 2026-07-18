package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.render.entity.model.WitchEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitchRenderer extends PetRenderer<@NotNull ClientWitch, @NotNull WitchEntityModel<ClientWitch>> {

    public ClientWitchRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new WitchEntityModel<>(0), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientWitch livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/witch.png");
    }
}
