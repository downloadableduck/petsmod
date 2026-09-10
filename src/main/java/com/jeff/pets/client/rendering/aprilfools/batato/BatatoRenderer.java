package com.jeff.pets.client.rendering.aprilfools.batato;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.Batato;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class BatatoRenderer extends PetRenderer<@NotNull Batato, @NotNull BatRenderState, @NotNull BatatoModel> {

    public static final ModelLayerLocation BATATO_LOCAITON = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "batato"), "main");

    public BatatoRenderer(EntityRendererProvider.Context context) {
        super(context, new BatatoModel(context.bakeLayer(BATATO_LOCAITON)), 0.25f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(BatRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/batato.png");
    }

    @Override
    public BatRenderState createRenderState() {
        return new BatRenderState();
    }

    @Override
    public void extractRenderState(Batato batato, BatRenderState state, float f) {
        super.extractRenderState(batato, state, f);
        state.flyAnimationState.start(0);
    }
}
