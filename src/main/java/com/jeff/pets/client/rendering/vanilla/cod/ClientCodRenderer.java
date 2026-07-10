package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.client.model.CodModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCodRenderer extends PetRenderer<@NotNull ClientCod, @NotNull CodModel<ClientCod>> {

    public ClientCodRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new CodModel(), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCod livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/cod.png");
    }
}
