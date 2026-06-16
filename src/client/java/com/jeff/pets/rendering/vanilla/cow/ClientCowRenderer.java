package com.jeff.pets.rendering.vanilla.cow;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCow;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer<@NotNull ClientCow, @NotNull CowRenderState, @NotNull ClientCowModel> {
    public static ModelLayerLocation COW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcow"), "main");
    String cowTexturePath;

    public ClientCowRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(CowRenderState cowRenderState) {
        switch (CONFIG.cowSkin) {
            case "temperate" -> cowTexturePath = "textures/entity/cow/temperate_cow.png";
            case "warm" -> cowTexturePath = "textures/entity/cow/warm_cow.png";
            case "cold" -> cowTexturePath = "textures/entity/cow/cold_cow.png";
            case null, default -> {
                cowTexturePath = "textures/entity/cow/temperate_cow.png";
            }
        }
        return ResourceLocation.withDefaultNamespace(cowTexturePath);
    }

    @Override
    protected void scale(CowRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public CowRenderState createRenderState() {
        return new CowRenderState();
    }
}
