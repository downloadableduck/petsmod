package com.jeff.pets.vanilla.drowned;

import com.jeff.pets.vanilla.hostile.ClientDrowned;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.zombie.DrownedModel;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedRenderer extends MobRenderer<@NotNull ClientDrowned, @NotNull ZombieRenderState, @NotNull DrownedModel> {

    public static final ModelLayerLocation DROWNED_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientdrowned"), "main");

    public ClientDrownedRenderer(EntityRendererProvider.Context context) {
        super(context, new DrownedModel(context.bakeLayer(ModelLayers.DROWNED)), 0.75f);
        this.addLayer(new DrownedOuterLayer(this, EntityModelSet.vanilla()));
    }

    public static LayerDefinition createBaseDrownedLayer() {
        DrownedModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/drowned.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }
}
