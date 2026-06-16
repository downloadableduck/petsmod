package com.jeff.pets.rendering.aprilfools.raytracing;

import com.jeff.pets.mob.aprilfools.RayTracing;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.entity.player.PlayerSkin;
import org.jetbrains.annotations.NotNull;

public class RayTracingRenderer extends PetRenderer<@NotNull RayTracing, @NotNull AvatarRenderState, @NotNull HumanoidModel<@NotNull AvatarRenderState>> {

    public static final ModelLayerLocation RAY_TRACING_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("raytracing"), "main");

    private final ClientAsset.Texture playerSkinTexture = new ClientAsset.Texture() {
        @Override
        public @NotNull ResourceLocation texturePath() {
            return ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png");
        }

        @Override
        public @NotNull ResourceLocation id() {
            return ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png");
        }
    };

    public RayTracingRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel(context.bakeLayer(RAY_TRACING_LOCATION), true), 0.75f);
    }

    public static LayerDefinition createBasePlayerBodyLayer() {
        var mesh = PlayerModel.createMesh(CubeDeformation.NONE, true);
        return LayerDefinition.create(mesh, 64, 64);
    }

    protected PlayerSkin getSkinFromRayTracingTexture() {
        return PlayerSkin.insecure(playerSkinTexture, playerSkinTexture, playerSkinTexture, PlayerModelType.WIDE);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(AvatarRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ray_tracing.png");
    }


    @Override
    public AvatarRenderState createRenderState() {
        return new AvatarRenderState();
    }

    @Override
    public void extractRenderState(RayTracing rayTracing, AvatarRenderState state, float f) {
        super.extractRenderState(rayTracing, state, f);
        state.skin = this.getSkinFromRayTracingTexture();
        state.showCape = false;
    }
}
