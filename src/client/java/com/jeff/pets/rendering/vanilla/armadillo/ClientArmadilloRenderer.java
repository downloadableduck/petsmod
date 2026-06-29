package com.jeff.pets.rendering.vanilla.armadillo;

import com.jeff.pets.mob.vanilla.passive.ClientArmadillo;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientArmadilloRenderer extends PetRenderer<@NotNull ClientArmadillo, @NotNull ClientArmadilloModel> {

    public static final ModelLayerLocation ARMADILLO_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/armadillo.png"), "main");

    public ClientArmadilloRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientArmadilloModel(context.bakeLayer(ModelLayers.ARMADILLO)), 0.4F);
    }

    @Override
    protected void scale(ClientArmadillo state, @NotNull PoseStack poseStack, float f) {
        super.scale(state, poseStack, f);
        if (CONFIG.isBaby) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientArmadillo livingEntityRenderState) {
        return ARMADILLO_LOCATION.getModel();
    }
}
