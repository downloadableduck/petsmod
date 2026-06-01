package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.dolphin.DolphinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DolphinRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin, @NotNull DolphinRenderState, @NotNull DolphinModel> {
    public static final ModelLayerLocation DOLPHIN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientdolphin"), "main");

    public ClientDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new DolphinModel(context.bakeLayer(ModelLayers.DOLPHIN)), 0.7f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(DolphinRenderState dolphinRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/dolphin/dolphin.png");
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
