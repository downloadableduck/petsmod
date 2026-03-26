package com.jeff.pets.vanilla.creeper;

import com.jeff.pets.vanilla.hostile.ClientCreeper;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCreeperRenderer extends MobRenderer<@NotNull ClientCreeper, @NotNull CreeperRenderState, @NotNull CreeperModel> {
    public static final ModelLayerLocation CREEPER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcreeper"), "main");

    public ClientCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer(new CreeperPowerLayer(this, context.getModelSet()));
    }

    public static LayerDefinition createBaseCreeperLayer() {
        CreeperModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    public @NotNull Identifier getTextureLocation(CreeperRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/creeper/creeper.png");
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }
}
