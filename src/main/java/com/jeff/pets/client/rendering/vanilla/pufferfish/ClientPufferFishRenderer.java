package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.animal.fish.PufferfishBigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PufferfishRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer<@NotNull ClientPufferFish, @NotNull PufferfishRenderState, @NotNull PufferfishBigModel> {

    public static final ModelLayerLocation PUFFERFISH_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpufferfish"), "main");

    public ClientPufferFishRenderer(EntityRendererProvider.Context context) {
        super(context, new PufferfishBigModel(context.bakeLayer(ModelLayers.PUFFERFISH_BIG)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(PufferfishRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/fish/pufferfish.png");
    }

    @Override
    public PufferfishRenderState createRenderState() {
        return new PufferfishRenderState();
    }
}