package com.jeff.pets.rendering.vanilla.blaze;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer<@NotNull ClientBlaze, @NotNull LivingEntityRenderState, @NotNull BlazeModel> {
    public static final ModelLayerLocation BLAZE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientblaze"), "main");

    public ClientBlazeRenderer(EntityRendererProvider.Context context) {
        super(context, new BlazeModel(context.bakeLayer(ModelLayers.BLAZE)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/blaze.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
