package com.jeff.pets.vanilla.frog;

import com.jeff.pets.vanilla.passive.ClientFrog;
import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientFrogRenderer extends MobRenderer<@NotNull ClientFrog, @NotNull FrogRenderState, @NotNull FrogModel> {

    public static ModelLayerLocation FROG_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientfrog"), "main");
    public String frogTexturePath;

    public ClientFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogModel(context.bakeLayer(ModelLayers.FROG)), 0.3F);
    }

    public @NotNull Identifier getTextureLocation(FrogRenderState frogRenderState) {
        switch (CONFIG.frogSkin) {
            case "temperate" -> frogTexturePath = "textures/entity/frog/temperate_frog.png";
            case "warm" -> frogTexturePath = "textures/entity/frog/warm_frog.png";
            case "cold" -> frogTexturePath = "textures/entity/frog/cold_frog.png";
            case null, default -> frogTexturePath = "textures/entity/frog/temperate_frog.png";
        }
        return Identifier.withDefaultNamespace(frogTexturePath);
    }

    public FrogRenderState createRenderState() {
        return new FrogRenderState();
    }

    public void extractRenderState(ClientFrog frog, FrogRenderState frogRenderState, float f) {
        super.extractRenderState(frog, frogRenderState, f);
        frogRenderState.isSwimming = frog.isInWater();
        /*frogRenderState.jumpAnimationState.copyFrom(frog.jumpAnimationState);
        frogRenderState.croakAnimationState.copyFrom(frog.croakAnimationState);
        frogRenderState.tongueAnimationState.copyFrom(frog.tongueAnimationState);
        frogRenderState.swimIdleAnimationState.copyFrom(frog.swimIdleAnimationState);
        frogRenderState.texture = ((FrogVariant)frog.getVariant().value()).assetInfo().texturePath();*/
    }
}
