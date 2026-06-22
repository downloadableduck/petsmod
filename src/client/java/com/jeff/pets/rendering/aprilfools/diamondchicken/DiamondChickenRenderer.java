package com.jeff.pets.rendering.aprilfools.diamondchicken;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.chicken.ClientChickenModel;
import com.jeff.pets.mob.aprilfools.DiamondChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class DiamondChickenRenderer extends PetRenderer<@NotNull DiamondChicken, @NotNull ChickenRenderState, @NotNull ClientChickenModel> {

    public static final ModelLayerLocation DIAMOND_CHICKEN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("diamondchicken"), "main");

    public DiamondChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientChickenModel(context.bakeLayer(DIAMOND_CHICKEN_LOCATION)), 0.75f);
    }

    @Override
    protected void scale(@NotNull ChickenRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ChickenRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/diamond_chicken.png");
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }
}
