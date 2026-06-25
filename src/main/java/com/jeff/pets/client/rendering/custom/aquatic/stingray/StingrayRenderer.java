package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayRenderState, StingrayModel> {

    public static final ModelLayerLocation STINGRAY_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "stingray"), "main");

    public StingrayRenderer(EntityRendererProvider.Context context) {
        super(context, new StingrayModel(context.bakeLayer(STINGRAY_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull StingrayRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public @NotNull StingrayRenderState createRenderState() {
        return new StingrayRenderState();
    }

    @Override
    public void extractRenderState(Stingray stingray, StingrayRenderState state, float f) {
        super.extractRenderState(stingray, state, f);
        state.flapTime = stingray.flap + state.ageInTicks;
    }
}
