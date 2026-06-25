package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

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
        return ResourceLocation.withDefaultNamespace("textures/entity/chicken.png");
    }

    @Override
    protected void scale(ChickenRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
