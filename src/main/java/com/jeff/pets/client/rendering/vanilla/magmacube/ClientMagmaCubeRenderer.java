package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeModel<ClientMagmaCube>> {

    public static final ModelLayerLocation MAGMA_CUBE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientmagmacube"), "main");

    public ClientMagmaCubeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel<>(context.bakeLayer(ModelLayers.MAGMA_CUBE)), 0.75f);
    }

    @Override
    protected void scale(ClientMagmaCube slimeRenderState, @NotNull PoseStack poseStack, float a) {
        int magmaCubeScale = switch (CONFIG.magmaCubeSkin) {
            case "small" -> 1;
            case "medium" -> 2;
            case "large" -> 4;
            case null, default -> 1;
        };
        poseStack.scale(magmaCubeScale, magmaCubeScale, magmaCubeScale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/magmacube.png");
    }
}
