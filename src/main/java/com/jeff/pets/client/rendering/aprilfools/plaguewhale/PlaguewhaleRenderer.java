package com.jeff.pets.client.rendering.aprilfools.plaguewhale;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.aprilfools.toxifin.ToxifinSlabModel;
import com.jeff.pets.mob.aprilfools.PlaguewhaleSlab;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlaguewhaleRenderer extends PetRenderer<@NotNull PlaguewhaleSlab, @NotNull ToxifinSlabModel<PlaguewhaleSlab>> {

    public static final ModelLayerLocation PLAGUEWHALE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("plaguewhale_slab"), "main");

    public PlaguewhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new ToxifinSlabModel<>(context.bakeLayer(PLAGUEWHALE_LOCATION)), 0.75f);
    }

    @Override
    protected void scale(PlaguewhaleSlab state, PoseStack poseStack, float f) {
        poseStack.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PlaguewhaleSlab livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/plaguewhale.png");
    }
}
