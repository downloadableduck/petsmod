package com.jeff.pets.client.rendering.aprilfools.angryghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.AngryGhast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class AngryGhastRenderer extends PetRenderer<@NotNull AngryGhast, @NotNull GhastModel<AngryGhast>> {

    public static final ModelLayerLocation ANGRY_GHAST_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "angryghast"), "main");

    public AngryGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel<>(context.bakeLayer(ANGRY_GHAST_LOCATION)), 0.75f);
    }

    @Override
    protected void scale(AngryGhast ghast, PoseStack poseStack, float f) {
        poseStack.scale(4.5F, 4.5F, 4.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AngryGhast livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/angry_ghast/ghast_angry.png");
    }
}
