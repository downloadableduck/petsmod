package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.model.WitchModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitchRenderer extends PetRenderer<@NotNull ClientWitch, @NotNull WitchModel<ClientWitch>> {

    public ClientWitchRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new WitchModel<>(0), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWitch livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}
