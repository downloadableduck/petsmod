package com.jeff.pets.client.rendering.vanilla.happyghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHappyGhast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHappyGhastRenderer extends PetRenderer<@NotNull ClientHappyGhast, @NotNull HappyGhastRenderState, @NotNull HappyGhastModel> {

    public static final ModelLayerLocation GHAST_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthappyghast"), "main");

    public ClientHappyGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new HappyGhastModel(context.bakeLayer(ModelLayers.HAPPY_GHAST)), 0.75f);
    }

    public static LayerDefinition createGhastBodyLayer() {
        HappyGhastModel.createBodyLayer(false, CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 128, 128);
    }

    @Override
    protected void scale(HappyGhastRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.2375F, 0.2375F, 0.2375F);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(HappyGhastRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/ghast/happy_ghast.png");
    }

    @Override
    public HappyGhastRenderState createRenderState() {
        return new HappyGhastRenderState();
    }
}
