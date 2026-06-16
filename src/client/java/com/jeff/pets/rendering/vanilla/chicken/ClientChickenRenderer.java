package com.jeff.pets.rendering.vanilla.chicken;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<@NotNull ClientChicken, @NotNull ChickenRenderState, @NotNull ClientChickenModel> {
    public static final ModelLayerLocation CHICKEN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientchicken"), "main");

    public String chickenTexturePath;

    public ClientChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ChickenRenderState livingEntityRenderState) {
        if (Objects.equals(CONFIG.chickenSkin, "temperate")) {
            chickenTexturePath = "textures/entity/chicken/temperate_chicken.png";
        } else if (Objects.equals(CONFIG.chickenSkin, "cold")) {
            chickenTexturePath = "textures/entity/chicken/cold_chicken.png";
        } else if (Objects.equals(CONFIG.chickenSkin, "warm")) {
            chickenTexturePath = "textures/entity/chicken/warm_chicken.png";
        }
        return ResourceLocation.withDefaultNamespace(chickenTexturePath);
    }

    @Override
    protected void scale(ChickenRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
