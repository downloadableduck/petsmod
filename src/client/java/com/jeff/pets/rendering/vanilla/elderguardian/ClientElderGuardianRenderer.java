package com.jeff.pets.rendering.vanilla.elderguardian;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.GuardianModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientElderGuardianRenderer extends PetRenderer<@NotNull ClientElderGuardian, @NotNull GuardianRenderState, @NotNull GuardianModel> {
    public static final ModelLayerLocation ELDER_GUARDIAN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientelderguardian"), "main");

    public ClientElderGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new GuardianModel(context.bakeLayer(ModelLayers.ELDER_GUARDIAN)), 0.75f);
        this.scale(new PoseStack());
    }

    private void scale(PoseStack poseStack) {
        poseStack.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GuardianRenderState livingEntityRenderState) {
        livingEntityRenderState.spikesAnimation = 1;
        return ResourceLocation.withDefaultNamespace("textures/entity/guardian_elder.png");
    }

    @Override
    public GuardianRenderState createRenderState() {
        return new GuardianRenderState();
    }
}
