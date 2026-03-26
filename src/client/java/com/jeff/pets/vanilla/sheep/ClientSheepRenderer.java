package com.jeff.pets.vanilla.sheep;

import com.jeff.pets.vanilla.passive.ClientSheep;
import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SheepWoolUndercoatLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSheepRenderer extends MobRenderer<@NotNull ClientSheep, @NotNull SheepRenderState, SheepModel> {
    public static final ModelLayerLocation SHEEP_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsheep"), "main");

    public ClientSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new SheepWoolUndercoatLayer(this, context.getModelSet()));
        this.addLayer(new ClientSheepWoolLayer(this, context.getModelSet()));
    }

    public SheepRenderState createRenderState() {
        return new SheepRenderState();
    }

    public void extractRenderState(ClientSheep sheep, SheepRenderState sheepRenderState, float f) {
        super.extractRenderState(sheep, sheepRenderState, f);
        sheepRenderState.headEatAngleScale = sheep.getHeadEatAngleScale(f);
        sheepRenderState.headEatPositionScale = sheep.getHeadEatPositionScale(f);
        sheepRenderState.isJebSheep = checkMagicName(sheep, "jeb_");
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull SheepRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/sheep/sheep.png");
    }
}
