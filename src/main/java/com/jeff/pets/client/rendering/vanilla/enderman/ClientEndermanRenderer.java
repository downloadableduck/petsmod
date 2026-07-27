package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.renderer.entity.layers.EndermanEyesLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.util.ResourceLocation;

public class ClientEndermanRenderer extends PetRenderer<ClientEnderman, EndermanModel<ClientEnderman>> {

    public ClientEndermanRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermanModel<>(0), 0.5f);
        this.addLayer(new EndermanEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ClientEnderman enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}
