package com.jeff.pets.rendering.vanilla.donkey;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DonkeyModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DonkeyRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<@NotNull ClientDonkey, @NotNull DonkeyRenderState, @NotNull DonkeyModel> {
    public static ModelLayerLocation DONKEY_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientdonkey"), "main");

    public ClientDonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new DonkeyModel(context.bakeLayer(ModelLayers.DONKEY)), 0.5f);
    }

    public static LayerDefinition createBodyLayer() {
        DonkeyModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    public @NotNull ResourceLocation getTextureLocation(DonkeyRenderState donkeyRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/horse/donkey.png");
    }

    protected void scale(DonkeyRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public DonkeyRenderState createRenderState() {
        return new DonkeyRenderState();
    }
}
