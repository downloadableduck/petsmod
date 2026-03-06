package com.jeff.pets.vanilla.creaking;

import com.jeff.pets.vanilla.hostile.ClientCreaking;
import net.fabricmc.fabric.mixin.client.rendering.ModelPartAccessor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.creaking.CreakingModel;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import net.minecraft.client.renderer.feature.ModelPartFeatureRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ClientCreakingRenderer extends MobRenderer<@NotNull ClientCreaking, @NotNull CreakingRenderState, @NotNull CreakingModel> {

    public static final ModelLayerLocation CREAKING_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcreaking"), "main");

    public ClientCreakingRenderer(EntityRendererProvider.Context context) {
        super(context, new CreakingModel(context.bakeLayer(ModelLayers.CREAKING)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(CreakingRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/creaking/creaking.png");
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
