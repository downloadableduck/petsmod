package com.jeff.pets.client.rendering.vanilla.tadpole;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientTadpole;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientTadpoleRenderer extends PetRenderer<@NotNull ClientTadpole, @NotNull ClientTadpoleModel> {
    public static final ModelLayerLocation TADPOLE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clienttadpole"), "main");

    public ClientTadpoleRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientTadpoleModel(context.bakeLayer(ModelLayers.TADPOLE)), 0.75F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientTadpole livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/tadpole/tadpole.png");
    }
}
