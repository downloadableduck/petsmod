package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<@NotNull ClientPolarBear, @NotNull ClientPolarBearModel> {

    public static final ModelLayerLocation POLAR_BEAR_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientpolarbear"), "main");

    public ClientPolarBearRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPolarBearModel(context.bakeLayer(ModelLayers.POLAR_BEAR)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        PolarBearModel.createBodyLayer();
        return LayerDefinition.create(new MeshDefinition(), 128, 64);
    }

    @Override
    protected void scale(@NotNull ClientPolarBear livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bear/polarbear.png");
    }
}
