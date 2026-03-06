package com.jeff.pets.vanilla.skeleton;

import com.jeff.pets.vanilla.hostile.ClientSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.BoggedModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientSkeletonRenderer extends MobRenderer<@NotNull ClientSkeleton, @NotNull SkeletonRenderState, @NotNull SkeletonModel<@NotNull SkeletonRenderState>> {

    public static final ModelLayerLocation SKELETON_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientskeleton"), "main");
    public ClientSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(SkeletonRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/skeleton/skeleton.png");
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }
}
