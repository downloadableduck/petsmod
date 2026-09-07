package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.util.ResourceLocation;

public class ClientEndermanRenderer extends PetRenderer<ClientEnderman, ModelEnderman> {

    public ClientEndermanRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelEnderman(0), 0.5f);
        this.addLayer(new LayerEndermanEyes(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ClientEnderman enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}
