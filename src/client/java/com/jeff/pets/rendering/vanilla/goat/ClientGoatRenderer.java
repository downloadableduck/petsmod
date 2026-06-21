package com.jeff.pets.rendering.vanilla.goat;

import com.jeff.pets.mob.vanilla.neutral.ClientGoat;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GoatRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientGoatRenderer extends PetRenderer<@NotNull ClientGoat, @NotNull GoatRenderState, @NotNull ClientGoatModel> {

    public static final ModelLayerLocation GOAT_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientgoat"), "main");

    public ClientGoatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientGoatModel(context.bakeLayer(ModelLayers.GOAT)), 0.75f);
    }

    @Override
    protected void scale(@NotNull GoatRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GoatRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/goat/goat.png");
    }

    @Override
    public GoatRenderState createRenderState() {
        return new GoatRenderState();
    }
}
