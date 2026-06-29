package com.jeff.pets.rendering.vanilla.bat;

import com.jeff.pets.mob.vanilla.passive.ClientBat;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer<@NotNull ClientBat, @NotNull ClientBatModel> {
    public static final ModelLayerLocation BAT_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/bat.png"), "main");

    public ClientBatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientBatModel(context.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientBat batRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/bat.png");
    }
}
