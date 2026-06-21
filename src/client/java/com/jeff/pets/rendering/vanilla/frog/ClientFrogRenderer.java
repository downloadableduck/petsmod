package com.jeff.pets.rendering.vanilla.frog;

import com.jeff.pets.mob.vanilla.passive.ClientFrog;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.FrogModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientFrogRenderer extends PetRenderer<@NotNull ClientFrog, @NotNull FrogRenderState, @NotNull FrogModel> {

    public static ModelLayerLocation FROG_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientfrog"), "main");
    public String frogTexturePath;

    public ClientFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogModel(context.bakeLayer(ModelLayers.FROG)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(FrogRenderState frogRenderState) {
        switch (CONFIG.frogSkin) {
            case "temperate" -> frogTexturePath = "textures/entity/frog/temperate_frog.png";
            case "warm" -> frogTexturePath = "textures/entity/frog/warm_frog.png";
            case "cold" -> frogTexturePath = "textures/entity/frog/cold_frog.png";
            case null, default -> frogTexturePath = "textures/entity/frog/temperate_frog.png";
        }
        return ResourceLocation.withDefaultNamespace(frogTexturePath);
    }

    public FrogRenderState createRenderState() {
        return new FrogRenderState();
    }

    public void extractRenderState(ClientFrog frog, FrogRenderState state, float f) {
        super.extractRenderState(frog, state, f);
        state.isSwimming = frog.isInWater();
    }
}
