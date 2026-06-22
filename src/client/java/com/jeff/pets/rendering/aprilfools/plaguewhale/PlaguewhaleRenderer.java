package com.jeff.pets.rendering.aprilfools.plaguewhale;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.aprilfools.toxifin.ToxifinSlabModel;
import com.jeff.pets.mob.aprilfools.PlaguewhaleSlab;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlaguewhaleRenderer extends PetRenderer<@NotNull PlaguewhaleSlab, @NotNull GuardianRenderState, @NotNull ToxifinSlabModel> {

    public static final ModelLayerLocation PLAGUEWHALE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("plaguewhale_slab"), "main");

    public PlaguewhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new ToxifinSlabModel(context.bakeLayer(PLAGUEWHALE_LOCATION)), 0.75f);
        this.scale(new GuardianRenderState(), new PoseStack());
    }

    @Override
    protected void scale(GuardianRenderState state, PoseStack poseStack) {
        poseStack.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GuardianRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/plaguewhale.png");
    }

    @Override
    public GuardianRenderState createRenderState() {
        return new GuardianRenderState();
    }
}
