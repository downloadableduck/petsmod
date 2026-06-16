package com.jeff.pets.rendering.vanilla.dolphin;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DolphinRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin, @NotNull DolphinRenderState, @NotNull DolphinModel> {
    public static final ModelLayerLocation DOLPHIN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientdolphin"), "main");

    public ClientDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new DolphinModel(context.bakeLayer(ModelLayers.DOLPHIN)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(DolphinRenderState dolphinRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/dolphin.png");
    }

    @Override
    protected void scale(DolphinRenderState state, @NotNull PoseStack poseStack) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
    }

    @Override
    public DolphinRenderState createRenderState() {
        return new DolphinRenderState();
    }
}
