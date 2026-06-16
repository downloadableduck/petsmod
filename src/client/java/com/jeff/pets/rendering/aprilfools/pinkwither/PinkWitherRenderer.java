package com.jeff.pets.rendering.aprilfools.pinkwither;

import com.jeff.pets.mob.aprilfools.PinkWither;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PinkWitherRenderer extends PetRenderer<@NotNull PinkWither, @NotNull WitherRenderState, @NotNull WitherBossModel> {

    public static final ModelLayerLocation PINK_WITHER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("pinkwither"), "main");

    public PinkWitherRenderer(EntityRendererProvider.Context context) {
        super(context, new WitherBossModel(context.bakeLayer(ModelLayers.WITHER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WitherRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/wither/wither_pink.png");
    }

    @Override
    public WitherRenderState createRenderState() {
        return new WitherRenderState();
    }

    @Override
    public void extractRenderState(PinkWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }
}
