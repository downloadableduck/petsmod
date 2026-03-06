package com.jeff.pets.vanilla.bat;

import com.jeff.pets.vanilla.passive.ClientBat;
import net.minecraft.client.model.ambient.BatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends MobRenderer<@NotNull ClientBat, @NotNull BatRenderState, @NotNull BatModel> {
    public static final ModelLayerLocation BAT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/bat.png"), "main");

    public ClientBatRenderer(EntityRendererProvider.Context context) {
        super(context, new BatModel(context.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    public @NotNull Identifier getTextureLocation(BatRenderState batRenderState) {
        return BAT_LOCATION.model();
    }

    public BatRenderState createRenderState() {
        return new BatRenderState();
    }

    public void extractRenderState(ClientBat bat, BatRenderState batRenderState, float f) {
        super.extractRenderState(bat, batRenderState, f);
        /*batRenderState.isResting = bat.isResting();
        batRenderState.flyAnimationState.copyFrom(bat.flyAnimationState);
        batRenderState.restAnimationState.copyFrom(bat.restAnimationState);*/
    }
}
