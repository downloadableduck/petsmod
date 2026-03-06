package com.jeff.pets.vanilla.stray;

import com.jeff.pets.vanilla.hostile.ClientStray;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientStrayRenderer extends MobRenderer<@NotNull ClientStray, @NotNull SkeletonRenderState, @NotNull SkeletonModel<@NotNull SkeletonRenderState>> {

    public static final ModelLayerLocation STRAY_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientstray"), "main");

    public ClientStrayRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.STRAY)), 0.75f);
        this.addLayer(new SkeletonClothingLayer<>(this, EntityModelSet.vanilla(), ModelLayers.STRAY_OUTER_LAYER, Identifier.withDefaultNamespace("textures/entity/skeleton/stray_overlay.png")));
    }

    @Override
    public @NotNull Identifier getTextureLocation(SkeletonRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/skeleton/stray.png");
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
