package com.jeff.pets.rendering.vanilla.creaking;

import com.jeff.pets.mob.vanilla.hostile.ClientCreaking;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.CreakingModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientCreakingRenderer extends PetRenderer<@NotNull ClientCreaking, @NotNull CreakingRenderState, @NotNull CreakingModel> {

    public static final ModelLayerLocation CREAKING_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcreaking"), "main");

    public ClientCreakingRenderer(EntityRendererProvider.Context context) {
        super(context, new CreakingModel(context.bakeLayer(ModelLayers.CREAKING)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CreakingRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/creaking/creaking.png");
    }

    @Override
    public CreakingRenderState createRenderState() {
        return new CreakingRenderState();
    }

    @Override
    public void extractRenderState(ClientCreaking creaking, CreakingRenderState state, float f) {
        super.extractRenderState(creaking, state, f);
        state.isFullyFrozen = false;
        state.canMove = true;
    }
}
