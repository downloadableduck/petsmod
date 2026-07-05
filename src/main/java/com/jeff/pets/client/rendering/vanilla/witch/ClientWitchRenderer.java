package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.client.model.WitchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWitchRenderer extends PetRenderer<@NotNull ClientWitch, @NotNull WitchModel<ClientWitch>> {

    public static final ModelLayerLocation WITCH_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwitch"), "main");

    public ClientWitchRenderer(EntityRendererProvider.Context context) {
        super(context, new WitchModel<>(context.bakeLayer(ModelLayers.WITCH)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWitch livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/witch.png");
    }
}
