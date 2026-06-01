package com.jeff.pets.client.rendering.vanilla.armadillo;

import com.jeff.pets.mob.vanilla.passive.ClientArmadillo;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.armadillo.AdultArmadilloModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArmadilloRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientArmadilloRenderer extends PetRenderer<@NotNull ClientArmadillo, @NotNull ArmadilloRenderState, @NotNull AdultArmadilloModel> {

    public static final ModelLayerLocation ARMADILLO_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/armadillo/armadillo.png"), "main");

    public ClientArmadilloRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultArmadilloModel(context.bakeLayer(ModelLayers.ARMADILLO)), 0.4F);
    }

    protected void scale(ArmadilloRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ArmadilloRenderState livingEntityRenderState) {
        return ARMADILLO_LOCATION.model();
    }

    @Override
    public ArmadilloRenderState createRenderState() {
        return new ArmadilloRenderState();
    }
}
