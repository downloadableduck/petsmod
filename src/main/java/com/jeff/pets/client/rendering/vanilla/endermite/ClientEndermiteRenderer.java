package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<@NotNull ClientEndermite, @NotNull EndermiteModel<ClientEndermite>> {

    public ClientEndermiteRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new EndermiteModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEndermite livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/endermite.png");
    }
}
