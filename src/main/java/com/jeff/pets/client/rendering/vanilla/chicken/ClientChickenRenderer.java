package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<@NotNull ClientChicken, @NotNull ChickenRenderState, @NotNull ClientChickenModel> {
    public static final ModelLayerLocation CHICKEN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientchicken"), "main");

    public String chickenTexturePath;

    public ClientChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(ChickenRenderState livingEntityRenderState) {
        String skin = ((IPetRenderState) livingEntityRenderState).pets$getPetSkin();
        if (Objects.equals(skin, "temperate")) {
            chickenTexturePath = "textures/entity/chicken/chicken_temperate.png";
        } else if (Objects.equals(skin, "cold")) {
            chickenTexturePath = "textures/entity/chicken/chicken_cold.png";
        } else if (Objects.equals(skin, "warm")) {
            chickenTexturePath = "textures/entity/chicken/chicken_warm.png";
        }
        return Identifier.withDefaultNamespace(chickenTexturePath);
    }

    @Override
    protected void scale(ChickenRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
