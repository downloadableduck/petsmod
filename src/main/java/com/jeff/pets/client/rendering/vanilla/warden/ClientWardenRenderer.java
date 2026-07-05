package com.jeff.pets.client.rendering.vanilla.warden;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWarden;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWardenRenderer extends PetRenderer<@NotNull ClientWarden, @NotNull ClientWardenModel> {

    public static final ModelLayerLocation WARDEN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwarden"), "main");

    public ClientWardenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWardenModel(context.bakeLayer(ModelLayers.WARDEN)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWarden livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/warden/warden.png");
    }
}
