package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned, @NotNull ClientDrownedModel> {

    public static final ModelLayerLocation DROWNED_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientdrowned"), "main");

    public ClientDrownedRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientDrownedModel(context.bakeLayer(ModelLayers.DROWNED)), 0.75f);
        this.addLayer(new ClientDrownedOuterLayer(this, context));
    }

    public static LayerDefinition createBaseDrownedLayer() {
        ClientDrownedModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(ClientDrowned state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientDrowned livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void setupRotations(ClientDrowned state, @NotNull PoseStack poseStack, float f, float g, float i, float k) {
        super.setupRotations(state, poseStack, f, g, i, k);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
