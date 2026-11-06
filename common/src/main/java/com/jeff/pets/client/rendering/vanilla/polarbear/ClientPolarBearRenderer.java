package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.polarbear.PolarBearModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PolarBearRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPolarBearRenderer extends PetRenderer<@NotNull ClientPolarBear, @NotNull PolarBearRenderState, @NotNull PolarBearModel> {

    public static final ModelLayerLocation POLAR_BEAR_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpolarbear"), "main");

    public ClientPolarBearRenderer(EntityRendererProvider.Context context) {
        super(context, new PolarBearModel(context.bakeLayer(ModelLayers.POLAR_BEAR)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        PolarBearModel.createBodyLayer();
        return LayerDefinition.create(new MeshDefinition(), 128, 64);
    }

    @Override
    protected void scale(@NotNull PolarBearRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (livingEntityRenderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(PolarBearRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/bear/polarbear.png");
    }

    @Override
    public PolarBearRenderState createRenderState() {
        return new PolarBearRenderState();
    }
}
