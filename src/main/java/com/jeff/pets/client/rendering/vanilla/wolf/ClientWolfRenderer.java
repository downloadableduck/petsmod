package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<@NotNull ClientWolf, @NotNull ClientWolfModel> {

    public static final ModelLayerLocation WOLF_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwolf"), "main");

    public ClientWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        ClientWolfModel.createMeshDefinition(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(@NotNull ClientWolf livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWolf livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new ResourceLocation("minecraft", wolfTexturePath);
    }

    @Override
    public void render(ClientWolf wolf, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(wolf, f, g, poseStack, source, i);
        wolf.setInSittingPose(wolf.isPassenger());
    }
}
