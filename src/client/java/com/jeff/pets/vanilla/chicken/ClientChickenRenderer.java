package com.jeff.pets.vanilla.chicken;

import com.jeff.pets.vanilla.passive.ClientChicken;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientChickenRenderer extends MobRenderer<@NotNull ClientChicken, @NotNull ChickenRenderState, @NotNull ChickenModel> {
    public static final ModelLayerLocation CHICKEN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientchicken"), "main");

    public String chickenTexturePath;

    public ClientChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(ChickenRenderState livingEntityRenderState) {
        if (Objects.equals(CONFIG.chickenSkin, "temperate")) {
            chickenTexturePath = "textures/entity/chicken/temperate_chicken.png";
        } else if (Objects.equals(CONFIG.chickenSkin, "cold")) {
            chickenTexturePath = "textures/entity/chicken/cold_chicken.png";
        } else if (Objects.equals(CONFIG.chickenSkin, "warm")) {
            chickenTexturePath = "textures/entity/chicken/warm_chicken.png";
        }
        return Identifier.withDefaultNamespace(chickenTexturePath);
    }
}
