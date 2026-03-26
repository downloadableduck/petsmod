package com.jeff.pets.vanilla.witherskeleton;

import com.jeff.pets.vanilla.hostile.ClientWitherSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitherSkeletonRenderer extends MobRenderer<@NotNull ClientWitherSkeleton, @NotNull SkeletonRenderState, @NotNull SkeletonModel<@NotNull SkeletonRenderState>> {

    public static final ModelLayerLocation WITHER_SKELETON_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientwitherskeleton"), "main");

    public ClientWitherSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.WITHER_SKELETON)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(SkeletonRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png");
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
