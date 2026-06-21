package com.jeff.pets.client.rendering.aprilfools.raytracing;

import com.jeff.pets.mob.aprilfools.RayTracing;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RayTracingRenderer extends PetRenderer<@NotNull RayTracing, @NotNull PlayerRenderState, @NotNull HumanoidModel<@NotNull PlayerRenderState>> {

    public static final ModelLayerLocation RAY_TRACING_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("raytracing"), "main");

    private final ClientAsset playerSkinTexture = new ClientAsset(ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png"));

    public RayTracingRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel(context.bakeLayer(RAY_TRACING_LOCATION), true), 0.75f);
    }

    public static LayerDefinition createBasePlayerBodyLayer() {
        var mesh = PlayerModel.createMesh(CubeDeformation.NONE, true);
        return LayerDefinition.create(mesh, 64, 64);
    }

    protected PlayerSkin getSkinFromRayTracingTexture() {
        return new PlayerSkin(playerSkinTexture.texturePath(), playerSkinTexture.texturePath().getPath(), playerSkinTexture.texturePath(), playerSkinTexture.texturePath(), PlayerSkin.Model.WIDE, false);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PlayerRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png");
    }


    @Override
    public PlayerRenderState createRenderState() {
        return new PlayerRenderState();
    }

    @Override
    public void extractRenderState(RayTracing rayTracing, PlayerRenderState state, float f) {
        super.extractRenderState(rayTracing, state, f);
        state.skin = this.getSkinFromRayTracingTexture();
        state.showCape = false;
    }
}
