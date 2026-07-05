package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<@NotNull ClientEnderman, @NotNull EndermanModel<ClientEnderman>> {
    public static final ModelLayerLocation ENDERMAN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientenderman"), "main");

    public ClientEndermanRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5f);
        this.addLayer(new EnderEyesLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEnderman enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}
