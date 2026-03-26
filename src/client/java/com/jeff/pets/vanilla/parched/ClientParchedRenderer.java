package com.jeff.pets.vanilla.parched;

import com.jeff.pets.vanilla.hostile.ClientParched;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.skeleton.Parched;
import org.jetbrains.annotations.NotNull;

public class ClientParchedRenderer extends MobRenderer<@NotNull ClientParched, @NotNull SkeletonRenderState, @NotNull SkeletonModel<@NotNull SkeletonRenderState>> {

    public static final ModelLayerLocation PARCHED_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientparched"), "main");

    public ClientParchedRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.PARCHED)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(SkeletonRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/skeleton/parched.png");
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
