package com.jeff.pets.rendering.vanilla.witch;

import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.WitchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitchRenderer extends PetRenderer<@NotNull ClientWitch, @NotNull WitchModel<ClientWitch>> {

    public static final ModelLayerLocation WITCH_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientwitch"), "main");

    public ClientWitchRenderer(EntityRendererProvider.Context context) {
        super(context, new WitchModel<>(context.bakeLayer(ModelLayers.WITCH)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWitch livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/witch.png");
    }
}
