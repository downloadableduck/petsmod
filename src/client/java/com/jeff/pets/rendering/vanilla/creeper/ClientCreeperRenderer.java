package com.jeff.pets.rendering.vanilla.creeper;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<@NotNull ClientCreeper, @NotNull CreeperRenderState, @NotNull CreeperModel> {
    public static final ModelLayerLocation CREEPER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcreeper"), "main");

    public ClientCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer(new CreeperPowerLayer(this, context.getModelSet()));
    }

    public static LayerDefinition createBaseCreeperLayer() {
        CreeperModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CreeperRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper.png");
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public void extractRenderState(ClientCreeper creeper, CreeperRenderState state, float f) {
        super.extractRenderState(creeper, state, f);
        if (Objects.equals(CONFIG.creeperSkin, "charged")) {
            state.isPowered = true;
        }
    }
}
