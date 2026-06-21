package com.jeff.pets.rendering.vanilla.breeze;

import com.jeff.pets.mob.vanilla.hostile.ClientBreeze;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.BreezeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.BreezeWindLayer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.client.renderer.entity.BreezeRenderer.enable;

public class ClientBreezeRenderer extends PetRenderer<@NotNull ClientBreeze, @NotNull BreezeRenderState, @NotNull BreezeModel> {

    public static final ModelLayerLocation BREEZE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientbreeze"), "main");

    public ClientBreezeRenderer(EntityRendererProvider.Context context) {
        super(context, new BreezeModel(context.bakeLayer(BREEZE_LOCATION)), 0.75f);
        this.addLayer(new BreezeWindLayer(context, this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BreezeRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/breeze/breeze.png");
    }

    @Override
    public BreezeRenderState createRenderState() {
        return new BreezeRenderState();
    }

    @Override
    public void extractRenderState(ClientBreeze breeze, BreezeRenderState state, float f) {
        super.extractRenderState(breeze, state, f);
        state.idle.start(0);
    }

    public void render(BreezeRenderState breezeRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        BreezeModel breezeModel = this.getModel();
        enable(breezeModel, breezeModel.head(), breezeModel.rods());
        super.render(breezeRenderState, poseStack, multiBufferSource, i);
    }
}
