package com.jeff.pets.client.rendering.vanilla.ravager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientRavagerRenderer extends PetRenderer<@NotNull ClientRavager, @NotNull ClientRavagerModel> {

    public ClientRavagerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientRavagerModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientRavager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/ravager.png");
    }
}
