package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned, @NotNull ZombieRenderState, @NotNull ClientDrownedModel> {

    public static final ModelLayerLocation DROWNED_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientdrowned"), "main");

    public ClientDrownedRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientDrownedModel(context.bakeLayer(ModelLayers.DROWNED)), 0.75f);
        this.addLayer(new ClientDrownedOuterLayer(this, context));
    }

    public static LayerDefinition createBaseDrownedLayer() {
        ClientDrownedModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    protected void scale(ZombieRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }


    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/drowned.png");
    }

    @Override
    public void setupRotations(ZombieRenderState state, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(state, poseStack, f, g);
        if (state.isPassenger) {
            poseStack.translate(0, -0.5, 0);
        }
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public void extractRenderState(ClientDrowned drowned, ZombieRenderState state, float f) {
        super.extractRenderState(drowned, state, f);
        state.isBaby = CONFIG.isBaby;
        state.isPassenger = drowned.isPassenger();
    }
}
