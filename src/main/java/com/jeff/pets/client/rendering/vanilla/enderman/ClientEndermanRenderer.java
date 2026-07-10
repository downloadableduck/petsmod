package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<@NotNull ClientEnderman, @NotNull EndermanModel<ClientEnderman>> {

    public ClientEndermanRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new EndermanModel<>(0), 0.5f);
        this.addLayer(new EnderEyesLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEnderman enderman) {
        return new ResourceLocation("minecraft", "textures/entity/enderman/enderman.png");
    }
}
