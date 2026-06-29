package com.jeff.pets.client.rendering.aprilfools.raytracing;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.RayTracing;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RayTracingRenderer extends PetRenderer<@NotNull RayTracing, @NotNull HumanoidModel<@NotNull RayTracing>> {

    public static final ModelLayerLocation RAY_TRACING_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("raytracing"), "main");

    public RayTracingRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel(context.bakeLayer(RAY_TRACING_LOCATION), true), 0.75f);
    }

    public static LayerDefinition createBasePlayerBodyLayer() {
        var mesh = PlayerModel.createMesh(CubeDeformation.NONE, true);
        return LayerDefinition.create(mesh, 64, 64);
    }

    protected PlayerSkin getSkinFromRayTracingTexture() {
        return new PlayerSkin(ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png"), "https://petsmod.com", ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png"), ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png"), PlayerSkin.Model.WIDE, false);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(RayTracing livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png");
    }

    @Override
    public void render(RayTracing rayTracing, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(rayTracing, f, g, poseStack, source, i);
        //rayTracing.skin = this.getSkinFromRayTracingTexture();
        //state.showCape = false;
    }
}
