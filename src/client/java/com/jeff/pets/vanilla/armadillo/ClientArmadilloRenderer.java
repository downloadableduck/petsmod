package com.jeff.pets.vanilla.armadillo;

import com.jeff.pets.vanilla.passive.ClientArmadillo;
import net.minecraft.client.model.animal.armadillo.ArmadilloModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ArmadilloRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientArmadilloRenderer extends MobRenderer<@NotNull ClientArmadillo, @NotNull ArmadilloRenderState, ArmadilloModel> {

    public static final ModelLayerLocation ARMADILLO_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/armadillo.png"), "main");

    public ClientArmadilloRenderer(EntityRendererProvider.Context context) {
        super(context, new ArmadilloModel(context.bakeLayer(ModelLayers.ARMADILLO)), 0.4F);
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
