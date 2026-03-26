package com.jeff.pets.vanilla.magmacube;

import com.jeff.pets.vanilla.hostile.ClientMagmaCube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.MagmaCubeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientMagmaCubeRenderer extends MobRenderer<@NotNull ClientMagmaCube, @NotNull SlimeRenderState, @NotNull MagmaCubeModel> {

    public static final ModelLayerLocation MAGMA_CUBE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientmagmacube"), "main");

    public ClientMagmaCubeRenderer(EntityRendererProvider.Context context) {
        super(context, new MagmaCubeModel(context.bakeLayer(ModelLayers.MAGMA_CUBE)), 500f);
        this.scale(new SlimeRenderState(), new PoseStack());
    }

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
    public @NotNull Identifier getTextureLocation(SlimeRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/slime/magmacube.png");
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }
}
