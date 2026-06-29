package com.jeff.pets.rendering.vanilla.irongolem;

import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<@NotNull ClientIronGolem, @NotNull ClientIronGolemModel<ClientIronGolem>> {

    public static final ModelLayerLocation IRON_GOLEM_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientirongolem"), "main");

    public ClientIronGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientIronGolemModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientIronGolem livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem.png");
    }
}
