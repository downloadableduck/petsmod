package com.jeff.pets.rendering.vanilla.bat;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer<@NotNull ClientBat, @NotNull BatRenderState, @NotNull BatModel> {
    public static final ModelLayerLocation BAT_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/bat.png"), "main");

    public ClientBatRenderer(EntityRendererProvider.Context context) {
        super(context, new BatModel(context.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BatRenderState batRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/bat.png");
    }

    @Override
    public BatRenderState createRenderState() {
        return new BatRenderState();
    }

    @Override
    public void extractRenderState(ClientBat bat, BatRenderState state, float f) {
        super.extractRenderState(bat, state, f);
        state.flyAnimationState.start(0);
    }
}
