package com.jeff.pets.client.rendering.vanilla.frog;

import com.jeff.pets.mob.vanilla.passive.ClientFrog;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFrogRenderer extends PetRenderer<@NotNull ClientFrog, @NotNull FrogRenderState, @NotNull FrogModel> {

    public static ModelLayerLocation FROG_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientfrog"), "main");
    public String frogTexturePath;

    public ClientFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogModel(context.bakeLayer(ModelLayers.FROG)), 0.3F);
    }

    public @NotNull Identifier getTextureLocation(FrogRenderState frogRenderState) {
        switch (CONFIG.frogSkin) {
            case "temperate" -> frogTexturePath = "textures/entity/frog/frog_temperate.png";
            case "warm" -> frogTexturePath = "textures/entity/frog/frog_warm.png";
            case "cold" -> frogTexturePath = "textures/entity/frog/frog_cold.png";
            case null, default -> frogTexturePath = "textures/entity/frog/frog_temperate.png";
        }
        return Identifier.withDefaultNamespace(frogTexturePath);
    }

    public FrogRenderState createRenderState() {
        return new FrogRenderState();
    }

    public void extractRenderState(ClientFrog frog, FrogRenderState state, float f) {
        super.extractRenderState(frog, state, f);
        state.isSwimming = frog.isInWater();
    }
}
