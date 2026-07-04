package com.jeff.pets.rendering.vanilla.vindicator;

import com.jeff.pets.mob.vanilla.hostile.ClientVindicator;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.evoker.ClientEvokerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVindicatorRenderer extends PetRenderer<@NotNull ClientVindicator, @NotNull ClientEvokerModel<ClientVindicator>> {

    public static final ModelLayerLocation VINDICATOR_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientvindicator"), "main");

    public ClientVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel<>(context.bakeLayer(ModelLayers.VINDICATOR)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVindicator livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vindicator.png");
    }
}
