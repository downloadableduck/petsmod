package com.jeff.pets.rendering.vanilla.warden;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWarden;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.WardenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWardenRenderer extends PetRenderer<@NotNull ClientWarden, @NotNull WardenRenderState, @NotNull WardenModel> {

    public static final ModelLayerLocation WARDEN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientwarden"), "main");

    public ClientWardenRenderer(EntityRendererProvider.Context context) {
        super(context, new WardenModel(context.bakeLayer(ModelLayers.WARDEN)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WardenRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/warden/warden.png");
    }

    @Override
    public WardenRenderState createRenderState() {
        return new WardenRenderState();
    }
}
