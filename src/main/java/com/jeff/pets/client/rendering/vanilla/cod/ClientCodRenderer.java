package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCodRenderer extends PetRenderer<@NotNull ClientCod, @NotNull CodModel<ClientCod>> {
    public static final ModelLayerLocation COD_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcod"), "main");

    public ClientCodRenderer(EntityRendererProvider.Context context) {
        super(context, new CodModel(context.bakeLayer(ModelLayers.COD)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCod livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/fish/cod.png");
    }
}
