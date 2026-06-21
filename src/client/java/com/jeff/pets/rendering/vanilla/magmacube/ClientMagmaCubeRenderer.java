package com.jeff.pets.rendering.vanilla.magmacube;

import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeRenderState, @NotNull SlimeModel> {

    public static final ModelLayerLocation MAGMA_CUBE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientmagmacube"), "main");

    public ClientMagmaCubeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel(context.bakeLayer(ModelLayers.MAGMA_CUBE)), 0.75f);
    }

    @Override
    protected void scale(SlimeRenderState slimeRenderState, @NotNull PoseStack poseStack) {
        int magmaCubeScale = switch (CONFIG.magmaCubeSkin) {
            case "small" -> 1;
            case "medium" -> 2;
            case "large" -> 4;
            case null, default -> 1;
        };
        float f = slimeRenderState.squish / ((float) magmaCubeScale * 0.5F + 1.0F);
        float g = 1.0F / (f + 1.0F);
        poseStack.scale(g * (float) magmaCubeScale, 1.0F / g * (float) magmaCubeScale, g * (float) magmaCubeScale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SlimeRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/slime/magmacube.png");
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }
}
