package com.jeff.pets.vanilla.bogged;

import com.jeff.pets.vanilla.hostile.ClientBogged;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.BoggedModel;
import net.minecraft.client.renderer.entity.BoggedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.BoggedRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBoggedRenderer extends MobRenderer<@NotNull ClientBogged, @NotNull BoggedRenderState, @NotNull BoggedModel> {

    public static final ModelLayerLocation BOGGED_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientbogged"), "main");

    public ClientBoggedRenderer(EntityRendererProvider.Context context) {
        super(context, new BoggedModel(context.bakeLayer(ModelLayers.BOGGED)), 0.75f);
        this.addLayer(new SkeletonClothingLayer<>(this, context.getModelSet(), ModelLayers.BOGGED_OUTER_LAYER, Identifier.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png")));
    }

    @Override
    public @NotNull Identifier getTextureLocation(BoggedRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/skeleton/bogged.png");
    }

    @Override
    public BoggedRenderState createRenderState() {
        return new BoggedRenderState();
    }
}
