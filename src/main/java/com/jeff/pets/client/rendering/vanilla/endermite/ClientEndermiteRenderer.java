package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<@NotNull ClientEndermite, @NotNull EndermiteModel<ClientEndermite>> {

    public static final ModelLayerLocation ENDERMITE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientendermite"), "main");

    public ClientEndermiteRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermiteModel<>(context.bakeLayer(ModelLayers.ENDERMITE)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEndermite livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/endermite.png");
    }
}
