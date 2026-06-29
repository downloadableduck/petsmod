package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class ClientGuardianRenderer extends PetRenderer<@NotNull ClientGuardian, @NotNull ClientGuardianModel<ClientGuardian>> {
    public static final ModelLayerLocation GUARDIAN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientguardian"), "main");

    public ClientGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientGuardianModel<>(context.bakeLayer(ModelLayers.GUARDIAN)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientGuardian livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/guardian.png");
    }
}
