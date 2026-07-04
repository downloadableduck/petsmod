package com.jeff.pets.rendering.aprilfools.pinkwither;

import com.jeff.pets.mob.aprilfools.PinkWither;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.wither.ClientWitherModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PinkWitherRenderer extends PetRenderer<@NotNull PinkWither, @NotNull ClientWitherModel<PinkWither>> {

    public static final ModelLayerLocation PINK_WITHER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "pinkwither"), "main");

    public PinkWitherRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWitherModel<>(context.bakeLayer(ModelLayers.WITHER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PinkWither livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/wither/wither_pink.png");
    }

    /*@Override
    public void extractRenderState(PinkWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        //wither.yHeadRot = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }*/
}
