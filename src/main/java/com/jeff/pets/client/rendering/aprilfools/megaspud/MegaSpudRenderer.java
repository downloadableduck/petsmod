package com.jeff.pets.client.rendering.aprilfools.megaspud;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.MegaSpud;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MegaSpudRenderer extends PetRenderer<@NotNull MegaSpud, @NotNull MegaSpudModel> {

    public static final ModelLayerLocation MEGA_SPUD_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("megaspoud"), "main");

    public MegaSpudRenderer(EntityRendererProvider.Context context) {
        super(context, new MegaSpudModel(context.bakeLayer(MEGA_SPUD_LOCATION)), 0.75f);
        this.addLayer((RenderLayer) new MegaSpudOuterLayer((RenderLayerParent) this, context));
    }

    @Override
    protected void scale(MegaSpud state, PoseStack poseStack, float f) {
        poseStack.scale(9, 12, 9);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MegaSpud livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/slime/mega_spud.png");
    }
}
